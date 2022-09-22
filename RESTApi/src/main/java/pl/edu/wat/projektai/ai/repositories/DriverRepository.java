package pl.edu.wat.projektai.ai.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.edu.wat.projektai.ai.models.Driver;

import java.util.ArrayList;


public interface DriverRepository extends CrudRepository<Driver, Long> {
    Driver findByPesel(String pesel);
    ArrayList<Driver> findAll();
    void delete(Driver driver);
}

