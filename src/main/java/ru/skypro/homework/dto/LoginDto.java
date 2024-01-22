package ru.skypro.homework.dto;

import lombok.*;

import javax.validation.constraints.Size;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {
    @Size(min = 4, max = 32)
    private String username;
    @Size(min = 8, max = 16)
    private String password;
}
