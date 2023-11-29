package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.homework.exception.UserAlreadyAddedException;
import ru.skypro.homework.models.UserEntity;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.UserService;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    @Override
    public UserEntity create(UserEntity userEntity) {
        if(userRepository.findByFirstNameAndLastName(
                userEntity.getFirstName(),
                userEntity.getLastName()).isPresent()){
            throw new UserAlreadyAddedException(
                    "UserEntity with that initial is already added!");
        }
        return userRepository.save(userEntity);
    }

    @Override
    public UserEntity read(Integer id) {
        Optional<UserEntity> user = userRepository.findById(id);
        if(user.isEmpty()){
            throw new NoSuchElementException("This user not found!");
        }
        return user.get();
    }

    @Override
    public UserEntity update(UserEntity userEntity) {
        Optional<UserEntity> check = userRepository.findById(userEntity.getId());
        if(check.isEmpty()){
            throw new NoSuchElementException("This userEntity not found!");
        }

        return userRepository.save(userEntity);
    }

    @Override
    public UserEntity delete(Integer id) {
        Optional<UserEntity> user = userRepository.findById(id);
        if(user.isEmpty()){
            throw new NoSuchElementException("This user not found!");
        }
        userRepository.deleteById(id);

        UserEntity deleteUserEntity = user.get();
        return deleteUserEntity;
    }
}
