package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.ExtendedAdDto;
import ru.skypro.homework.models.Ad;

public interface AdService {


    AdDto updateAd(CreateOrUpdateAdDto createOrUpdateAdDto, Integer userId);

    AdDto createAd(CreateOrUpdateAdDto createOrUpdateAdDto, MultipartFile multipartFile);
    Ad getAdEntity(Integer id);

    AdsDto getAllAds();

    AdsDto getMyAds();

    ExtendedAdDto getExtAdDto(Integer id);

    void deleteAd(Integer id);

    byte[] updateImage(MultipartFile file);
}
