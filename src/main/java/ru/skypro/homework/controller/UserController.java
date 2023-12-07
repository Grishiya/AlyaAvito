package ru.skypro.homework.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.RoleDto;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.dto.UserDto;

@RestController
@RequestMapping("/users")
@CrossOrigin("http://localhost:3000")
public class UserController {

    @PostMapping("/set_password")
    public ResponseEntity<String> setPassword(@RequestBody NewPasswordDto newPassword) {
        return ResponseEntity.ok("Ok");
    }

    @GetMapping("/me")
    public UserDto getUser() {
        return new UserDto(1,"test@example.com",
                "Test","Test","+79000000000", RoleDto.USER,null);
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