package br.com.terracota.infra.security.jwt;

import br.com.terracota.application.dto.output.AuthLoginOutput;
import br.com.terracota.infra.security.user.CustomUserDetails;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@Component
public class JwtUtils {

    @Value("${trc.auth.jwt.secret}")
    private String jwtSecret;

    @Value("${trc.auth.jwt.expiration-time}")
    private Integer jwtExpirationTime;

    public AuthLoginOutput generateTokenForUser(Authentication authentication){
        CustomUserDetails principal = (CustomUserDetails) authentication.getPrincipal();
        List<String> roles = principal.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        Instant now = Instant.now();
        Instant validity = now.plusMillis(this.jwtExpirationTime);

        String jwt = Jwts.builder()
                .subject(principal.getUsername())
                .claim("id", principal.getId())
                .claim("roles", roles)
                .issuedAt(Date.from(now))
                .expiration(Date.from(validity))
                .signWith(key())
                .compact();

        return new AuthLoginOutput(
                principal.getUsername(),
                Boolean.TRUE,
                now,
                validity,
                jwt
        );
    }

    public String getUsernameFromToken(final String token){
        return Jwts.parser()
                .verifyWith(key())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public boolean validateToken(final String token){
        try {
            Jwts.parser()
                    .verifyWith(key())
                    .build()
                    .parseSignedClaims(token);
            return true;
        }catch (JwtException | IllegalArgumentException e){
            throw new JwtException(e.getMessage());
        }
    }

    private SecretKey key(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(this.jwtSecret));
    }
}
