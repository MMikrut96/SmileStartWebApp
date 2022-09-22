package pl.edu.wat.projektai.ai.services;

import pl.edu.wat.projektai.ai.dto.RentDto;

import java.util.List;

public interface RentService {
    List<RentDto> findMyRents(String driverPesel);
    RentDto actualRent(String vehicleVin);
    RentDto addRent(RentDto rentDto);
    RentDto endRent(Long rentId);
}
