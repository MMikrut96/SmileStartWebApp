package pl.edu.wat.projektai.ai.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import pl.edu.wat.projektai.ai.models.User;

public interface AuthorizationService extends UserDetailsService {
    public User addUser(String login);
}
