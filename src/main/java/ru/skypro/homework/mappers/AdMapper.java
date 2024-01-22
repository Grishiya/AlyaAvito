package ru.skypro.homework.mappers;

import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.ExtendedAdDto;
import ru.skypro.homework.models.Ad;

public class AdMapper {

    public static Ad createOrUpdateAdDtoInAd(CreateOrUpdateAdDto adDto) {
        if (adDto == null) {
            throw new
                    NullPointerException("Нельзя добавлять пустое объявление");
        }

          var  ad = new Ad();

        ad.setTitle(adDto.getTitle());
        ad.setPrice(adDto.getPrice());
        ad.setDescription(adDto.getDescription());
        return ad;
    }

    public static AdDto fromAd(Ad ad) {
        if (ad == null) {
            throw new NullPointerException("Такого объявления не существует");
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
            throw new NullPointerException("Такого объявления не существует");
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
