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
public class AdController {
    private final AdDto adDto = new AdDto(1, "test", 1, 1, "test");
    private final AdsDto adsDto = new AdsDto(1, List.of(adDto));
    private final ExtendedAdDto extendedAdDto = new ExtendedAdDto(
            1, "test", "test", "test",
            "test", "test", "+2222", 1, "test");

    @GetMapping
    public AdsDto getAllAds() {
        return adsDto;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public AdDto createAd(@RequestBody CreateOrUpdateAdDto createOrUpdateAdDto, @RequestParam MultipartFile image) {
        return adDto;
    }

    @GetMapping("/{id}")
    public ExtendedAdDto getExtendedAdDto(@PathVariable Integer id) {
            return extendedAdDto;
        }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeAd(@PathVariable Integer id) {
        return ResponseEntity.ok("OK");
    }

    @PatchMapping("/{id}")
    public AdDto updateAds(@PathVariable Integer id, @RequestBody CreateOrUpdateAdDto createOrUpdateAdDto) {
        return adDto;
    }

    @GetMapping("/me")
    public AdsDto getAdsMe() {
        return adsDto;
    }

    @PatchMapping(value = "/{adId}/image",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<byte[]> updateImage(@PathVariable Integer adId, @RequestParam("image") MultipartFile image) {
        return ResponseEntity.ok().build();
    }
}
