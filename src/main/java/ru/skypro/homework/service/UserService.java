package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.models.UserEntity;

public interface UserService {

    UserDto getUser();

    UserEntity getUserEntity();

    UpdateUserDto updateUser(UpdateUserDto userDto);

    void updatePassword(NewPasswordDto newPasswordDto);

    void updateAvatar(MultipartFile file);

    Integer getUserId();

    boolean featuresRole(Integer id);
}
