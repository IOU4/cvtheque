package ma.cvtheque.config;

import java.security.Key;
import java.time.Instant;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

  private final Logger logger = LoggerFactory.getLogger(JwtService.class);

  private Key singInKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

  public String extractUsername(String token) {
    try {
      var email = Jwts.parserBuilder().setSigningKey(singInKey).build().parseClaimsJws(token).getBody().getSubject();
      return email;
    } catch (JwtException ex) {
      logger.error("invalid jwt token: " + ex.getMessage());
      return null;
    }
  }

  public String generateToken(UserDetails userDetails) {
    Date now = Date.from(Instant.now());
    Date expire = Date.from(Instant.now().plusSeconds(36000L));
    return Jwts.builder()
        .setIssuer("self")
        .setIssuedAt(now)
        .setExpiration(expire)
        .setSubject(userDetails.getUsername())
        .signWith(singInKey)
        .compact();
  }

}
