package ma.cvtheque.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ma.cvtheque.config.JwtService;
import ma.cvtheque.student.StudentRepository;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

  private final StudentRepository studentRepository;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  public String login(Login credentials) {
    authenticationManager
        .authenticate(new UsernamePasswordAuthenticationToken(credentials.email(), credentials.password()));
    var user = studentRepository.findByEmail(credentials.email())
        .orElseThrow(() -> new UsernameNotFoundException("user: " + credentials.email() + " not found"));
    var token = jwtService.generateToken(user);
    return token;
  }
}
