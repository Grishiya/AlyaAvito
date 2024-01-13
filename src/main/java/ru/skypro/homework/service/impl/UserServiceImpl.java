package ru.skypro.homework.service.impl;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.RoleDto;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.exception.UserAlreadyAddedException;
import ru.skypro.homework.mappers.UserMapper;
import ru.skypro.homework.models.UserEntity;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.security.SecurityUser;
import ru.skypro.homework.service.UserService;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
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
        var userEntity = userRepository.findById(1).orElseThrow(() -> new NoSuchElementException("User not found"));
        userEntity.setFirstName(userDto.getFirstName());
        userEntity.setLastName(userDto.getLastName());
        userEntity.setPhone(userDto.getPhone());
        var save = userRepository.save(userEntity);
        return UserMapper.userEntityToUpdateUser(save);
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
