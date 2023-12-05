package com.modsen.library.registration.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.modsen.library.registration.dto.UserDTO;
import com.modsen.library.registration.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtProvider {

    private static final int SESSION_TIMEOUT = 30;
    private static final String JWT_SUBJECT = "User details";
    private static final String JWT_ISSUER = "user";

    private final UserService userService;

    @Value("jwt.secret")
    private String jwtSecret;

    public String generateToken(final String email) {
        final UserDTO user = userService.getByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        final Date expirationDate = Date.from(ZonedDateTime.now().plusMinutes(SESSION_TIMEOUT).toInstant());

        return JWT.create()
                .withKeyId(user.getId().toString())
                .withSubject(JWT_SUBJECT)
                .withIssuedAt(new Date())
                .withIssuer(JWT_ISSUER)
                .withExpiresAt(expirationDate)
                .sign(Algorithm.HMAC256(jwtSecret));
    }

    public String validateTokenAndRetrieveClaim(final String token) throws JWTVerificationException {
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(jwtSecret))
                .withSubject(JWT_SUBJECT)
                .withIssuer(JWT_ISSUER)
                .build();

        DecodedJWT jwt = jwtVerifier.verify(token);

        return jwt.getKeyId();
    }

}
