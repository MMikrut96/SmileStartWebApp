package pl.edu.wat.projektai.ai.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class RentDto {
    Date dateFrom;
    VehicleListDto vehicle;
    String driverPesel;
    Long cost;
}
