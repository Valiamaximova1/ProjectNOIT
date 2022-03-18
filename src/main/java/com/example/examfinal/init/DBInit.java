package com.example.examfinal.init;

import com.example.examfinal.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DBInit implements CommandLineRunner {


    private final UserService userService;
    private final CityService cityService;
    private final PlaceService placeService;

    public DBInit(UserService userService, CityService cityService, PlaceService placeService) {
        this.userService = userService;
        this.cityService = cityService;
        this.placeService = placeService;
    }



    @Override
    public void run(String... args) throws Exception {
        userService.initializeUsersAndRoles();
        cityService.initializeCities();
        placeService.initializePlace();
    }
}