package ru.skypro.homework.security;

import ru.skypro.homework.dto.RoleDto;
import ru.skypro.homework.models.UserEntity;

import java.util.Objects;

public class PermissionChecker {
    public static boolean isActionAllowed(UserEntity user, OwnedEnity entity) {
        return user.getRole() == RoleDto.ADMIN ||
                Objects.equals(user.getId(),entity.getOwnerId())
    }
}
