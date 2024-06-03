package andriawan.interview.sample.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class UserStoreRequest {
  @NotEmpty(message = "${validatedValue} required")
  private String name;

  @Email(regexp = ".+[@].+[\\.].+")
  private String email;

  @NotEmpty private String password;
  private Boolean isActive;
  private String address;
  private Integer age;
  private BigDecimal salary;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
}
