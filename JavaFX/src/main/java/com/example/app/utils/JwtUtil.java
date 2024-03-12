package com.example.app.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class JwtUtil {

    private static final String SECRET_KEY = "secret-nutrinet";
    private static final Set<String> BLACKLIST = new HashSet<>();

    public String createJwt(String subject, long ttlMillis) {
        Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + ttlMillis);

        return JWT.create()
                .withJWTId(UUID.randomUUID().toString())
                .withSubject(subject)
                .withIssuedAt(now)
                .withExpiresAt(expiryDate)
                .withIssuer("your-issuer")
                .sign(algorithm);
    }

    public boolean verifyJwt(String jwtToken) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            JWTVerifier verifier = JWT.require(algorithm).build();
            verifier.verify(jwtToken);
            return !isBlacklisted(jwtToken); // Token is valid if not blacklisted
        } catch (JWTVerificationException exception) {
            return false; // Token is invalid
        }
    }

    public String getUsernameFromToken(String jwtToken) {
        DecodedJWT decodedJWT = JWT.decode(jwtToken);
        return decodedJWT.getSubject();
    }

    public void blacklistToken(String jwtToken) {
        BLACKLIST.add(jwtToken);
    }

    public boolean isBlacklisted(String jwtToken) {
        return BLACKLIST.contains(jwtToken);
    }
}
