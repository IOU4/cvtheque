package ma.cvtheque.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import lombok.RequiredArgsConstructor;
import ma.cvtheque.student.StudentRepository;

@RequiredArgsConstructor
@Configuration
public class AppConfig {

  private final StudentRepository studentRepository;

  @Bean
  UserDetailsService userDetailsService() {
    return username -> studentRepository.findByEmail(username)
        .orElseThrow(() -> new UsernameNotFoundException("user :" + username + " not found"));
  }
}
