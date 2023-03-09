package com.travel.blog;

import com.travel.blog.repositories.CategoriesRepository;
import com.travel.blog.repositories.CommentRepository;
import com.travel.blog.repositories.TravelRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class TravelBlogApiApplicationTests {
    @Autowired
    MockMvc mvc;
    @Autowired
    TravelRepository travelRepository;
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    CategoriesRepository categoriesRepository;

    @Test
    void contextLoads() {
        assert travelRepository.count() == 3;
        assert commentRepository.count() == 3;
        assert categoriesRepository.count() == 3;
    }

    @Test
    void getAll() throws Exception {
        mvc.perform(get("/api/travels").contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].title").value("Travel to Paris"))
                .andExpect(jsonPath("$[1].title").value("Travel to Italy"))
                .andExpect(jsonPath("$[2].title").value("Travel to Spain"));
        mvc.perform(get("/api/comments").contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].comment").value("This is a comment"))
                .andExpect(jsonPath("$[1].comment").value("I enjoyed this travel"))
                .andExpect(jsonPath("$[2].comment").value("It was very disappointing"));
        mvc.perform(get("/api/categories").contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].name").value("Romantic"))
                .andExpect(jsonPath("$[1].name").value("Adventure"))
                .andExpect(jsonPath("$[2].name").value("Family"));
    }

    @Test
    void getById() throws Exception {
        mvc.perform(get("/api/travels/1").contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title").value("Travel to Paris"));
        mvc.perform(get("/api/categories/1").contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Romantic"));
    }

    @Test
    void create() throws Exception {
        mvc.perform(post("/api/travels").contentType("application/json")
                .content("{\"title\": \"Travel to London\", \"description\": \"This is a description\", \"date\": \"2020-01-01\", \"image\": \"https://www.google.com\", \"location\": \"London\" }"))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title").value("Travel to London"));
        mvc.perform(post("/api/categories").contentType("application/json")
                .content("{\"name\": \"Business\"}"))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Business"));
        mvc.perform(post("/api/travels/1/comments").contentType("application/json")
                .content("{\"comment\": \"This is a comment\", \"email\": \"david@david.es\", \"name\": \"David\"}"))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.comment").value("This is a comment"))
                .andExpect(jsonPath("$.email").value("david@david.es"))
                .andExpect(jsonPath("$.name").value("David"));
    }
}
