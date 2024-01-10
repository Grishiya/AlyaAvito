package ru.skypro.homework.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.RoleDto;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.models.UserEntity;
import ru.skypro.homework.service.UserService;

@RestController
@RequestMapping("/users")
@CrossOrigin("http://localhost:3000")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/set_password")
    public ResponseEntity<String> setPassword(@RequestBody NewPasswordDto newPassword) {
        return ResponseEntity.ok("Ok");
    }

    @GetMapping("/me")
    public UserDto getUser(UserEntity userEntity) {

        return userService.read(userEntity.getId());
    }

    @PatchMapping("/me")
    public UpdateUserDto updateUser(@RequestBody UpdateUserDto updateUser) {
        return updateUser;
    }

    @PatchMapping("/me/image")
    public ResponseEntity<String> updateUserImage(@RequestPart("image") MultipartFile image) {
        return ResponseEntity.ok("Ok");
    }
}