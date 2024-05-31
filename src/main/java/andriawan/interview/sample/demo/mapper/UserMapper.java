package andriawan.interview.sample.demo.mapper;

import andriawan.interview.sample.demo.dto.UserDto;
import andriawan.interview.sample.demo.dto.UserStoreRequest;
import andriawan.interview.sample.demo.entity.User;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

@Mapper
public interface UserMapper {
  UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "password", ignore = true)
  User toUser(UserStoreRequest request);

  UserDto toUserDto(User user);

  List<UserDto> toDtoList(List<UserDto> persons);

  List<UserDto> toEntityList(List<User> personDtos);

  default Page<UserDto> toDtoPage(Page<User> persons) {
    return persons.map(this::toUserDto);
  }
}
