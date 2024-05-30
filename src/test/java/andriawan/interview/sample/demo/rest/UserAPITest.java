package andriawan.interview.sample.demo.rest;

import static org.assertj.core.api.Assertions.assertThat;

import andriawan.interview.sample.demo.component.FakerComponent;
import andriawan.interview.sample.demo.dto.UserStoreRequest;
import andriawan.interview.sample.demo.repository.UserRepository;
import java.math.BigDecimal;
import java.math.RoundingMode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class UserAPITest {

  @LocalServerPort private int port;

  @Autowired private TestRestTemplate restTemplate;

  @Autowired private UserRepository userRepository;

  @Autowired private FakerComponent faker;

  private String url;

  @BeforeEach
  public void setUp() {
    this.url = "http://localhost:".concat(String.valueOf(port));
    userRepository.deleteAll();
  }

  public UserStoreRequest createUser() {
    return UserStoreRequest.builder()
        .isActive(true)
        .name(faker.name().nameWithMiddle())
        .address(faker.address().fullAddress())
        .salary(
            new BigDecimal(faker.number().randomNumber(5, false)).setScale(2, RoundingMode.HALF_UP))
        .build();
  }

  @Test
  void listShouldContainSavedUser() throws Exception {
    String result =
        this.restTemplate.postForObject(this.url.concat("/user"), createUser(), String.class);
    String list = this.restTemplate.getForObject(this.url.concat("/user"), String.class);
    assertThat(result).isSubstringOf(list);
  }
}
