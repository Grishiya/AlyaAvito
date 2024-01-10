package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.exception.UserAlreadyAddedException;
import ru.skypro.homework.mappers.UserMapper;
import ru.skypro.homework.models.UserEntity;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.UserService;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;


    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto getUser(Integer id) {
        UserEntity user = userRepository.findById(id).orElseThrow(
                ()-> new NoSuchElementException("THis user not found"));

        return UserMapper.userEntityToUserDto(getUserEntity(id));
    }

    @Override
    public UserEntity getUserEntity(Integer id) {
        return userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("User not found"));
        }

    @Override
    public UpdateUserDto updateUser(UpdateUserDto userDto) {
        var userEntity = userRepository.findById(1).orElseThrow(() -> new NoSuchElementException("User not found"));
        userEntity.setFirstName(userDto.getFirstName());
        userEntity.setLastName(userDto.getLastName());
        userEntity.setPhone(userDto.getPhone());
        var save = userRepository.save(userEntity);
        return UserMapper.userEntityToUpdateUser(save);
    }

    @Override
    public void updatePassword(NewPasswordDto passwordDto) {
        var user = userRepository.findById(1).orElseThrow(() -> new NoSuchElementException("User not found"));
        user.setPassword(passwordDto.getCurrentPassword());
        userRepository.save(user);

    }

    @Override
    public void updateAvatar(MultipartFile file) {
    }
}
