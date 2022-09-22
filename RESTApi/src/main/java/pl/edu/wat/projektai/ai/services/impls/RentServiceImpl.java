package pl.edu.wat.projektai.ai.services.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wat.projektai.ai.dto.RentDto;
import pl.edu.wat.projektai.ai.exceptions.RentNotFound;
import pl.edu.wat.projektai.ai.exceptions.VehicleIsAlreadyRented;
import pl.edu.wat.projektai.ai.models.Driver;
import pl.edu.wat.projektai.ai.models.Rent;
import pl.edu.wat.projektai.ai.repositories.DriverRepository;
import pl.edu.wat.projektai.ai.repositories.RentRepository;
import pl.edu.wat.projektai.ai.services.RentService;
import pl.edu.wat.projektai.ai.services.VehicleService;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RentServiceImpl implements RentService {

    private final RentRepository rentRepository;
    private final DriverRepository driverRepository;
    private final VehicleService vehicleService;

    @Autowired
    public RentServiceImpl(RentRepository rentRepository, DriverRepository driverRepository, VehicleService vehicleService) {
        this.rentRepository = rentRepository;
        this.driverRepository = driverRepository;
        this.vehicleService = vehicleService;
    }

    @Override
    public List<RentDto> findMyRents(String driverPesel) {
        Driver driver = driverRepository.findByPesel(driverPesel);
        List<Rent> rents = rentRepository.findByDriverAndDateTo(driver, null);
        return rents.stream().map(rent -> convertToDto(rent)).collect(Collectors.toList());
    }

    @Override
    public RentDto addRent(RentDto rentDto) {
        if (rentRepository.findByVehicleAndDateTo(vehicleService.findByVin(rentDto.getVehicle().getVin()), null) == null)
            return convertToDto(rentRepository.save(convertFromDto(rentDto)));
        else
            throw new VehicleIsAlreadyRented("Pojazd jest juz wynajety.");
    }

    @Override
    public RentDto endRent(Long rentId) {
        if (rentRepository.findById(rentId).get().getId() == null)
            throw new RentNotFound("Wynajem nie zostal znaleziony w bazie.");
        else {
            Rent rent = rentRepository.findById(rentId).get();
            rent.setDateTo(new Date(System.currentTimeMillis()));
            return convertToDto(rentRepository.save(rent));
        }
    }

    @Override
    public RentDto actualRent(String vehicleVin) {
        return convertToDto(rentRepository.findByVehicleAndDateTo(vehicleService.findByVin(vehicleVin), null));
    }

    private RentDto convertToDto(Rent rent) {
        RentDto rentDto = new RentDto();
        rentDto.setDateFrom(rent.getDateFrom());
        rentDto.setVehicle(vehicleService.findVehicle(rent.getVehicle().getVin()));
        rentDto.setDriverPesel(rent.getDriver().getPesel());
        rentDto.setCost(rent.getCost());
        return rentDto;
    }

    private Rent convertFromDto(RentDto rentDto) {
        Rent rent = new Rent();
        rent.setDriver(driverRepository.findByPesel(rentDto.getDriverPesel()));
        rent.setVehicle(vehicleService.findByVin(rentDto.getVehicle().getVin()));
        rent.setDateFrom(rentDto.getDateFrom());
        rent.setCost(rentDto.getCost());
        return rent;
    }
}
