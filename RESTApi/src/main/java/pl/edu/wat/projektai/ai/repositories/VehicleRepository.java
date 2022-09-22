package pl.edu.wat.projektai.ai.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.edu.wat.projektai.ai.models.Vehicle;

import java.util.ArrayList;

public interface VehicleRepository extends CrudRepository<Vehicle,Long> {
    Vehicle findByVin(String vin);
    ArrayList<Vehicle> findAll();
    void delete(Vehicle vehicle);
}
