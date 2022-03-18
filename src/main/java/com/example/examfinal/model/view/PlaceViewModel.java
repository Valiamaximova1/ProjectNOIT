package com.example.examfinal.model.view;

import com.example.examfinal.model.entity.CityEntity;
import com.example.examfinal.model.entity.enums.NameType;
import com.example.examfinal.model.entity.enums.RatingEnum;

import javax.validation.constraints.Size;

public class PlaceViewModel {
    private Long id;
    private String name;
    private String imageUrl;
    private CityEntity city;
    private String latitude;
    private String longitude;

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public CityEntity getCity() {
        return city;
    }

    public void setCity(CityEntity city) {
        this.city = city;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
