package ru.skypro.homework.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.CreateOrUpdateAdDto;
import ru.skypro.homework.models.Ad;
import ru.skypro.homework.models.UserEntity;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AdService;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class AdServiceImpl implements AdService {

    private final AdRepository adRepository;
    private final UserRepository userRepository;


    public AdServiceImpl(AdRepository adRepository, UserRepository userRepository) {
        this.adRepository = adRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Ad create(CreateOrUpdateAdDto createOrUpdateAdDto, Integer userId) {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow();
        Ad ad = new Ad();
        ad.setAuthor(userEntity);
        ad.setTitle(createOrUpdateAdDto.getTitle());
        ad.setPrice(createOrUpdateAdDto.getPrice());
        ad.setDescription(createOrUpdateAdDto.getDescription());
        return adRepository.save(ad);

    }

    @Override
    public Ad read(Integer id) {
        Optional<Ad> ad = adRepository.findById(id);
        if (!ad.isEmpty()) {
            throw new NoSuchElementException("Такого объявления нет");
        }
        return ad.get();
    }
}
