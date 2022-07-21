package com.agileactors.usersproject.controllers;

import com.agileactors.usersproject.models.User;
import com.agileactors.usersproject.repositories.UsersRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;


class UsersControllerTest {

    public static final long USER_ID = 22L;
    private UsersController usersController;
    private UsersRepository usersRepository = mock(UsersRepository.class);
    private User user;
    @BeforeEach
    void setUp(){
        usersController = new UsersController(usersRepository);
        user = new User(10L, "John", "Alexiou", 23);
    }

    @Test
    void list() {
        List<User> userL = List.of(user, user, user);
        when(usersRepository.findAll()).thenReturn(userL);
        List<User> userList = usersController.list();
        assertEquals(userL.get(1).getFirst_name(), userList.get(1).getFirst_name());
        assertEquals(userL.get(0).getAge(), userList.get(0).getAge());
        assertEquals(userL.get(0).getLast_name(), userList.get(0).getLast_name());

    }

    @Test
    void get() {
        when(usersRepository.getOne(USER_ID)).thenReturn(user);
        User user1 = usersController.get(USER_ID);
        assertEquals(user.getFirst_name(), user1.getFirst_name());
        assertEquals(user.getAge(), user1.getAge());
        assertEquals(user.getLast_name(), user1.getLast_name());
    }

    @Test
    void create() {
        when(usersRepository.saveAndFlush(anyObject())).thenReturn(user);
        User user1 = usersController.create(user);
        assertEquals(user.getFirst_name(), user1.getFirst_name());
        assertEquals(user.getAge(), user1.getAge());
        assertEquals(user.getLast_name(), user1.getLast_name());
    }

    @Test
    void delete(){
        usersController.delete(USER_ID);
        verify(usersRepository).deleteById(anyLong());
    }
    @Test
    void update(){
        User nUser = new User(23L, "Giannis", "Alexopoulos", 24);
        when(usersRepository.getById(USER_ID)).thenReturn(user);
        when(usersRepository.saveAndFlush(user)).thenReturn(nUser);

        User user1 = usersController.update(USER_ID, user);

        assertEquals(nUser.getFirst_name(), user1.getFirst_name());
        assertEquals(nUser.getAge(), user1.getAge());
        assertEquals(nUser.getLast_name(), user1.getLast_name());

    }


}