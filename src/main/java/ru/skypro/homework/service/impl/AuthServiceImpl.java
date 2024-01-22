package ru.skypro.homework.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.RegisterDto;
import ru.skypro.homework.mappers.UserMapper;
import ru.skypro.homework.models.UserEntity;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserDetailsService manager;
    private final PasswordEncoder encoder;
    private final UserRepository userRepository;

    public AuthServiceImpl(UserDetailsService manager, PasswordEncoder encoder, UserRepository userRepository) {
        this.manager = manager;
        this.encoder = encoder;
        this.userRepository = userRepository;
    }

    @Override
    public boolean login(String userName,  String password) {
        UserDetails userDetails = manager.loadUserByUsername(userName);
        return encoder.matches(password, userDetails.getPassword());
    }

    @Override
    public boolean register(RegisterDto register) {
        if (userRepository.existsByEmail(register.getUsername())) {
            return false;
        }
        UserEntity user = UserMapper.registerDtoInUserEntity(register);
        user.setPassword(encoder.encode(register.getPassword()));
        userRepository.save(user);
        return true;
    }

}
