package ru.skypro.homework.service;

import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.models.UserEntity;

public interface UserService {

    UserDto read(Integer id);
    UserEntity update(UserEntity userEntity);
}
