package com.example.examfinal.service;

import com.example.examfinal.model.binding.CityAddBindModel;
import com.example.examfinal.model.service.CityAddServiceModel;
import com.example.examfinal.model.service.CityUpdateServiceModel;
import com.example.examfinal.model.view.CityDetailsView;
import com.example.examfinal.model.view.CityView;
import com.example.examfinal.model.view.PlaceViewModel;

import java.util.List;

public interface CityService {
    void initializeCities();

    List<CityView> getAllCities();

    CityDetailsView findById(Long id);

    boolean isOwner(String userName, Long id);

    void deleteCity(Long id);

    void updateCity(CityUpdateServiceModel cityModel);

    CityAddServiceModel addCity(CityAddBindModel cityAddBindModel, String ownerId);
}
