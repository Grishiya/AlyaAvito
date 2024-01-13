package ru.skypro.homework.mappers;

import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.RegisterDto;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.models.UserEntity;
@Component
public class UserMapper {

    public static UserEntity registerDtoInUserEntity(RegisterDto dto) {
        if (dto == null) {
            throw new NullPointerException("Tried to map null to UserEntity");
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(dto.getUsername());
        userEntity.setFirstName(dto.getFirstName());
        userEntity.setLastName(dto.getLastName());
        userEntity.setPhone(dto.getPhone());
        userEntity.setRole(dto.getRole());
        return userEntity;
    }

    public static UserDto userEntityToUserDto(UserEntity user) {
        if (user == null) {
            throw new NullPointerException("Tried to map null to UserDto");
        }
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getEmail());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setPhone(user.getPhone());
        userDto.setRole(user.getRole());
        userDto.setImage(user.getImage());
        return userDto;
    }

    public static UpdateUserDto userEntityToUpdateUser(UserEntity user) {
        if (user == null) {
            throw new NullPointerException("Tried to map null to UserEntity");
        }
        var updateUser = new UpdateUserDto();
        updateUser.setFirstName(user.getFirstName());
        updateUser.setLastName(user.getLastName());
        updateUser.setPhone(user.getPhone());
        return updateUser;
    }
}
