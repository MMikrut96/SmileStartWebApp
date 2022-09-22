package pl.edu.wat.projektai.ai.services.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wat.projektai.ai.dto.ProfitDto;
import pl.edu.wat.projektai.ai.dto.RentDto;
import pl.edu.wat.projektai.ai.exceptions.ProfitAlreadyExistException;
import pl.edu.wat.projektai.ai.models.Driver;
import pl.edu.wat.projektai.ai.models.Profit;
import pl.edu.wat.projektai.ai.repositories.DriverRepository;
import pl.edu.wat.projektai.ai.repositories.ProfitRepository;
import pl.edu.wat.projektai.ai.services.ProfitService;
import pl.edu.wat.projektai.ai.services.RentService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfitServiceImpl implements ProfitService {

    private final ProfitRepository profitRepository;
    private final DriverRepository driverRepository;
    private final RentService rentService;

    @Autowired
    ProfitServiceImpl(ProfitRepository profitRepository, DriverRepository driverRepository, RentService rentService) {
        this.driverRepository = driverRepository;
        this.profitRepository = profitRepository;
        this.rentService = rentService;
    }

    @Override
    public List<ProfitDto> findProfitByDriver(String driverPesel) {
        Driver driver = this.driverRepository.findByPesel(driverPesel);
        List<Profit> profits = this.profitRepository.findByDriver(driver);
        return profits.stream().map(profit -> convertProfitToDto(profit)).collect(Collectors.toList());
    }

    @Override
    public ProfitDto addProfit(ProfitDto profitDto) {
        if (profitRepository.findByDriverAndDate(driverRepository.findByPesel(profitDto.getDriverPesel()), profitDto.getDate()) == null) {
            return convertProfitToDto(profitRepository.save(convertProfitFromDto(profitDto)));
        } else
            throw new ProfitAlreadyExistException("Profit za ten dzien jest juz zarejestrowany");
    }

    @Override
    public ProfitDto saveProfit(ProfitDto profitDto){
        Profit profit;
        profit = convertProfitFromDto(profitDto);
        profit.setId(profitRepository.findByDriverAndDate(driverRepository.findByPesel(profitDto.getDriverPesel()), profitDto.getDate()).getId());
        return convertProfitToDto(profitRepository.save(profit));
    }

    private ProfitDto convertProfitToDto(Profit profit) {
        ProfitDto profitDto = new ProfitDto();
        profitDto.setDate(profit.getDate());
        profitDto.setIncome(profit.getIncome());
        profitDto.setDriverPesel(profit.getDriver().getPesel());
        List<RentDto> rents = new ArrayList<>(rentService.findMyRents(profit.getDriver().getPesel()));
        float count = 0;
        for (RentDto r : rents) {
            count += r.getCost();
        }
        profitDto.setRentCost((long) count);
        return profitDto;
    }

    private Profit convertProfitFromDto(ProfitDto profitDto) {
        Profit profit = new Profit();
        profit.setDate(profitDto.getDate());
        profit.setDriver(driverRepository.findByPesel(profitDto.getDriverPesel()));
        profit.setIncome(profitDto.getIncome());
        return profit;
    }
}
