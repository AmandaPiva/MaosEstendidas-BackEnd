package com.tcc.maosestendidas.JWT.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.Claims;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


@Service
public class JwtServiceImpl implements JwtService {

    //DEVE SER IDENTICA A FORMA QUE ESTA NO ARQUIVO application.yml
    @Value("${token.signing.key}")
    private String jwtSigningKey;

    @Override
    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolvers) {
        final Claims claims = extractAllClaims(token);
        return claimsResolvers.apply(claims);
    }

    @Override
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    private final Logger log = LoggerFactory.getLogger(JwtServiceImpl.class);

    public String generateToken(Map<String, String> extraClaims, UserDetails userDetails) {
        log.info("Gerando token com chave: " + jwtSigningKey + "Claims: " + extraClaims);
        return Jwts.builder().setClaims(extraClaims).setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis())) //data atual
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))//data de expiração
                .signWith(getSigningKey(), SignatureAlgorithm.HS256) //assinando o token
                .compact();
    }

    @Override
    public boolean isTokenValid(String token, UserDetails userDetails){
        final String userName = extractUserName(token);
        return (userName.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }


    private boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    //Obtém a data de expiração do token
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }


    //este método extrai todas as reinivindicações do token, ele utiliza o contrutor para criar
    //um parse de tokens e depois obter as reinivindicações do corpo deles
    @Override
    public Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(getSigningKey()).build().parseClaimsJws(token)
                .getBody();
    }

    //este método converte a chave que está no formato string com a base 64 para o formato da chave que verifica os tokens
    //decodificando
    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSigningKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
