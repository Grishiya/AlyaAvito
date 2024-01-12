package ru.skypro.homework.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.RoleDto;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.models.UserEntity;
import ru.skypro.homework.service.UserService;

import java.io.IOException;

@RestController
@RequestMapping("/users")
@CrossOrigin("http://localhost:3000")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/set_password")
    public ResponseEntity<String> setPassword(@RequestBody NewPasswordDto newPassword) {
        userService.updatePassword(newPassword);
        return ResponseEntity.ok("Ok");
    }

    @GetMapping("/me")
    public UserDto getUser() {

        return userService.getUser(1);
    }

    @PatchMapping("/me")
    public UpdateUserDto updateUser(@RequestBody UpdateUserDto updateUser)
    {
        return userService.updateUser(updateUser);
    }

    @PostMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> updateUserImage
            (@PathVariable Integer userId,@RequestParam MultipartFile image)throws IOException {

        userService.updateAvatar(userId, image);

        return ResponseEntity.ok("picture is save");
    }

    @GetMapping("/{id}/image-from-db")
    public ResponseEntity<byte[]> downloadImageFromDb(@PathVariable Integer id){

        UserEntity image = userService.readFromDb(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentLength(image.getImage().length);
        return ResponseEntity.ok().headers(headers).body(image.getImage());
    }

}