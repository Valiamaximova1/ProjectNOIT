package com.example.examfinal.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "comments")
public class Comment extends BaseEntity {


    @Column(nullable = false)
    private Boolean approved;
    @Lob
    private String textContent;
    @ManyToOne
    private PlaceEntity place;
    @ManyToOne
    private UserEntity user;

    public Comment() {
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }


    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }


    public PlaceEntity getPlace() {
        return place;
    }

    public void setPlace(PlaceEntity place) {
        this.place = place;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
