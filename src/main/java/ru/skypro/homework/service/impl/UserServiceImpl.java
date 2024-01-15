package ru.skypro.homework.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.RoleDto;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.mappers.UserMapper;
import ru.skypro.homework.models.OwnedEntity;
import ru.skypro.homework.models.UserEntity;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.security.SecurityUser;
import ru.skypro.homework.service.PhotoService;
import ru.skypro.homework.service.UserService;

import java.io.IOException;
import java.util.Objects;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final PhotoService photoService;

    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           PhotoService photoService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.photoService = photoService;
    }

    @Override
    public UserDto getUser() {

        return UserMapper.userEntityToUserDto(getUserEntity());
    }

    @Override
    public UserEntity getUserEntity() {
        var user = (SecurityUser)
                SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getUser();        }

    @Override
    public UpdateUserDto  updateUser(UpdateUserDto userDto) {
        var userEntity = getUserEntity();
        userEntity.setFirstName(userDto.getFirstName());
        userEntity.setLastName(userDto.getLastName());
        userEntity.setPhone(userDto.getPhone());
        var saveUser = userRepository.save(userEntity);
        return UserMapper.userEntityToUpdateUser(saveUser);
    }

    @Override
    public void updatePassword(NewPasswordDto passwordDto) {
        var user = getUserEntity();
        user.setPassword(passwordEncoder.encode(passwordDto.getNewPassword()));
        userRepository.save(user);
    }

    @Override
    public void updateAvatar(MultipartFile file) {
        var user = getUserEntity();
        try {
            var url = "/content/" + photoService.saveImage(file, user);
            user.setImage(url);
        } catch (IOException e) {
            throw new RuntimeException();
        }
        userRepository.save(user);
    }

    @Override
    public Integer getUserId() {
        var user = (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getId();
    }
}
