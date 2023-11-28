package ru.skypro.homework.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.ExtendedAdDto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/ads")
@CrossOrigin("http://localhost:3000")
public class AdController {
    private  AdDto adDto = new AdDto(1, null, 1, 100, "test");
    private  AdsDto adsDto = new AdsDto(1, List.of(adDto));
    private final ExtendedAdDto extendedAdDto = new ExtendedAdDto(
            1, "test", "test", "test",
            "test", null, "+2222", 1, "test");

    @GetMapping
    public AdsDto getAllAds() {
        return adsDto;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public AdDto createAd(@RequestBody CreateOrUpdateAdDto createOrUpdateAdDto, @RequestParam MultipartFile image) {
        return new AdDto(1, null, 1,
                createOrUpdateAdDto.getPrice() , createOrUpdateAdDto.getTitle());
    }

    @GetMapping("/{id}")
    public ExtendedAdDto getExtendedAdDto(@PathVariable Integer id) {
            return new ExtendedAdDto(
                    1, "test", "test",
                    "test Description","test@example.com",
                    null, "+79000000000", 100, "test");
        }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeAd(@PathVariable Integer id) {
        return ResponseEntity.ok("OK");
    }

    @PatchMapping("/{id}")
    public AdDto updateAds(@PathVariable Integer id, @RequestBody CreateOrUpdateAdDto createOrUpdateAdDto) {
        return new AdDto(1, null, 1,
                createOrUpdateAdDto.getPrice() , createOrUpdateAdDto.getTitle());
    }

    @GetMapping("/me")
    public AdsDto getAdsMe() {
        return new AdsDto(1,List.of(new AdDto(1, null, 1, 100, "test")));
    }

    @PatchMapping(value = "/{adId}/image",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<byte[]> updateImage(@PathVariable Integer adId, @RequestParam("image") MultipartFile image) {
        return ResponseEntity.ok().build();
    }
}
