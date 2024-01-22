package ru.skypro.homework.security;

;
import lombok.Getter;
import org.springframework.security.core.userdetails.User;
import ru.skypro.homework.models.UserEntity;

import java.util.List;


@Getter
public class SecurityUser extends User {
    private final Integer id;
    private final UserEntity user;

    public SecurityUser(UserEntity user) {
        super(user.getEmail(), user.getPassword(), List.of(user.getRole()));
        this.id = user.getId();
        this.user = user;
    }

}
