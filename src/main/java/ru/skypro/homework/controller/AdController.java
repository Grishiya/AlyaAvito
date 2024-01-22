package ru.skypro.homework.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.service.AdService;
import java.util.Arrays;


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
        return adService.getAllAds();
    }


    @GetMapping("/me")
    public AdsDto getAdsMe() {
        return adService.getMyAds();
    }

    @GetMapping("/{id}")
    public ExtendedAdDto getExtendedAdDto(@PathVariable Integer id) {
        return adService.getExtAdDto(id);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public AdDto create(@RequestPart("properties") CreateOrUpdateAdDto adDto, @RequestPart("image") MultipartFile multipartFile) {
        return adService.createAd(adDto, multipartFile);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeAd(@PathVariable Integer id) {
        adService.deleteAd(id);
        return ResponseEntity.ok("OK");
    }

    @PatchMapping("/{id}")
    public AdDto updateAds(@PathVariable Integer id, @RequestBody CreateOrUpdateAdDto createOrUpdateAdDto) {
        return adService.updateAd(createOrUpdateAdDto, id);
    }

    @PatchMapping(value = "/{adId}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> updateImage(@PathVariable Integer adId, @RequestParam("image") MultipartFile image) {
        return new ResponseEntity<>(Arrays.toString(adService.updateImage(adId, image)), HttpStatus.OK);
    }
}
