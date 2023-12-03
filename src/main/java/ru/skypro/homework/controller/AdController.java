package ru.skypro.homework.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.ExtendedAdDto;
import ru.skypro.homework.mappers.AdMapper;
import ru.skypro.homework.service.AdService;

import java.util.List;

@RestController
@RequestMapping("/ads")
@CrossOrigin("http://localhost:3000")
public class AdController {

    private final AdService adService;

    public AdController(AdService adService) {
        this.adService = adService;
    }

    @GetMapping
    public AdsDto getAllAds() {
        return new AdsDto(2,List.of(new AdDto(1,null,1,100,"Test"),
                new AdDto(1,null,2,150,"Test2")));
    }


    @GetMapping("/{id}")
        public ExtendedAdDto getExtendedAdDto(@PathVariable Integer id) {
            return AdMapper.fromExtendedAd(adService.read(id));
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
        return new AdsDto(2,List.of(new AdDto(1,null,1,100,"Test"),
                new AdDto(1,null,2,150,"Test2")));
    }

    @PatchMapping(value = "/{adId}/image",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<byte[]> updateImage(@PathVariable Integer adId, @RequestParam("image") MultipartFile image) {
        return ResponseEntity.ok().build();
    }
}
