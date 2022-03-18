package com.example.examfinal.model.entity;

import com.example.examfinal.model.entity.enums.AreaOfBg;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "cities")
public class CityEntity extends BaseEntity {
    //name, description, year, Capital ot not(enum), imageUrl, mayor, area of Bg
    @Column(nullable  = false)
    private String name;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;
    private Integer population;
    private String imageUrl;
    private String mayor;
    @Enumerated(EnumType.STRING)
    private AreaOfBg areaOfBg;
    @ManyToOne
    private UserEntity user;
    @OneToMany(mappedBy = "city", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PlaceEntity> places;


    public CityEntity() {
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

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public List<PlaceEntity> getPlaces() {
        return places;
    }

    public void setPlaces(List<PlaceEntity> places) {
        this.places = places;
    }
}
