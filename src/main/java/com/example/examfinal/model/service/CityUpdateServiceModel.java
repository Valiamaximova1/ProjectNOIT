package com.example.examfinal.model.service;

import com.example.examfinal.model.entity.enums.AreaOfBg;

public class CityUpdateServiceModel {
    private Long id;
    private String name;
    private String description;
    private Integer population;
    private String imageUrl;
    private String mayor;
    private AreaOfBg areaOfBg;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
