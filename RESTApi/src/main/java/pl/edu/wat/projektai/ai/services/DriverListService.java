package pl.edu.wat.projektai.ai.services;

import pl.edu.wat.projektai.ai.dto.DriverDto;

import java.util.List;

public interface DriverListService {
    DriverDto findDriver(String pesel);
    List<DriverDto> getDriverList();
    void deleteDriverByPesel(String pesel);
    DriverDto addDriver(DriverDto driver);
}
