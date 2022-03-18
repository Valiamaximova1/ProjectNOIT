package com.example.examfinal.model.binding;


import com.example.examfinal.model.entity.enums.AreaOfBg;

import javax.validation.constraints.*;

public class CityAddBindModel {

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotNull
    @Positive
    private Integer population;


    @NotBlank
    private String imageUrl;

    @NotBlank
    @Size(min = 3)
    private String mayor;

    @NotNull
    private AreaOfBg areaOfBg;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getMayor() {
        return mayor;
    }

    public void setMayor(String mayor) {
        this.mayor = mayor;
    }

    public AreaOfBg getAreaOfBg() {
        return areaOfBg;
    }

    public void setAreaOfBg(AreaOfBg areaOfBg) {
        this.areaOfBg = areaOfBg;
    }
}