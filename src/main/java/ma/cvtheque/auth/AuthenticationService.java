package ma.cvtheque.auth;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ma.cvtheque.config.JwtService;
import ma.cvtheque.student.StudentRepository;
import ma.cvtheque.util.NotFoundException;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

  private final StudentRepository studentRepository;
  private final JwtService jwtService;
  private final PasswordEncoder passwordEncoder;

  public String login(Login credentials) {
    var user = studentRepository.findByEmail(credentials.email())
        .orElseThrow(() -> new UsernameNotFoundException("user: " + credentials.email() + " not found"));
    if (!passwordEncoder.matches(credentials.password(), user.getPassword()))
      throw new NotFoundException("password don't match");
    var token = jwtService.generateToken(user);
    return token;
  }
}
