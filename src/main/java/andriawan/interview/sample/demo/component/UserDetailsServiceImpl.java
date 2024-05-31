package andriawan.interview.sample.demo.component;

import andriawan.interview.sample.demo.entity.User;
import andriawan.interview.sample.demo.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

  @Autowired private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    log.debug("Entering in loadUserByUsername Method...");
    User user = userRepository.findByEmail(username);
    if (user == null) {
      log.error("Username not found: " + username);
      throw new UsernameNotFoundException("could not found user..!!");
    }
    log.info("User Authenticated Successfully..!!!");
    return user;
  }
}
