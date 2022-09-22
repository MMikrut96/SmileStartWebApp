package pl.edu.wat.projektai.ai.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.wat.projektai.ai.dto.RentDto;
import pl.edu.wat.projektai.ai.services.RentService;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RentController {

    private final RentService rentService;

    @Autowired
    public RentController(RentService rentService) {
        this.rentService = rentService;
    }


    @GetMapping("/my-rents")
    public ResponseEntity<List<RentDto>> getMyRents(String driverPesel) {
        List<RentDto> rents = new ArrayList<>(rentService.findMyRents(driverPesel));
        return new ResponseEntity<>(rents, HttpStatus.OK);
    }

    @GetMapping("/vehicle-actual-rent")
    public ResponseEntity<RentDto> actualRent(String vehicleVin) {
        return new ResponseEntity<>(rentService.actualRent(vehicleVin), HttpStatus.OK);
    }

    @PostMapping("/add-rent")
    public ResponseEntity<RentDto> addRent(@RequestBody RentDto rent){
        return new ResponseEntity<>(rentService.addRent(rent), HttpStatus.OK);
    }

    @PostMapping("/end-rent")
    public void endRent(Long rentId){
        rentService.endRent(rentId);
    }
}
