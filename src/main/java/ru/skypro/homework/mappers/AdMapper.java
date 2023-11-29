package ru.skypro.homework.mappers;

import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.models.Ad;
import ru.skypro.homework.models.UserEntity;

public class AdMapper {

    public static Ad toAd(AdDto adDto, UserEntity author) {
        if (adDto == null) {
            throw new NullPointerException("Tried to map null to Ad");
        }


        Ad ad = new Ad();

        ad.setPkId(adDto.getPkId());
        ad.setAuthor(author);
        ad.setImage(adDto.getImage());
        ad.setPrice(adDto.getPrice());
        ad.setTitle(adDto.getTitle());

        return ad;

    }

    public static AdDto fromAd(Ad ad) {
        if (ad == null) {
            throw new NullPointerException("Tried to map null to AdDto");
        }

        AdDto adDto = new AdDto();

        adDto.setPkId(ad.getPkId());
        adDto.setAuthorId(ad.getAuthor().getId());
        adDto.setImage(ad.getImage());
        adDto.setPrice(ad.getPrice());
        adDto.setTitle(ad.getTitle());

        return adDto;
    }


}
