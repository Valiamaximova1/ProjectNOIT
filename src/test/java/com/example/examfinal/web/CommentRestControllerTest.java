package com.example.examfinal.web;

import com.example.examfinal.model.binding.NewCommentBindingModel;
import com.example.examfinal.model.entity.Comment;
import com.example.examfinal.model.entity.PlaceEntity;
import com.example.examfinal.model.entity.UserEntity;
import com.example.examfinal.repository.PlaceRepository;
import com.example.examfinal.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.text.MatchesPattern;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WithMockUser("valia")
@SpringBootTest
@AutoConfigureMockMvc
class CommentRestControllerTest {

  private static final String COMMENT_1 = "hey Spring is cool!";
  private static final String COMMENT_2 = "Well... it is a bit trick sometimes... :(";

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private PlaceRepository placeRepository;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private ObjectMapper objectMapper;

  private UserEntity testUser;

  @BeforeEach
  void setUp() {
    testUser = new UserEntity();
    testUser.setPassword("password");
    testUser.setUsername("lucho");
    testUser.setFirstName("Valentina");
    testUser.setLastName("Maximova");

    testUser = userRepository.save(testUser);
  }

  @AfterEach
  void tearDown() {
    placeRepository.deleteAll();
    userRepository.deleteAll();
  }

  @Test
  void testGetComments() throws Exception {
    var place = initComments(initPlace());

    mockMvc.perform(get("/api/" + place.getId() + "/comments")).
        andExpect(status().isOk()).
        andExpect(jsonPath("$", hasSize(2))).
        andExpect(jsonPath("$.[0].message", is(COMMENT_1))).
        andExpect(jsonPath("$.[1].message", is(COMMENT_2)));
  }

  @Test
  void testCreateComments() throws Exception {
    NewCommentBindingModel testComment = new NewCommentBindingModel().
        setMessage(COMMENT_1);

    var emptyRoute = initPlace();

    mockMvc.perform(
        post("/api/" + emptyRoute.getId() + "/comments")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(testComment))
            .accept(MediaType.APPLICATION_JSON)
            .with(csrf())
    )
            .andExpect(status().isCreated())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(header().string("Location", MatchesPattern.matchesPattern("/api/" + emptyRoute.getId() + "/comments/\\d")))
            .andExpect(jsonPath("$.message").value(is(COMMENT_1)));

  }

  private PlaceEntity initPlace() {
    PlaceEntity testPlace = new PlaceEntity();
    testPlace.setName("Testing route");

    return placeRepository.save(testPlace);
  }

  private PlaceEntity initComments(PlaceEntity place) {

    Comment comment1 = new Comment();
    comment1.setCreated(Instant.now());
    comment1.setUser(testUser);
    comment1.setTextContent(COMMENT_1);
    comment1.setApproved(true);
    comment1.setPlace(place);

    Comment comment2 = new Comment();
    comment2.setCreated(Instant.now());
    comment2.setUser(testUser);
    comment2.setTextContent(COMMENT_2);
    comment2.setApproved(true);
    comment2.setPlace(place);

    place.setComments(List.of(comment1, comment2));

    return placeRepository.save(place);
  }
}
