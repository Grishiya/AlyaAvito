package ru.skypro.homework.service;

import ru.skypro.homework.dto.CreateOrUpdateAdDto;
import ru.skypro.homework.models.Ad;

public interface AdService {


    Ad create(CreateOrUpdateAdDto createOrUpdateAdDto, Integer userId);

    Ad read(Integer id);
}
