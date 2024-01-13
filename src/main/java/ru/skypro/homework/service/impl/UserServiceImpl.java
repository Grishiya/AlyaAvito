package ru.skypro.homework.service.impl;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.RoleDto;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.mappers.UserMapper;
import ru.skypro.homework.models.UserEntity;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.security.SecurityUser;
import ru.skypro.homework.service.UserService;

import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto getUser() {

        return UserMapper.userEntityToUserDto(getUserEntity());
    }

    @Override
    public UserEntity getUserEntity() {
        var user = (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getUserEntity();        }

    @Override
    public UpdateUserDto  updateUser(UpdateUserDto userDto) {
        var userEntity = getUserEntity();
        userEntity.setFirstName(userDto.getFirstName());
        userEntity.setLastName(userDto.getLastName());
        userEntity.setPhone(userDto.getPhone());
        return UserMapper.userEntityToUpdateUser(userRepository.save(userEntity));
    }

    @Override
    public void updatePassword(NewPasswordDto passwordDto) {
        var user = getUserEntity();
        user.setPassword(passwordEncoder.encode(passwordDto.getNewPassword()));
        userRepository.save(user);
    }

    @Override
    public void updateAvatar(MultipartFile file) {
    }

    @Override
    public Integer getUserId() {
        var user = (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getId();
    }
    @Override
    public boolean featuresRole(Integer id){
        return Objects.equals(getUserId(), id) || getUser().getRole() == RoleDto.ADMIN;
    }
}
