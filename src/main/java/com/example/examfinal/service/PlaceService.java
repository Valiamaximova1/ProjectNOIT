package com.example.examfinal.service;

import com.example.examfinal.model.binding.CityAddBindModel;
import com.example.examfinal.model.binding.PlaceAddBindingModel;
import com.example.examfinal.model.binding.PlaceUpdateBingdingModel;
import com.example.examfinal.model.service.CityAddServiceModel;
import com.example.examfinal.model.service.CityUpdateServiceModel;
import com.example.examfinal.model.service.PlaceAddServiceModel;
import com.example.examfinal.model.service.PlaceUpdateServiceModel;
import com.example.examfinal.model.view.CityDetailsView;
import com.example.examfinal.model.view.CityView;
import com.example.examfinal.model.view.PlaceDetailsViewModel;
import com.example.examfinal.model.view.PlaceViewModel;

import java.util.List;

public interface PlaceService {
    List<PlaceViewModel> getAllPlace();

    List<PlaceViewModel> getCulture();

    List<PlaceViewModel> getNature();

    List<PlaceViewModel> getHistory();

    List<PlaceViewModel> getById(Long id);

    List<PlaceViewModel> getReligios();

    void initializePlace();

    PlaceDetailsViewModel findById(Long id);

    void deletePlace(Long id);

    boolean isOwner(String userName, Long id);

    void updatePlace(PlaceUpdateServiceModel placeModel);

    PlaceAddServiceModel addPlace(PlaceAddBindingModel placeAddBindModel, String ownerId);

    void deletePlacesInOneCity(Long cityId);}
