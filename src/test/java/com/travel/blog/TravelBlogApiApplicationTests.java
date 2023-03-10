package com.travel.blog;

import com.travel.blog.repositories.CategoriesRepository;
import com.travel.blog.repositories.CommentRepository;
import com.travel.blog.repositories.TravelRepository;
import com.travel.blog.repositories.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.TestInstance;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS) // to use @BeforeAll annotation
class TravelBlogApiApplicationTests {
    @Autowired
    MockMvc mvc;
    @Autowired
    TravelRepository travelRepository;
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    CategoriesRepository categoriesRepository;
    @Autowired
    UserRepository userRepository;

    @Test
    @Transactional
    void contextLoads() {
        assert travelRepository.count() == 3;
        assert commentRepository.count() == 3;
        assert categoriesRepository.count() == 3;
        assert userRepository.count() == 2;
    }

    @Test
    @Transactional
    void rootWhenUnauthenticatedThen401() throws Exception {
        this.mvc.perform(get("/"))
                .andExpect(status().isUnauthorized());
    }

    private String token; // JWT token
    @BeforeAll
    void getToken() throws Exception{
        MvcResult result=this.mvc.perform(post("/token").with(httpBasic("Doe","123456789")))
                .andExpect(status().isOk())
                .andReturn();

        this.token= result.getResponse().getContentAsString();
    }

    @Test
    @Transactional
    void getAll() throws Exception {
        mvc.perform(get("/api/travels").header("Authorization","Bearer "+token).contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].title").value("Travel to Paris"))
                .andExpect(jsonPath("$[1].title").value("Travel to Italy"))
                .andExpect(jsonPath("$[2].title").value("Travel to Spain"));
        mvc.perform(get("/api/comments").header("Authorization","Bearer "+token).contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].comment").value("This is a comment"))
                .andExpect(jsonPath("$[1].comment").value("I enjoyed this travel"))
                .andExpect(jsonPath("$[2].comment").value("It was very disappointing"));
        mvc.perform(get("/api/categories").header("Authorization","Bearer "+token).contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].name").value("Romantic"))
                .andExpect(jsonPath("$[1].name").value("Adventure"))
                .andExpect(jsonPath("$[2].name").value("Family"));
    }

    @Test
    @Transactional
    void getById() throws Exception {
        mvc.perform(get("/api/travels/1").header("Authorization","Bearer "+token).contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title").value("Travel to Paris"));
        mvc.perform(get("/api/categories/1").header("Authorization","Bearer "+token).contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Romantic"));
    }

    @Test
    @Transactional
    void create() throws Exception {
        mvc.perform(post("/api/travels").header("Authorization","Bearer "+token).contentType("application/json")
                .content("{\"title\": \"Travel to London\", \"description\": \"This is a description\", \"date\": \"2020-01-01\", \"image\": \"https://www.google.com\", \"location\": \"London\" }"))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title").value("Travel to London"));
        mvc.perform(post("/api/categories").header("Authorization","Bearer "+token).contentType("application/json")
                .content("{\"name\": \"Business\"}"))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Business"));
        mvc.perform(post("/api/travels/1/comments").header("Authorization","Bearer "+token).contentType("application/json")
                .content("{\"comment\": \"This is a comment\", \"email\": \"david@david.es\", \"name\": \"David\"}"))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.comment").value("This is a comment"))
                .andExpect(jsonPath("$.email").value("david@david.es"))
                .andExpect(jsonPath("$.name").value("David"));
    }

    @Test
    @Transactional
    void update() throws Exception {
        String testTravel = "{\"title\": \"Travel to London\", \"description\": \"This is a description\", \"date\": \"2020-01-01\", \"image\": \"https://www.google.com\", \"location\": \"London\" }";
        mvc.perform(put("/api/travels/1").header("Authorization","Bearer "+token).contentType("application/json")
                .content(testTravel))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title").value("Travel to London"))
                .andExpect(jsonPath("$.description").value("This is a description"))
                .andExpect(jsonPath("$.date").value("2020-01-01T00:00:00.000+00:00"))
                .andExpect(jsonPath("$.image").value("https://www.google.com"))
                .andExpect(jsonPath("$.location").value("London"));

        String testCategory = "{\"name\": \"Business\"}";
        mvc.perform(put("/api/categories/1").header("Authorization","Bearer "+token).contentType("application/json")
                .content(testCategory))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Business"));

        String testComment = "{\"comment\": \"This is a comment\", \"email\": \"david@david.es\", \"name\": \"David\"}";
        mvc.perform(put("/api/travels/1/comments/1").header("Authorization","Bearer "+token).contentType("application/json")
                .content(testComment))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.comment").value("This is a comment"))
                .andExpect(jsonPath("$.email").value("david@david.es"))
                .andExpect(jsonPath("$.name").value("David"));
    }

    @Test
    @Transactional
    void delete() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete("/api/travels/2").header("Authorization","Bearer "+token).contentType("application/json"))
                .andExpect(status().isOk());
        assert travelRepository.count() == 2;
        mvc.perform(MockMvcRequestBuilders.delete("/api/travels/1/comments/1").header("Authorization","Bearer "+token).contentType("application/json"))
                .andExpect(status().isOk());
        assert commentRepository.count() == 1; //1 because travel 2 is deleted in previous test which has 1 comment
        mvc.perform(MockMvcRequestBuilders.delete("/api/categories/1").header("Authorization","Bearer "+token).contentType("application/json"))
                .andExpect(status().isOk());
        assert categoriesRepository.count() == 2;
    }
}
