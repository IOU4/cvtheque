package ma.cvtheque.config;

import java.time.Instant;
import java.util.Date;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

  @Value("${spring.security.singInKey}")
  private String singInKey;

  public String extractUsername(String token) {
    return extractClaim(token, Claims::getSubject);
  }

  public <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
    return claimResolver.apply(excractAllClaims(token));
  }

  private Claims excractAllClaims(String token) {
    return Jwts
        .parserBuilder()
        .setSigningKey(Keys.hmacShaKeyFor(Decoders.BASE64.decode(singInKey)))
        .build()
        .parseClaimsJws(token)
        .getBody();
  }

  public boolean isTokenValid(String token, UserDetails userDetails) {
    return userDetails.getUsername() == extractUsername(token) && !isTokenExpaired(token);
  }

  private boolean isTokenExpaired(String token) {
    return Instant.now().compareTo(extractClaim(token, Claims::getExpiration).toInstant()) > 0;
  }

  public String generateToken(UserDetails userDetails) {
    Date now = Date.from(Instant.now());
    Date expire = Date.from(Instant.now().plusSeconds(36000L));
    return Jwts.builder()
        .setIssuer("self")
        .setIssuedAt(now)
        .setExpiration(expire)
        .setSubject(userDetails.getUsername())
        .signWith(Keys.hmacShaKeyFor(Decoders.BASE64.decode(singInKey)), SignatureAlgorithm.HS256)
        .compact();
  }

}
