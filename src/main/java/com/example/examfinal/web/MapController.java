package com.example.examfinal.web;

import com.example.examfinal.service.PlaceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MapController {
  private final PlaceService placeService;

  public MapController(PlaceService placeService) {
    this.placeService = placeService;
  }


  @GetMapping("/map")
  public String map(Model model) {
    model.addAttribute("places", placeService.getAllPlace());
    return  "map";
  }



}
