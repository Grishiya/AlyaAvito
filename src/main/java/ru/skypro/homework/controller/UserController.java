package ru.skypro.homework.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.service.UserService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/users")
@CrossOrigin("http://localhost:3000")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private HttpServletRequest request;

    @PostMapping("/set_password")
    public ResponseEntity<String> setPassword(@RequestBody NewPasswordDto newPassword) {
        userService.updatePassword(newPassword);
        return ResponseEntity.ok("Ok");
    }

    @GetMapping("/me")
    public UserDto getUser() {

        return userService.getUser();
    }

    @PatchMapping("/me")
    public UpdateUserDto updateUser(@RequestBody UpdateUserDto updateUser)
    {
        return userService.updateUser(updateUser);
    }

    @PatchMapping(value = "/me/image",
   consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> updateUserImage(@RequestPart("image") MultipartFile image) {
        userService.updateAvatar(image);
        return ResponseEntity.ok("Ok");
    }
}