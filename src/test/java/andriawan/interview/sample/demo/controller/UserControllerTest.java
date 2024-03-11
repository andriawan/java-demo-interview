package andriawan.interview.sample.demo.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

import andriawan.interview.sample.demo.repository.UserRepository;
import andriawan.interview.sample.demo.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(controllers = UserController.class)
public class UserControllerTest {
  @MockBean UserRepository userRepository;
  @Autowired private MockMvc mockMvc;
  @MockBean UserService userService;

  @SuppressWarnings("null")
  @Test
  void evaluatesPageableParameter() throws Exception {

    mockMvc
        .perform(
            MockMvcRequestBuilders.get("/user")
                .param("page", "5")
                .param("size", "10")
                .param("sort", "id,desc")
                .param("sort", "name,asc"))
        .andExpect(MockMvcResultMatchers.status().isOk());

    ArgumentCaptor<Pageable> pageableCaptor = ArgumentCaptor.forClass(Pageable.class);
    verify(userService).getUsers(pageableCaptor.capture());
    PageRequest pageable = (PageRequest) pageableCaptor.getValue();

    assertThat(pageable).matches(page -> page.getPageNumber() == 5);
    assertThat(pageable).matches(page -> page.getPageSize() == 10);
    assertThat(pageable).matches(page -> page.getSort().getOrderFor("name").isAscending());
    assertThat(pageable).matches(page -> page.getSort().getOrderFor("id").isDescending());
  }
}
