package ru.skypro.homework.security;

import lombok.Data;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import ru.skypro.homework.models.UserEntity;

import java.util.Collection;
import java.util.List;


@Getter
public class SecurityUser extends User {
    private final Integer id;
    private final UserEntity userEntity;


    public SecurityUser (UserEntity userEntity) {
        super(userEntity.getEmail(), userEntity.getPassword(), List.of(userEntity.getRole()));
        this.id = userEntity.getId();
        this.userEntity = userEntity;
    }

}
