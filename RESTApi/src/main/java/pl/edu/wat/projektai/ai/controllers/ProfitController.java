package pl.edu.wat.projektai.ai.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.wat.projektai.ai.dto.ProfitDto;
import pl.edu.wat.projektai.ai.services.ProfitService;

import java.util.List;

@RestController
public class ProfitController {

    private final ProfitService profitService;

    @Autowired
    public ProfitController(ProfitService profitService) {
        this.profitService = profitService;
    }

    @GetMapping("/my-profits")
    public ResponseEntity<List<ProfitDto>> getProfitList(String driverPesel){
        List<ProfitDto> profits = profitService.findProfitByDriver(driverPesel);
        return new ResponseEntity<>(profits, HttpStatus.OK);
    }

    @PostMapping("/add-profit")
    public ResponseEntity<ProfitDto> addProfit(@RequestBody ProfitDto profit){
        return new ResponseEntity<>(profitService.addProfit(profit), HttpStatus.OK);
    }

    @PostMapping("/save-profit")
    public ResponseEntity<ProfitDto> saveProfit(@RequestBody ProfitDto profit){
        return new ResponseEntity<>(profitService.saveProfit(profit), HttpStatus.OK);
    }

}
