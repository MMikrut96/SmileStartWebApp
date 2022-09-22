package pl.edu.wat.projektai.ai.securityfiles;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import pl.edu.wat.projektai.ai.dto.UserDto;
import pl.edu.wat.projektai.ai.models.User;
import pl.edu.wat.projektai.ai.repositories.UserRepository;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthProvider extends DaoAuthenticationProvider {

    private final UserRepository userRepository;

    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {
        final User user = userRepository.findByLogin(auth.getName());
        if (user == null) {
            throw new BadCredentialsException("Invalid username or password");
        }

        final Authentication result = super.authenticate(auth);
        return new UsernamePasswordAuthenticationToken(new UserDto(user), result.getCredentials(), result.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
