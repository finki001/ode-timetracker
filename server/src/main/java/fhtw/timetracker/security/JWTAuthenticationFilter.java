package fhtw.timetracker.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import fhtw.timetracker.model.LoginUser;
import fhtw.timetracker.model.Role;
import fhtw.timetracker.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            LoginUser user = new ObjectMapper().readValue(request.getInputStream(), LoginUser.class);
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
            if (user.getRole() == Role.ADMIN) {
                grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            }

            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), grantedAuthorities));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public AuthenticationManager getAuthenticationManager() {
        return authenticationManager;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) authResult.getPrincipal();

        String[] authorities = user.getAuthorities().stream().map(GrantedAuthority::getAuthority).toArray(String[]::new);

        String token = JWT.create()
                .withSubject(user.getUsername())
                .withArrayClaim("roles", authorities)
                .withClaim("name", userRepository.findUserByLogin(user.getUsername()).getFullName())
                .withExpiresAt(new Date(System.currentTimeMillis() + JwtConstants.EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(JwtConstants.SECRET));

        response.addHeader("Access-Control-Expose-Headers", JwtConstants.HEADER_STRING);
        response.addHeader(JwtConstants.HEADER_STRING, JwtConstants.TOKEN_PREFIX + token);
    }
}
