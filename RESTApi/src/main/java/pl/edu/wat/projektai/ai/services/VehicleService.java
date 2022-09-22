package pl.edu.wat.projektai.ai.services;

import pl.edu.wat.projektai.ai.dto.VehicleListDto;
import pl.edu.wat.projektai.ai.models.Vehicle;

import java.util.List;

public interface VehicleService {
    VehicleListDto findVehicle(String vin);
    Vehicle findByVin(String vin);
    List<VehicleListDto> getVehicleList();
    void deleteVehicle(VehicleListDto vehicleDto);
    VehicleListDto addVehicle(VehicleListDto vehicleDto);
    VehicleListDto saveVehicle(VehicleListDto vehicle);
}
