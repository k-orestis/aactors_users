package com.agileactors.usersproject.integrationtest;

import com.agileactors.usersproject.models.User;

import com.github.database.rider.junit5.DBUnitExtension;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith({DBUnitExtension.class, SpringExtension.class})
@SpringBootTest
@TestPropertySource(locations = "classpath:application_test.properties")
@AutoConfigureMockMvc
public class UsersIntegrationTest extends BaseIntegrity{
    @Autowired
    private MockMvc mockMvc;

    @Nested
    class TestSaveDelete {
        @BeforeEach
        public void init(){
            User nUser = usersService.saveAndFlush(user);
            id = nUser.getUserId();
        }
        @Test
        public void getById() throws Exception {


            mockMvc.perform(MockMvcRequestBuilders.get(BASE_ENDPOINT+String.valueOf(id)))
                    .andExpect(status().isOk())
                    .andExpect( content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.firstName").value("Stratos"))
                    .andExpect(jsonPath("$.lastName").value("Kosmapetris"))
                    .andExpect(jsonPath("$.age").value(22));
        }
        @Test
        public void put() throws Exception{

            User userUpdated = new User(id, "Stavros", "Kosmapetris", 32, "stavroskosmm@mail.com");

            mockMvc.perform(MockMvcRequestBuilders.put(BASE_ENDPOINT + String.valueOf(id))
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(ResponseUtils.asJsonString(userUpdated)))
                    .andExpect(status().isOk())
                    .andExpect( content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.firstName").value("Stavros"))
                    .andExpect(jsonPath("$.lastName").value("Kosmapetris"))
                    .andExpect(jsonPath("$.age").value(32));

        }
        @AfterEach
        public void tearDown(){
            usersService.deleteById(id);
        }

    }



    @Test
    public void getNotFound() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders.get(BASE_ENDPOINT + "1000"))
                .andExpect(status().isNotFound());

    }

    @Test
    public void post() throws Exception{
        user = new User(22L, "Stratos", "Kosmapetris", 22, "stratoskosmas@mail.com");
        mockMvc.perform(MockMvcRequestBuilders.post(BASE_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ResponseUtils.asJsonString(user)))
                .andExpect(status().isCreated())
                .andExpect( content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.firstName").value("Stratos"))
                .andExpect(jsonPath("$.lastName").value("Kosmapetris"))
                .andExpect(jsonPath("$.age").value(22));

        usersService.deleteById(usersService.findAll()
                .stream().map(User::getUserId)
                .max(Long::compare).get());

    }



    @Test
    public void putNotFound() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders.put(BASE_ENDPOINT + "1000")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ResponseUtils.asJsonString(user)))
                .andExpect(status().isNotFound());

    }

    @Test
    public void delete() throws Exception {
        User nUser = usersService.saveAndFlush(user);
        id = nUser.getUserId();
        mockMvc.perform(MockMvcRequestBuilders.delete(BASE_ENDPOINT + String.valueOf(id)))
                .andExpect(status().isOk());

    }
    @Test
    public void deleteNotFound() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.delete(BASE_ENDPOINT + "1000"))
                .andExpect(status().isNotFound());

    }

}
