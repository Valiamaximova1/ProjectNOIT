package com.example.examfinal.model.service;

import com.example.examfinal.model.entity.enums.NameType;
import com.example.examfinal.model.entity.enums.RatingEnum;

public class PlaceUpdateServiceModel {
    private Long id;
    private String city;
    private String name;
    private String description;
    private String imageUrl;
    private RatingEnum rating;
    private NameType type;
    private Double price;
    private Double time;
    private String latitude;

    private String longitude;
    private Boolean naturalSites;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public RatingEnum getRating() {
        return rating;
    }

    public void setRating(RatingEnum rating) {
        this.rating = rating;
    }

    public NameType getType() {
        return type;
    }

    public void setType(NameType type) {
        this.type = type;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getTime() {
        return time;
    }

    public void setTime(Double time) {
        this.time = time;
    }

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

    public Boolean getNaturalSites() {
        return naturalSites;
    }

    public void setNaturalSites(Boolean naturalSites) {
        this.naturalSites = naturalSites;
    }
}
