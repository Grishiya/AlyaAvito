package ru.skypro.homework.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.security.core.GrantedAuthority;

public enum RoleDto implements GrantedAuthority {
    USER("USER"), ADMIN("ADMIN")
    ;
    private String value;

    RoleDto(String value) {
        this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
        return String.valueOf(value);
    }

    @JsonCreator
    public static RoleDto fromValue(String string) {
        for (RoleDto roleDto : RoleDto.values()) {
            if (String.valueOf(roleDto.value).equals(string)) {
                return roleDto;
            }
        }
        return null;
    }

    @Override

    public String getAuthority() {
        return value;
    }
}
