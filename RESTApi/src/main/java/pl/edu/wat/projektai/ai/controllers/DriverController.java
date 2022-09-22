package pl.edu.wat.projektai.ai.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.wat.projektai.ai.dto.DriverDto;
import pl.edu.wat.projektai.ai.services.DriverListService;

import java.util.List;


@RestController
public class DriverController {

    private final DriverListService driverListService;

    @Autowired
    public DriverController(DriverListService driverListService) {
        this.driverListService = driverListService;
    }

    @GetMapping("/drivers")
    public ResponseEntity<List<DriverDto>> getDrivers() {
        List<DriverDto> drivers = driverListService.getDriverList();
        return new ResponseEntity<>(drivers, HttpStatus.OK);
    }

    @PostMapping("/delete-driver")
    public void deleteDriverByPesel(String pesel) {
        driverListService.deleteDriverByPesel(pesel);
    }

    @PostMapping("/add-driver")
    public ResponseEntity<DriverDto> saveDriver(@RequestBody DriverDto driver) {
        return new ResponseEntity<>(driverListService.addDriver(driver), HttpStatus.OK);
    }
}
