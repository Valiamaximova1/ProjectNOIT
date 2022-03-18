package com.example.examfinal.model.service;

public class CommentServiceModel {

  private Long placeId;
  private String message;
  private String creator;


  public Long getPlaceId() {
    return placeId;
  }

  public void setPlaceId(Long placeId) {
    this.placeId = placeId;
  }

  public String getMessage() {
    return message;
  }

  public CommentServiceModel setMessage(String message) {
    this.message = message;
    return this;
  }

  public String getCreator() {
    return creator;
  }

  public CommentServiceModel setCreator(String creator) {
    this.creator = creator;
    return this;
  }
}
