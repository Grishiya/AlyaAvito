package ru.skypro.homework.mappers;

import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.ExtendedAdDto;
import ru.skypro.homework.models.Ad;
import ru.skypro.homework.models.UserEntity;

public class AdMapper {

    public static Ad toAd(AdDto adDto, UserEntity author) {
        if (adDto == null) {
            throw new NullPointerException("Tried to map null to Ad");
        }


        Ad ad = new Ad();

        ad.setId(adDto.getPk());
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

        adDto.setPk(ad.getId());
        adDto.setAuthor(ad.getAuthor().getId());
        adDto.setImage(ad.getImage());
        adDto.setPrice(ad.getPrice());
        adDto.setTitle(ad.getTitle());

        return adDto;
    }

    public static ExtendedAdDto fromExtendedAd(Ad ad) {
        if (ad == null) {
            throw new NullPointerException("Tried to map null to AdDto");
        }

        ExtendedAdDto extendedAdDto = new ExtendedAdDto();

        extendedAdDto.setPk(ad.getId());
        extendedAdDto.setAuthorFirstName(ad.getAuthor().getFirstName());
        extendedAdDto.setAuthorLastName(ad.getAuthor().getLastName());
        extendedAdDto.setDescription(ad.getDescription());
        extendedAdDto.setEmail(ad.getAuthor().getEmail());
        extendedAdDto.setImage(ad.getImage());
        extendedAdDto.setPhone(ad.getAuthor().getPhone());
        extendedAdDto.setPrice(ad.getPrice());
        extendedAdDto.setTitle(ad.getTitle());

        return extendedAdDto;
    }

}
