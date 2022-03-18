package com.example.examfinal.web;

import com.example.examfinal.model.binding.CityAddBindModel;
import com.example.examfinal.model.binding.CityUpdateBindingModel;
import com.example.examfinal.model.entity.enums.AreaOfBg;
import com.example.examfinal.model.service.CityAddServiceModel;
import com.example.examfinal.model.service.CityUpdateServiceModel;
import com.example.examfinal.model.view.CityDetailsView;
import com.example.examfinal.service.CityService;
import com.example.examfinal.service.impl.ProjectUser;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class CityController {
    private final CityService cityService;
    private final ModelMapper modelMapper;

    public CityController(CityService cityService, ModelMapper modelMapper) {
        this.cityService = cityService;
        this.modelMapper = modelMapper;
    }

    // GET
    @GetMapping("/cities/all")
    public String allCities(Model model) {
        model.addAttribute("cities", cityService.getAllCities());
        return "cities";
    }

    @GetMapping("/cities/{id}/details")
    public String showCity(@PathVariable Long id, Model model) {
        model.addAttribute("city", this.cityService.findById(id));
        return "details";
    }

    // DELETE
    @DeleteMapping("/cities/{id}")
    public String deleteCity(@PathVariable Long id) {
        cityService.deleteCity(id);

        return "redirect:/cities/all";
    }


    @GetMapping("/cities/{id}/edit")
    public String editCity(@PathVariable Long id, Model model, Principal principal) {
        if (!cityService.isOwner(principal.getName(), id)) {
            throw new RuntimeException();
        }
        CityDetailsView cityDetailsView = cityService.findById(id);
        CityUpdateBindingModel cityModel = modelMapper.map(cityDetailsView, CityUpdateBindingModel.class);
        model.addAttribute("areaOfBg", AreaOfBg.values());
        model.addAttribute("cityModel", cityModel);
        return "update";
    }

    @GetMapping("/cities/{id}/edit/errors")
    public String editCityErrors(@PathVariable Long id, Model model) {
        model.addAttribute("areaOfBg", AreaOfBg.values());
        return "update";
    }

    @PatchMapping("/cities/{id}/edit")
    public String editCity(
            @PathVariable Long id,
            @Valid CityUpdateBindingModel cityModel,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("cityModel", cityModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.cityModel", bindingResult);
            return "redirect:/cities/" + id + "/edit/errors";
        }

        CityUpdateServiceModel serviceModel = modelMapper.map(cityModel, CityUpdateServiceModel.class);
        serviceModel.setId(id);

        cityService.updateCity(serviceModel);

        return "redirect:/cities/" + id + "/details";
    }


    @GetMapping("/cities/add")
    public String getAddCityPage(Model model) {

        if (!model.containsAttribute("cityAddBindModel")) {
            model.addAttribute("cityAddBindModel", new CityAddBindModel());
        }
        return "city-add";
    }

    @PostMapping("/cities/add")
    public String addCity(@Valid CityAddBindModel cityAddBindModel,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes,
                          @AuthenticationPrincipal ProjectUser user) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("cityAddBindModel", cityAddBindModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.cityAddBindModel", bindingResult);
            return "redirect:/cities/add";
        }

        CityAddServiceModel cityAddServiceModel = cityService.addCity(cityAddBindModel, user.getUserIdentifier());
        return "redirect:/cities/" + cityAddServiceModel.getId() + "/details";
    }
}
