package ru.skypro.homework.service;

import ru.skypro.homework.models.UserEntity;

public interface UserService {

    UserEntity create(UserEntity userEntity);
    UserEntity read(Integer id);
    UserEntity update(UserEntity userEntity);
    UserEntity delete(Integer id);
}
