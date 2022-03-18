package com.example.examfinal.web;

import com.example.examfinal.model.binding.PlaceAddBindingModel;
import com.example.examfinal.model.binding.PlaceUpdateBingdingModel;
import com.example.examfinal.model.entity.enums.NameType;
import com.example.examfinal.model.entity.enums.RatingEnum;
import com.example.examfinal.model.service.PlaceAddServiceModel;
import com.example.examfinal.model.service.PlaceUpdateServiceModel;
import com.example.examfinal.model.view.PlaceDetailsViewModel;
import com.example.examfinal.service.CityService;
import com.example.examfinal.service.PlaceService;
import com.example.examfinal.service.impl.ProjectUser;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;


@Controller
public class PlaceController {
    private final PlaceService placeService;
    private final CityService cityService;
    private final ModelMapper modelMapper;

    public PlaceController(PlaceService placeService, CityService cityService, ModelMapper modelMapper) {
        this.placeService = placeService;
        this.cityService = cityService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/places/all")
    public String allPlaces(Model model) {
        model.addAttribute("places", placeService.getAllPlace());
        return "places";
    }
    @GetMapping("/places/culture")
    public String culture(Model model) {
        model.addAttribute("culture", placeService.getCulture());
        return "culture";
    }
    @GetMapping("/places/nature")
    public String nature(Model model) {
        model.addAttribute("nature", placeService.getNature());
        return "nature";
    }

    @GetMapping("/places/history")
    public String history(Model model) {
        model.addAttribute("history", placeService.getHistory());
        return "history";
    }
    @GetMapping("/places/religious")
    public String religious(Model model) {
        model.addAttribute("religious", placeService.getReligios());
        return "religious";
    }

    @PreAuthorize("isOwner(#id)")
    @DeleteMapping("/places/{id}")
    public String deletePlace(@PathVariable Long id) {
        placeService.deletePlace(id);

        return "redirect:/places/all";
    }


    @GetMapping("/places/{id}/place-details")
    public String showPlace(@PathVariable Long id, Model model) {

        model.addAttribute("place", this.placeService.findById(id));
        return "place-details";
    }


    @GetMapping("/places/{id}/edit")
    public String editPlace(@PathVariable Long id, Model model) {
//        if (!placeService.isOwner(principal.getName(), id)) {
//            throw new RuntimeException();
//        }

        PlaceDetailsViewModel placeDetailsViewModel = placeService.findById(id);
        PlaceUpdateBingdingModel placeModel = modelMapper.map(placeDetailsViewModel, PlaceUpdateBingdingModel.class);
        model.addAttribute("ratingEnum", RatingEnum.values());
        model.addAttribute("types", NameType.values());
        model.addAttribute("placeModel", placeModel);
        return "updatePlace";
    }

    @GetMapping("/places/{id}/edit/errors")
    public String editPlacesErrors(@PathVariable Long id, Model model) {
        model.addAttribute("ratingEnum", RatingEnum.values());
        model.addAttribute("types", NameType.values());
        return "updatePlace";
    }

    @PatchMapping("/places/{id}/edit")
    public String editPlace(@PathVariable Long id,
                            @Valid PlaceUpdateBingdingModel placeModel,
                            BindingResult bindingResult,
                            RedirectAttributes redirectAttributes, Principal principal) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("placeModel", placeModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.placeModel", bindingResult);
            return "redirect:/places/" + id + "/edit/errors";
        }

        PlaceUpdateServiceModel serviceModel = modelMapper.map(placeModel, PlaceUpdateServiceModel.class);
        serviceModel.setId(id);

        placeService.updatePlace(serviceModel);

        return "redirect:/places/" + id + "/place-details";
    }


    @GetMapping("/places/add")
    public String getAddCityPage(Model model) {

        if (!model.containsAttribute("placeAddBindModel")) {
            model.addAttribute("placeAddBindModel", new PlaceAddBindingModel())
                    .addAttribute("cities", cityService.getAllCities());
        }
        return "place-add";
    }

    @PostMapping("/places/add")
    public String addPlace(@Valid PlaceAddBindingModel placeAddBindingModel,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes,
                           @AuthenticationPrincipal ProjectUser user) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("placeAddBindingModel", placeAddBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.placeAddBindingModel", bindingResult);
//                    .addAttribute("places", placeService.getAllPlace());
            return "redirect:/places/add";
        }

        PlaceAddServiceModel placeAddServiceModel = placeService.addPlace(placeAddBindingModel, user.getUserIdentifier());
        return "redirect:/places/" + placeAddServiceModel.getId() + "/place-details";
    }
}
