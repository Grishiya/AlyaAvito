package ru.skypro.homework.mappers;

import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.models.UserEntity;
@Component
public class UserMapper {

    public  UserEntity toUser(UserDto userDto) {

        if (userDto == null) {
            throw new NullPointerException("Tried to map null to UserEntity");
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setId(userDto.getId());
        userEntity.setEmail(userDto.getEmail());
        userEntity.setFirstName(userDto.getFirstName());
        userEntity.setLastName(userDto.getLastName());
        userEntity.setPhone(userDto.getPhone());
        userEntity.setRole(userDto.getRole());
        userEntity.setImage(userDto.getImage());

        return userEntity;
    }

    public UserDto userToUserDTO(UserEntity user) {
        UserDto userDTO = new UserDto();
        userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setPhone(user.getPhone());
        userDTO.setRole(user.getRole());
        userDTO.setImage(user.getImage());
        return userDTO;
    }
}
