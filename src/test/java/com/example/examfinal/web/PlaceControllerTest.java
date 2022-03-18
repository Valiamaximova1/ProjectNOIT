package com.example.examfinal.web;

import com.example.examfinal.repository.CityRepository;
import com.example.examfinal.repository.PlaceRepository;
import com.example.examfinal.service.CityService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WithMockUser("admin")
@SpringBootTest
@AutoConfigureMockMvc
public class PlaceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private CityService cityService;

    @AfterEach
    void tearDown() {
        placeRepository.deleteAll();
    }

    @Test
    void testOpenAllPlaces() throws Exception {
        mockMvc.
                perform(get("/places/all"))
                .andExpect(status().isOk())
                .andExpect(view().name("places"));
//        mockMvc.perform(get("/cities/all").with(user("admin").roles("ADMIN"))).
//                andExpect(status().isOk());
    }


}
