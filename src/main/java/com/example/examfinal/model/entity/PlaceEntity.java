package com.example.examfinal.model.entity;

import com.example.examfinal.model.entity.enums.NameType;
import com.example.examfinal.model.entity.enums.RatingEnum;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "places")
public class PlaceEntity extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String imageUrl;

    @Enumerated(EnumType.STRING)
    private RatingEnum rating;


    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private NameType type;

    @Column(nullable = false)
    @Min(value = 0)
    private Double price;


    @Column(nullable = false)
    @Min(value = 0)
    private Double time;

    @Size(min = 3)
    private String latitude;

    @Size(min = 3)
    private String longitude;

    private Boolean naturalSites;


    @ManyToOne
    private CityEntity city;

    @OneToMany(mappedBy = "place", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Comment> comments;

    @ManyToOne
    private UserEntity user;

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

    public CityEntity getCity() {
        return city;
    }

    public void setCity(CityEntity city) {
        this.city = city;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
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
