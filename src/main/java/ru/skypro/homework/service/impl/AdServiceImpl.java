package ru.skypro.homework.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.UserService;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class AdServiceImpl implements AdService {

    private final AdRepository adRepository;
    private final UserService userService;

    private final Logger logger = LoggerFactory.getLogger(AdServiceImpl.class);

    public AdServiceImpl(AdRepository adRepository, UserService userService) {
        this.adRepository = adRepository;
        this.userService = userService;
    }
    /**
     * The method gets all Ads and save it in the database
     *
     * @return a new AdsDto
     */
    @Override
    public AdsDto getAllAds() {
        logger.info("The getAllAds method was called");
        List<AdDto> ads;
        ads = adRepository.findAll().stream().map(AdMapper::fromAd).collect(Collectors.toList());
        return new AdsDto(ads.size(), ads);
    }
    /**
     * The method gets user Ads
     *
     * @param userId
     * @return a new AdsDto
     */
    @Override
    public AdsDto getMyAds( Integer userId) {
        logger.info("The getMyAds method was called with data" + userId);
        userId = 1;
        var ads = adRepository.findByAuthorId(userId).stream()
                .map(AdMapper::fromAd).collect(Collectors.toList());
        return new AdsDto(ads.size(), ads);
    }
    /**
     * The method gets ExtendedAdDto
     *
     * @param id
     * @return AdEntity
     */
    @Override
    public ExtendedAdDto getExtAdDto(Integer id) {
        logger.info("The getExtAdDto method was called with data" + id);

        return AdMapper.fromExtendedAd(getAdEntity(id));
    }
    /**
     * The method gets AdEntity
     *
     * @param id
     * @return AdEntity
     * @throws NoSuchElementException Ad not found.
     */
    @Override
    public Ad getAdEntity(Integer id) {
        logger.info("The getAdEntity method was called with data" + id);

        return adRepository.findById(id).orElseThrow(()->
                new NoSuchElementException("Ad not found."));
    }
    /**
     * The method delete Ad
     *
     * @param id
     * @throws NoSuchElementException Ad not found.
     */
    @Override
    public void deleteAd(Integer id) {
        logger.info("The deleteAd method was called with data" + id);

        var ad = adRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("Ad not fond"));
    adRepository.delete(ad);
    }
    /**
     * The method create Ad and save to database
     *
     * @param adDto, id
     * @return Ad
     * @throws NoSuchElementException Ad not found.
     */
    @Override
    public AdDto createAd(CreateOrUpdateAdDto adDto, Integer id) {
        logger.info("The createAd method was called with data" + adDto + "and" + id);
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
    public void updateImage(Integer authorId,MultipartFile imageFile) throws IOException {
        logger.info("The updateImage method was called with data" + authorId + "and" + imageFile);

        Ad authorPhoto = (Ad) adRepository.findByAuthorId(authorId);
        authorPhoto.setImage(imageFile.getBytes());

        adRepository.save(authorPhoto);

    }


}
