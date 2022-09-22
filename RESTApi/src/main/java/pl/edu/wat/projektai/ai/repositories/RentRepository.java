package pl.edu.wat.projektai.ai.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.edu.wat.projektai.ai.models.Driver;
import pl.edu.wat.projektai.ai.models.Rent;
import pl.edu.wat.projektai.ai.models.Vehicle;

import java.util.ArrayList;
import java.util.Date;

public interface RentRepository extends CrudRepository<Rent, Long> {
    ArrayList<Rent> findByDriver(Driver driver);
    ArrayList<Rent> findAll();
    ArrayList<Rent> findByVehicle(Vehicle vehicle);
    Rent findByVehicleAndDateTo(Vehicle vehicle, Date date);
    ArrayList<Rent> findByDriverAndDateTo(Driver driver, Date date);
}
