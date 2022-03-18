package com.example.examfinal.web;

import com.example.examfinal.service.PlaceService;
import com.example.examfinal.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;
    private final PlaceService placeService;

    public UserController(UserService userService, ModelMapper modelMapper, PlaceService placeService) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.placeService = placeService;
    }
    @GetMapping("/users/all")
    public String allUsers(Model model) {
        model.addAttribute("usersAll", userService.getAllUsers());
        return "users";
    }

    @GetMapping("/user/{id}")
    public String editUser(@PathVariable String userId, Model model){

        model.addAttribute("users", this.userService.findById(Long.parseLong(userId)));
        return "user-details";

    }
    @GetMapping("/users/{id}/user-details")
    public String showUser(@PathVariable Long id, Model model) {
        model.addAttribute("user", this.userService.findById(id));
        model.addAttribute("places", this.placeService.getById(id));
        return "user-details";
    }



}
