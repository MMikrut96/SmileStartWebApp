package pl.edu.wat.projektai.ai.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.wat.projektai.ai.dto.VehicleListDto;
import pl.edu.wat.projektai.ai.services.VehicleService;

import java.util.List;

@RestController
public class VehicleController {

    private final VehicleService vehicleService;

    @Autowired
    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping("/vehicles")
    public ResponseEntity<List<VehicleListDto>> getVehicles() {
        List<VehicleListDto> vehicles = vehicleService.getVehicleList();
        return new ResponseEntity<>(vehicles, HttpStatus.OK);
    }

    @PostMapping("/delete-vehicle")
    public void deleteVehicle(@RequestBody VehicleListDto vehicle) {
        vehicleService.deleteVehicle(vehicle);
    }

    @PostMapping("/add-vehicle")
    public ResponseEntity<VehicleListDto> addVehicle(@RequestBody VehicleListDto vehicle) {
        return new ResponseEntity<>(vehicleService.addVehicle(vehicle), HttpStatus.OK);
    }

    @PostMapping("/save-vehicle")
    public ResponseEntity<VehicleListDto> saveVehicle(@RequestBody VehicleListDto vehicle) {
        return new ResponseEntity<>(vehicleService.saveVehicle(vehicle), HttpStatus.OK);
    }
}
