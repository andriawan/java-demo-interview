package andriawan.interview.sample.demo.repository;

import static org.assertj.core.api.Assertions.assertThat;

import andriawan.interview.sample.demo.entity.QUser;
import andriawan.interview.sample.demo.entity.User;
import java.util.List;
import net.datafaker.Faker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest(showSql = true)
public class UserRepositoryTest {

  @Autowired private UserRepository userRepository;
  private Faker faker = new Faker();
  private QUser userPredicate = QUser.user;

  @SuppressWarnings("null")
  @Test
  public void testSaveUser() {

    User user =
        User.builder()
            .name(faker.name().fullName())
            .isActive(faker.bool().bool())
            .address(faker.address().fullAddress())
            .build();
    User userSaved = userRepository.save(user);
    List<User> users = userRepository.findAll();
    assertThat(userSaved).isNotNull();
    assertThat(users).isNotEmpty();
    userRepository.deleteAll();
    users = userRepository.findAll();
    assertThat(users).isEmpty();
  }

  @SuppressWarnings("null")
  @Test
  public void testDslQuery() {

    String name = faker.name().fullName();
    String address = faker.address().fullAddress();
    Boolean isActive = faker.bool().bool();

    User user = User.builder().name(name).isActive(isActive).address(address).build();
    User userSaved = userRepository.save(user);
    Iterable<User> users =
        userRepository.findAll(
            userPredicate
                .name
                .eq(name)
                .and(userPredicate.isActive.eq(isActive))
                .and(userPredicate.address.eq(address)));
    assertThat(userSaved).isNotNull();
    assertThat(users).isNotEmpty();
    userRepository.deleteAll();
    users = userRepository.findAll();
    assertThat(users).isEmpty();
  }
}
