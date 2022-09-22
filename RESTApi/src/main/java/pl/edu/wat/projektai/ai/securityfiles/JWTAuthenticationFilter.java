package pl.edu.wat.projektai.ai.securityfiles;

import com.auth0.jwt.JWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import pl.edu.wat.projektai.ai.dto.UserDto;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    public static final String SECRET = "jc#$#343rnfskdjuhf/*/+jsd4####grh4&^(935fsd434g";
    public static final long EXPIRATION_TIME = 864_000_000;
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String EXPOSE_HEADERS = "Access-Control-Expose-Headers";
    public static final String AUTHORIZATION_HEADER = "Authorization";


    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        setAuthenticationManager(authenticationManager);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException {
        String token = JWT.create()
                .withSubject(((UserDto) auth.getPrincipal()).getLogin())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(HMAC512(SECRET.getBytes()));
        res.addHeader(EXPOSE_HEADERS, AUTHORIZATION_HEADER);
        res.addHeader(AUTHORIZATION_HEADER, TOKEN_PREFIX + token);

        ((UserDto) auth.getPrincipal()).setToken(TOKEN_PREFIX + token);

        res.setContentType("application/json;charset=UTF-8");
        res.getWriter().write(new ObjectMapper().writeValueAsString(auth.getPrincipal()));
        res.getWriter().flush();
        res.getWriter().close();
    }
}
