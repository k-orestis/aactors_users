package com.agileactors.usersproject.controllers;

import com.agileactors.usersproject.integrationtest.BaseIntegrity;
import com.agileactors.usersproject.integrationtest.ResponseUtils;
import com.agileactors.usersproject.models.User;
import com.agileactors.usersproject.service.UsersService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application_test.properties")
public class UsersControllerTest extends BaseIntegrity {
    @MockBean
    UsersService usersService;
    @Autowired
    MockMvc mockMvc;

    @Test
    public void get() throws Exception {
        User user = new User(7L, "Stavros", "Kosmapetris", 22, "stavroskosm@mail.com");
        List<User> userList = List.of(user, user, user);
        doReturn(userList).when(usersService).findAll();

        mockMvc.perform(MockMvcRequestBuilders.get(BASE_ENDPOINT))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void getById() throws Exception {
        User user = new User(7L, "Stavros", "Kosmapetris", 22,  "stavroskosm@mail.com");
        doReturn(true).when(usersService).existsById(any());
        doReturn(user).when(usersService).getOne(any());

        mockMvc.perform(MockMvcRequestBuilders.get(BASE_ENDPOINT+"5"))
                .andExpect(status().isOk())
                .andExpect( content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.firstName").value("Stavros"))
                .andExpect(jsonPath("$.lastName").value("Kosmapetris"))
                .andExpect(jsonPath("$.age").value(22));
    }

    @Test
    public void getNotFound() throws Exception{
        doReturn(false).when(usersService).existsById(5L);
        mockMvc.perform(MockMvcRequestBuilders.get(BASE_ENDPOINT + "5"))
                .andExpect(status().isNotFound());

    }

    @Test
    public void post() throws Exception{
        User postUser = new User(7L, "Stavros", "Kosmapetris", 22, "stavroskosm@mail.com");
        User mockUser = new User(7L, "Stavros", "Kosmapetris", 22,  "stavroskosm@mail.com");
        doReturn(mockUser).when(usersService).save(any());
        mockMvc.perform(MockMvcRequestBuilders.post(BASE_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(ResponseUtils.asJsonString(postUser)))
                .andExpect(status().isCreated())
                .andExpect( content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.firstName").value("Stavros"))
                .andExpect(jsonPath("$.lastName").value("Kosmapetris"))
                .andExpect(jsonPath("$.age").value(22));

    }
    @Test
    public void put() throws Exception{
        User putUser = new User(7L, "Stratos", "Kosmapetris", 32,  "stavroskosm@mail.com");
        User mockUser = new User(7L, "Stavros", "Kosmapetris", 22, "stavroskosm@mail.com");
        doReturn(true).when(usersService).existsById(any());
        doReturn(putUser).when(usersService).update(any(), any());
        mockMvc.perform(MockMvcRequestBuilders.put(BASE_ENDPOINT + "7")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ResponseUtils.asJsonString(putUser)))
                .andExpect(status().isOk())
                .andExpect( content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.firstName").value("Stratos"))
                .andExpect(jsonPath("$.lastName").value("Kosmapetris"))
                .andExpect(jsonPath("$.age").value(32));

    }
    @Test
    public void putNotFound() throws Exception{
        User putUser = new User(7L, "Stratos", "Kosmapetris", 32, "stavroskosm@mail.com");
        doReturn(false).when(usersService).existsById(any());
        mockMvc.perform(MockMvcRequestBuilders.put(BASE_ENDPOINT + "5")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ResponseUtils.asJsonString(putUser)))
                .andExpect(status().isNotFound());

    }

    @Test
    public void delete() throws Exception {
        doReturn(true).when(usersService).existsById(any());
        mockMvc.perform(MockMvcRequestBuilders.delete(BASE_ENDPOINT + "5"))
                .andExpect(status().isOk());

    }
    @Test
    public void deleteNotFound() throws Exception {
        doReturn(false).when(usersService).existsById(any());
        mockMvc.perform(MockMvcRequestBuilders.delete(BASE_ENDPOINT + "5"))
                .andExpect(status().isNotFound());

    }



}
