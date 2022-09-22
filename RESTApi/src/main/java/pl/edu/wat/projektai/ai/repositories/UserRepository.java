package pl.edu.wat.projektai.ai.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.edu.wat.projektai.ai.models.User;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByLoginAndPassword(String login,String password);
    User findByLogin(String login);
}
