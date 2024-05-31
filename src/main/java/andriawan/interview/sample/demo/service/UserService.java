package andriawan.interview.sample.demo.service;

import andriawan.interview.sample.demo.dto.UserDto;
import andriawan.interview.sample.demo.dto.UserStoreRequest;
import andriawan.interview.sample.demo.entity.User;
import andriawan.interview.sample.demo.mapper.UserMapper;
import andriawan.interview.sample.demo.repository.UserRepository;
import java.time.LocalDateTime;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  public final Integer ADULT_TRESHOLD = 25;

  @Autowired UserRepository userRepository;
  @Autowired PasswordEncoder passwordEncoder;

  public boolean isAdult(Integer age) {
    return age >= ADULT_TRESHOLD;
  }

  public Page<UserDto> getUsers(@NonNull Pageable page) {
    return UserMapper.INSTANCE.toDtoPage(this.userRepository.findAll(page));
  }

  public UserDto saveData(@NonNull UserStoreRequest user) {
    User data = UserMapper.INSTANCE.toUser(user);
    data.setCreatedAt(LocalDateTime.now());
    data.setPassword(passwordEncoder.encode(user.getPassword()));
    return UserMapper.INSTANCE.toUserDto(this.userRepository.save(data));
  }
}
