package andriawan.interview.sample.demo;

import static org.assertj.core.api.Assertions.assertThat;

import andriawan.interview.sample.demo.controller.UserController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

  @Autowired UserController userController;

  @Test
  void contextLoads() {
    assertThat(userController).isNotNull();
  }
}
