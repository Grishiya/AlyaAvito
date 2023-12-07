package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
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
    private UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper){
        this.userRepository=userRepository;
        this.userMapper = userMapper;
    }



    @Override
    public UserDto read(Integer id) {
        UserEntity user = userRepository.findById(id).orElseThrow(
                ()-> new NoSuchElementException("THis user not found"));

        return userMapper.userToUserDTO(user);
    }

    @Override
    public UserEntity update(UserEntity userEntity) {
        Optional<UserEntity> check = userRepository.findById(userEntity.getId());
        if(check.isEmpty()){
            throw new NoSuchElementException("This userEntity not found!");
        }

        return userRepository.save(userEntity);
    }

}
