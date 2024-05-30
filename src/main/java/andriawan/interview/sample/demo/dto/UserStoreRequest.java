package andriawan.interview.sample.demo.dto;

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
  private String name;
  private Boolean isActive;
  private String address;
  private Integer age;
  private BigDecimal salary;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
}
