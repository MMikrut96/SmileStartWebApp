package pl.edu.wat.projektai.ai.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.edu.wat.projektai.ai.models.Driver;
import pl.edu.wat.projektai.ai.models.Profit;

import java.util.ArrayList;
import java.util.Date;

public interface ProfitRepository extends CrudRepository<Profit, Long> {
    ArrayList<Profit> findByDriver(Driver driver);
    Profit findByDriverAndDate(Driver driver, Date date);
}
