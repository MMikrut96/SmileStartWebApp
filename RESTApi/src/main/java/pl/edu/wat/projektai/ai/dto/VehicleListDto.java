package pl.edu.wat.projektai.ai.dto;

import lombok.Data;

@Data
public class VehicleListDto {
    String brand;
    String model;
    String registerNo;
    String vin;
    boolean repair;
    Long rentId;
    DriverDto driver;
}
