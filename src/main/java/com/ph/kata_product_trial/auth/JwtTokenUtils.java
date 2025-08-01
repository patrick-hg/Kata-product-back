package com.ph.kata_product_trial.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;
import java.util.Date;
import java.util.HashMap;

@Component
public class JwtTokenUtils {
    private final Logger LOG = LoggerFactory.getLogger(JwtTokenUtils.class);

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expirationSec}")
    private Long jwtExpirationSec;

    public String generateToken(String email) {
        String generatedToken = generateTokenWithAuth0(email);
        LOG.info("Token: {}", generatedToken);
        return generatedToken;
    }

    public String validateTokenAndRetrieveSubject(String token) {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(jwtSecret))
                .withSubject("User Details")
                .withIssuer("PRODUCT-TRIAL-APP")
                .build();
        DecodedJWT jwt = verifier.verify(token);
        return jwt.getClaim("username").asString();
    }

    private String doGenerateToken(String username) {
        return Jwts.builder()
                .signWith(Keys.hmacShaKeyFor(jwtSecret.getBytes(Charset.forName("UTF-8"))), SignatureAlgorithm.HS512)
                .setIssuer("PRODUCT-TRIAL-APP")
                .setSubject(username)
                .setClaims(new HashMap<>())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationSec * 1000))
                .compact();
    }

    private String generateTokenWithAuth0(String username) {
        return JWT.create()
                .withSubject("User Details")
                .withClaim("username", username)
                .withIssuedAt(new Date())
                .withIssuer("PRODUCT-TRIAL-APP")
                .sign(Algorithm.HMAC256(jwtSecret));
    }
}
