package andriawan.interview.sample.demo.controller;

import andriawan.interview.sample.demo.dto.LoginRequest;
import andriawan.interview.sample.demo.dto.Token;
import andriawan.interview.sample.demo.dto.UserDto;
import andriawan.interview.sample.demo.dto.UserStoreRequest;
import andriawan.interview.sample.demo.service.JWTService;
import andriawan.interview.sample.demo.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/auth")
public class AuthController {

  @Autowired private JWTService jwtService;

  @Autowired private UserService userService;

  @Autowired private AuthenticationManager authorizationManager;

  @PostMapping("/login")
  public @ResponseBody ResponseEntity<Token> login(@RequestBody LoginRequest loginRequest)
      throws JsonProcessingException {
    Token token;
    Authentication auth =
        authorizationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginRequest.username(), loginRequest.password()));
    if (auth.isAuthenticated()) {
      token = new Token(jwtService.generateToken(loginRequest.username()), null);
    } else {
      throw new UsernameNotFoundException("invalid user request..!!");
    }
    return ResponseEntity.ok(token);
  }

  @PostMapping("/register")
  public @ResponseBody ResponseEntity<UserDto> register(
      @Valid @RequestBody UserStoreRequest request) throws JsonProcessingException {
    UserDto data = this.userService.saveData(request);
    return ResponseEntity.status(HttpStatus.CREATED).body(data);
  }
}
