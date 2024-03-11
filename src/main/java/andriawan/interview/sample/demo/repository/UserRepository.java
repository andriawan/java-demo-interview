package andriawan.interview.sample.demo.repository;

import andriawan.interview.sample.demo.entity.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  List<User> findByName(String lastName);

  User findById(long id);
}
