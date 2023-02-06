package ma.cvtheque.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationResource {
  private final AuthenticationService authService;

  @PostMapping(value = "/login")
  public ResponseEntity<String> login(@RequestBody Login credentials) {
    return ResponseEntity.ok(authService.login(credentials));
  }

}
