package ru.skypro.homework.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.ExtendedAdDto;
import ru.skypro.homework.mappers.AdMapper;
import ru.skypro.homework.models.Ad;
import ru.skypro.homework.models.UserEntity;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
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
    public AdsDto getMyAds( Integer userId) {
        userId = 1;
        var ads = adRepository.findByUserId(userId).stream()
                .map(AdMapper::fromAd).collect(Collectors.toList());
        return new AdsDto(ads.size(), ads);
    }

    @Override
    public ExtendedAdDto getExtAdDto(Integer id) {
        return AdMapper.fromExtendedAd(getAdEntity(id));
    }

    @Override
    public Ad getAdEntity(Integer id) {
        return adRepository.findById(id).orElseThrow(()->
                new NoSuchElementException("Ad not found."));
    }

    @Override
    public void deleteAd(Integer id) {
        var ad = adRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("Ad not fond"));
    adRepository.delete(ad);
    }

    @Override
    public AdDto createAd(CreateOrUpdateAdDto adDto, Integer id) {
        Ad ad = null;
        if (id != 0 && id != null) {
            adRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Ad not found"));
        }
        ad = AdMapper.createOrUpdateAdDtoInAd(adDto, ad);
        var user = userService.getUserEntity(1);
        ad.setAuthor(user);
        var save = adRepository.save(ad);
        return AdMapper.fromAd(save);
    }

    @Override
    public byte[] updateImage(MultipartFile file) {
        return new byte[0];
    }
}
