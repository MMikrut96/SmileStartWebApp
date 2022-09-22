package pl.edu.wat.projektai.ai.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class ProfitDto {
    Date date;
    Long income;
    Long rentCost;
    String driverPesel;
}
