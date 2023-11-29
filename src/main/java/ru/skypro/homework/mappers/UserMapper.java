package ru.skypro.homework.mappers;

import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.models.UserEntity;

public class UserMapper {

    public static UserEntity toUser(UserDto userDto) {

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

    public static UserDto fromUser(UserEntity userEntity) {
        if (userEntity == null) {
            throw new NullPointerException("Tried to map null to UserEntity");
        }

        UserDto userDto = new UserDto();

        userDto.setId(userEntity.getId());
        userDto.setEmail(userEntity.getEmail());
        userDto.setFirstName(userEntity.getFirstName());
        userDto.setLastName(userEntity.getLastName());
        userDto.setPhone(userEntity.getPhone());
        userDto.setRole(userEntity.getRole());
        userDto.setImage(userEntity.getImage());

        return userDto;

    }
}
