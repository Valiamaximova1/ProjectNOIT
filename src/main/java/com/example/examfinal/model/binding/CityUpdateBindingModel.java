package com.example.examfinal.model.binding;

import com.example.examfinal.model.entity.enums.AreaOfBg;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class CityUpdateBindingModel {

    private Long id;

    @NotEmpty
    private String description;

    @NotNull
    private String imageUrl;

    @NotNull
    @Positive
    private Integer population;

    @NotBlank
    private String name;

    @NotNull
    private String mayor;

    @NotNull
    private AreaOfBg areaOfBg;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
