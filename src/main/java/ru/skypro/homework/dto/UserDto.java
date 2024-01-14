package ru.skypro.homework.dto;

import lombok.*;


@ToString
@EqualsAndHashCode
@Setter
@Getter
public class UserDto {

    private Integer id;
    private String username;
    private String firstName;
    private String lastName;
    private String phone;
    private  RoleDto role;
    private String image;


}
