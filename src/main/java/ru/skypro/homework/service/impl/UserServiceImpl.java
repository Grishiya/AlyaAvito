package ru.skypro.homework.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
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

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.NoSuchElementException;
import java.util.Optional;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private String avatarsDir;

    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);


    public UserServiceImpl(UserRepository userRepository,
                           @Value("${path.to.avatars.folder}") String avatarsDir) {
        this.userRepository = userRepository;
        this.avatarsDir=avatarsDir;
    }
    /**
     * The method get user
     *
     * @param id
     * @return userEntity
     * @throws NoSuchElementException This user not found
     */
    @Override
    public UserDto getUser(Integer id) {
        logger.info("The getUser method was called with data" + id );

        UserEntity user = userRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("This user not found"));

        return UserMapper.userEntityToUserDto(getUserEntity(id));
    }
    /**
     * The method get userEntity
     *
     * @param id
     * @return user
     * @throws NoSuchElementException User not found
     */
    @Override
    public UserEntity getUserEntity(Integer id) {
        logger.info("The getUserEntity method was called with data" + id );

        return userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("User not found"));
    }
    /**
     * The method update user and save in to database
     *
     * @param userDto
     * @return userEntity
     * @throws NoSuchElementException User not found
     */
    @Override
    public UpdateUserDto updateUser(UpdateUserDto userDto) {
        logger.info("The updateUser method was called with data" + userDto );

        var userEntity = userRepository.findById(1).orElseThrow(() -> new NoSuchElementException("User not found"));
        userEntity.setFirstName(userDto.getFirstName());
        userEntity.setLastName(userDto.getLastName());
        userEntity.setPhone(userDto.getPhone());
        var save = userRepository.save(userEntity);
        return UserMapper.userEntityToUpdateUser(save);
    }
    /**
     * The method update Password
     *
     * @param passwordDto
     * @throws NoSuchElementException User not found
     */
    @Override
    public void updatePassword(NewPasswordDto passwordDto) {
        logger.info("The updatePassword method was called with data" + passwordDto );

        var user = userRepository.findById(1).orElseThrow(() -> new NoSuchElementException("User not found"));
        user.setPassword(passwordDto.getCurrentPassword());
        userRepository.save(user);

    }

    @Override
    public void updateAvatar(Integer userId, MultipartFile imageFile) throws IOException {
        logger.info("The updateAvatar method was called with data" + userId + "and" + imageFile );

        UserEntity userPhoto = userRepository.findById(userId).orElse(new UserEntity());
        userPhoto.setImage(imageFile.getBytes());

        userRepository.save(userPhoto);

    }

    @Override
    public UserEntity readFromDb(Integer userId){
        logger.info("was called method readFromDB with data" + userId);

        UserEntity userPhotoFromDb = userRepository.findById(userId)
                .orElseThrow(()-> new RuntimeException("Photo not found!"));
        return userPhotoFromDb;
    }

}
