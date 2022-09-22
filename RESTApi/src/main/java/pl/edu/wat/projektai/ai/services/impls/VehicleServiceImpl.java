package pl.edu.wat.projektai.ai.services.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wat.projektai.ai.dto.VehicleListDto;
import pl.edu.wat.projektai.ai.exceptions.VehicleAlreadyExistException;
import pl.edu.wat.projektai.ai.models.Rent;
import pl.edu.wat.projektai.ai.models.Vehicle;
import pl.edu.wat.projektai.ai.repositories.RentRepository;
import pl.edu.wat.projektai.ai.repositories.VehicleRepository;
import pl.edu.wat.projektai.ai.services.DriverListService;
import pl.edu.wat.projektai.ai.services.VehicleService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;
    private final RentRepository rentRepository;
    private final DriverListService driverListService;

    @Autowired
    public VehicleServiceImpl(VehicleRepository vehicleRepository, RentRepository rentRepository, DriverListService driverListService) {
        this.vehicleRepository = vehicleRepository;
        this.rentRepository = rentRepository;
        this.driverListService = driverListService;
    }

    @Override
    public VehicleListDto findVehicle(String vin) {
        return convertVehicleToDto(vehicleRepository.findByVin(vin));
    }

    @Override
    public Vehicle findByVin(String vin){
        return vehicleRepository.findByVin(vin);
    }

    @Override
    public List<VehicleListDto> getVehicleList() {
        List<Vehicle> vehicles = new ArrayList<>(vehicleRepository.findAll());
        return vehicles.stream().map(vehicle -> convertVehicleToDto(vehicle)).collect(Collectors.toList());
    }

    @Override
    public void deleteVehicle(VehicleListDto vehicleDto) {
        List<Rent> rents = rentRepository.findByVehicle(vehicleRepository.findByVin(vehicleDto.getVin()));
        for (Rent r : rents) {
            rentRepository.delete(r);
        }
        vehicleRepository.delete(vehicleRepository.findByVin(vehicleDto.getVin()));
    }

    @Override
    public VehicleListDto addVehicle(VehicleListDto vehicle) {
        if (vehicleRepository.findByVin(vehicle.getVin()) == null)
            return convertVehicleToDto(vehicleRepository.save(convertToVehicle(vehicle)));
        else
            throw new VehicleAlreadyExistException("Pojazd juz istnieje w bazie danych.");
    }

    @Override
    public VehicleListDto saveVehicle(VehicleListDto vehicle) {
        Vehicle v;
        v = convertToVehicle(vehicle);
        v.setId(vehicleRepository.findByVin(vehicle.getVin()).getId());
        return convertVehicleToDto(vehicleRepository.save(v));
    }

    private VehicleListDto convertVehicleToDto(Vehicle vehicle) {
        VehicleListDto vehicleListDto = new VehicleListDto();
        vehicleListDto.setBrand(vehicle.getBrand());
        vehicleListDto.setModel(vehicle.getModel());
        vehicleListDto.setRegisterNo(vehicle.getRegisterNr());
        vehicleListDto.setVin(vehicle.getVin());
        vehicleListDto.setRepair(vehicle.isRepair());
        if (this.rentRepository.findByVehicleAndDateTo(vehicle,null) != null) {
            vehicleListDto.setRentId(this.rentRepository.findByVehicleAndDateTo(vehicle,null).getId());
            vehicleListDto.setDriver(this.driverListService.findDriver(this.rentRepository.findByVehicleAndDateTo(vehicle,null).getDriver().getPesel()));
        }
        return vehicleListDto;
    }

    private Vehicle convertToVehicle(VehicleListDto vehicleListDto) {
        Vehicle vehicle = new Vehicle();
        vehicle.setBrand(vehicleListDto.getBrand());
        vehicle.setModel(vehicleListDto.getModel());
        vehicle.setRegisterNr(vehicleListDto.getRegisterNo());
        vehicle.setVin(vehicleListDto.getVin());
        vehicle.setRepair(vehicleListDto.isRepair());
        return vehicle;
    }
}
