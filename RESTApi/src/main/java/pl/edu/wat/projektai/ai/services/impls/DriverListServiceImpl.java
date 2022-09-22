package pl.edu.wat.projektai.ai.services.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wat.projektai.ai.dto.DriverDto;
import pl.edu.wat.projektai.ai.exceptions.DriverAlreadyExistException;
import pl.edu.wat.projektai.ai.models.Driver;
import pl.edu.wat.projektai.ai.models.Profit;
import pl.edu.wat.projektai.ai.models.Rent;
import pl.edu.wat.projektai.ai.repositories.DriverRepository;
import pl.edu.wat.projektai.ai.repositories.ProfitRepository;
import pl.edu.wat.projektai.ai.repositories.RentRepository;
import pl.edu.wat.projektai.ai.services.DriverListService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DriverListServiceImpl implements DriverListService {

    private final DriverRepository driverRepository;
    private final RentRepository rentRepository;
    private final ProfitRepository profitRepository;

    @Autowired
    public DriverListServiceImpl(DriverRepository driverRepository, RentRepository rentRepository, ProfitRepository profitRepository) {
        this.driverRepository = driverRepository;
        this.rentRepository = rentRepository;
        this.profitRepository = profitRepository;
    }

    @Override
    public List<DriverDto> getDriverList() {
        List<Driver> drivers = new ArrayList<>(driverRepository.findAll());
        return drivers.stream().map(driver -> convertToDriverDto(driver)).collect(Collectors.toList());
    }

    @Override
    public DriverDto findDriver(String pesel) {
        Driver driver = driverRepository.findByPesel(pesel);
        return convertToDriverDto(driver);
    }

    @Override
    public void deleteDriverByPesel(String pesel) {
        List<Rent> rents = rentRepository.findByDriver(driverRepository.findByPesel(pesel));
        for (Rent r: rents){
            rentRepository.delete(r);
        }
        List<Profit> profits = profitRepository.findByDriver(driverRepository.findByPesel(pesel));
        for (Profit p: profits){
            profitRepository.delete(p);
        }
        driverRepository.delete(driverRepository.findByPesel(pesel));
    }

    @Override
    public DriverDto addDriver(DriverDto driverDto) {
        if (driverRepository.findByPesel(driverDto.getPesel()) == null) {
            Driver driver = new Driver();
            driver.setFirstName(driverDto.getFirstName());
            driver.setName(driverDto.getName());
            driver.setPesel(driverDto.getPesel());
            return convertToDriverDto(driverRepository.save(driver));
        }
        else
            throw new DriverAlreadyExistException("Kierowca juz istnieje w bazie danych.");
    }

    private DriverDto convertToDriverDto(Driver driver){
        DriverDto driverDto = new DriverDto();
        driverDto.setFirstName(driver.getFirstName());
        driverDto.setName(driver.getName());
        driverDto.setPesel(driver.getPesel());
        return  driverDto;
    }


}
