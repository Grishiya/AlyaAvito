package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.models.UserEntity;

import java.io.IOException;

public interface UserService {

    UserDto getUser(Integer id);

    UserEntity getUserEntity(Integer id);

    UpdateUserDto updateUser(UpdateUserDto userDto);

    void updatePassword(NewPasswordDto newPasswordDto);

    void updateAvatar(Integer userId, MultipartFile imageFile) throws IOException;

    UserEntity readFromDb(Integer userId);
}
