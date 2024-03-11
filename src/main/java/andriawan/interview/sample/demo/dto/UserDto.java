package andriawan.interview.sample.demo.dto;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class UserDto {
  private String name;
  private Boolean isActive;
  private Integer age;
  private BigDecimal salary;
}
