package com.example.examfinal.model.binding;

import com.example.examfinal.model.entity.CityEntity;
import com.example.examfinal.model.entity.UserEntity;
import com.example.examfinal.model.entity.enums.NameType;
import com.example.examfinal.model.entity.enums.RatingEnum;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;


public class PlaceAddBindingModel {
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotBlank
    private String imageUrl;

    @NotNull
    private RatingEnum rating;

    @NotNull
    private NameType type;

    @NotNull
    private CityEntity city;

    @NotNull
    private BigDecimal price;

    @NotNull
    private Double time;

    private String latitude;

    private String longitude;

    private Boolean naturalSites;

    private UserEntity user;

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

    public CityEntity getCity() {
        return city;
    }

    public void setCity(CityEntity city) {
        this.city = city;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Double getTime() {
        return time;
    }

    public void setTime(Double time) {
        this.time = time;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
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
