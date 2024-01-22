package ru.skypro.homework.service.impl;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.ExtendedAdDto;
import ru.skypro.homework.exception.ActionForbiddenException;
import ru.skypro.homework.mappers.AdMapper;
import ru.skypro.homework.models.Ad;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.security.PermissionChecker;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.PhotoService;
import ru.skypro.homework.service.UserService;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AdServiceImpl implements AdService {

    private final AdRepository adRepository;
    private final UserService userService;
    private final PhotoService photoService;

    public AdServiceImpl(AdRepository adRepository,
                         UserService userService,
                         PhotoService photoService) {
        this.adRepository = adRepository;
        this.userService = userService;
        this.photoService = photoService;
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
        var user = userService.getUserEntity();
        if (!PermissionChecker.isActionAllowed(user,ad)) throw new ActionForbiddenException();
        var fileName = ad.getImage().substring(ad.getImage().lastIndexOf("/") + 1);
        try {
            photoService.deletePhoto(fileName, Ad.class.getSimpleName());
        } catch (IOException e) {
            throw new RuntimeException();
        }
        adRepository.delete(ad);
    }

    @Override
    public AdDto createAd(CreateOrUpdateAdDto adDto, MultipartFile multipartFile) {
        var ad = AdMapper.createOrUpdateAdDtoInAd(adDto);
        try {
            var url = "/content/" + photoService.saveImage(multipartFile, ad);
            ad.setAuthor(userService.getUserEntity());
            ad.setImage(url);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return AdMapper.fromAd(adRepository.save(ad));
    }
    @Override
    public AdDto updateAd(CreateOrUpdateAdDto adDto, Integer id) {
        var ad = adRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Ad not found"));
        var user = userService.getUserEntity();
        if (!PermissionChecker.isActionAllowed(user,ad)) throw new ActionForbiddenException();
        ad.setTitle(adDto.getTitle());
        ad.setDescription(ad.getDescription());
        ad.setPrice(adDto.getPrice());
        return AdMapper.fromAd(adRepository.save(ad));
    }

    @Override
    public byte[] updateImage(Integer id, MultipartFile file) {
        var ad = getAdEntity(id);
        try {
            ad.setImage("/content/" + photoService.saveImage(file, ad));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        adRepository.save(ad);
        return new byte[0];
    }
}
