package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.ExtendedAdDto;
import ru.skypro.homework.mappers.AdMapper;
import ru.skypro.homework.models.Ad;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.UserService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class AdServiceImpl implements AdService {

    private final AdRepository adRepository;
    private final UserService userService;

    public AdServiceImpl(AdRepository adRepository, UserService userService) {
        this.adRepository = adRepository;
        this.userService = userService;
    }

    @Override
    public AdsDto getAllAds() {
        List<AdDto> ads;
        ads = adRepository.findAll().stream().map(AdMapper::fromAd).collect(Collectors.toList());
        return new AdsDto(ads.size(), ads);
    }

    @Override
    public AdsDto getMyAds() {
        var ads = adRepository.findByAuthorId(userService.getUserId()).stream()
                .map(AdMapper::fromAd).collect(Collectors.toList());
        return new AdsDto(ads.size(), ads);
    }

    @Override
    public ExtendedAdDto getExtAdDto(Integer id) {
        return AdMapper.fromExtendedAd(getAdEntity(id));
    }

    @Override
    public Ad getAdEntity(Integer id) {
        return adRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("Нет такого объявления"));
    }

    @Override
    public void deleteAd(Integer id) {
        var ad = adRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("Нет такого объявления"));
        adRepository.delete(ad);
    }

    @Override
    public AdDto createAd(CreateOrUpdateAdDto adDto, MultipartFile multipartFile) {
        var ad = AdMapper.createOrUpdateAdDtoInAd(adDto);
        return AdMapper.fromAd(ad);
    }

    @Override
    public AdDto updateAd(CreateOrUpdateAdDto adDto, Integer id) {
        var ad = adRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Ad not found"));
        ad.setTitle(adDto.getTitle());
        ad.setDescription(ad.getDescription());
        ad.setPrice(adDto.getPrice());
        return AdMapper.fromAd(adRepository.save(ad));
    }

    @Override
    public byte[] updateImage(MultipartFile file) {
        return new byte[0];
    }
}
