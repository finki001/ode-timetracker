package fhtw.timetracker.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
    private final UserDetailsService userDetailsService;


    public JWTAuthorizationFilter(AuthenticationManager authenticationManager, UserDetailsService userDetailsService) {
        super(authenticationManager);
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader(JwtConstants.HEADER_STRING);
        if (header == null || !header.startsWith(JwtConstants.TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }
        SecurityContextHolder.getContext().setAuthentication(getAuthentication(request));
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(JwtConstants.HEADER_STRING);
        if (token != null) {
            String login = JWT.require(Algorithm.HMAC512(JwtConstants.SECRET))
                    .build()
                    .verify(token.replace(JwtConstants.TOKEN_PREFIX, ""))
                    .getSubject();
            if (login != null) {
                return new UsernamePasswordAuthenticationToken(login, null, userDetailsService.loadUserByUsername(login).getAuthorities());
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
}
