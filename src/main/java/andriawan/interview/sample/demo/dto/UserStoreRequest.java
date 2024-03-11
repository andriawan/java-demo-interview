package andriawan.interview.sample.demo.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserStoreRequest {
  private String name;
  private Boolean isActive;
  private String address;
  private BigDecimal salary;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
}
