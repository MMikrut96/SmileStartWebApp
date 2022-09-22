package pl.edu.wat.projektai.ai.services;

import pl.edu.wat.projektai.ai.dto.ProfitDto;

import java.util.List;

public interface ProfitService {
    List<ProfitDto> findProfitByDriver(String driverPesel);
    ProfitDto addProfit(ProfitDto profitDto);
    ProfitDto saveProfit(ProfitDto profitDto);
}
