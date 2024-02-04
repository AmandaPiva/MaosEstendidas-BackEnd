package com.tcc.maosestendidas.JWT.Service;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Map;
import io.jsonwebtoken.Claims;


public interface JwtService {
    String extractUserName(String token);
    String generateToken(UserDetails userDetails);
    String generateToken(Map<String, String> extraClaims, UserDetails userDetails);

    boolean isTokenValid(String token, UserDetails userDetails);

    Claims extractAllClaims(String token);

}
