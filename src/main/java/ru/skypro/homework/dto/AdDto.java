package ru.skypro.homework.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
// Объявление
public class AdDto {

    private Integer author;
    private String image;
    @JsonProperty("pk")
    private Integer Id;
    private Integer price;
    private String title;

}
