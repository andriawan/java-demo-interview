package andriawan.interview.sample.demo.controller;

import andriawan.interview.sample.demo.dto.UserDto;
import andriawan.interview.sample.demo.dto.UserStoreRequest;
import andriawan.interview.sample.demo.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired private UserService userService;

  @GetMapping()
  public @ResponseBody ResponseEntity<Page<UserDto>> getUser(Pageable pageable)
      throws JsonProcessingException {
    Page<UserDto> data = this.userService.getUsers(pageable);
    return ResponseEntity.ok(data);
  }

  @PostMapping()
  public @ResponseBody ResponseEntity<UserDto> saveUser(@RequestBody UserStoreRequest dto)
      throws JsonProcessingException {
    UserDto data = this.userService.saveData(dto);
    return ResponseEntity.status(HttpStatus.CREATED).body(data);
  }
}
