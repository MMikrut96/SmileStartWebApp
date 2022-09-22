package pl.edu.wat.projektai.ai.services.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.edu.wat.projektai.ai.exceptions.UserAlreadyExistException;
import pl.edu.wat.projektai.ai.models.User;
import pl.edu.wat.projektai.ai.repositories.UserRepository;
import pl.edu.wat.projektai.ai.services.AuthorizationService;


@Service("userDetailsService")
public class AuthorizationServiceImpl implements AuthorizationService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(final String login) throws UsernameNotFoundException {
        final User user = userRepository.findByLogin(login);
        if (user == null) {
            throw new UsernameNotFoundException("Nie znaleziono użytkownika o loginie: " + login);
        }

        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), true, true, true, true, AuthorityUtils.NO_AUTHORITIES);
    }

    @Override
    public User addUser(String login) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(11);
        if (userRepository.findByLogin(login) == null) {
            User user = new User();
            user.setLogin(login);
            user.setPassword(encoder.encode("user"));
            return userRepository.save(user);
        } else
            throw new UserAlreadyExistException("Użytkownik o podanym loginie już istnieje");
    }


}
