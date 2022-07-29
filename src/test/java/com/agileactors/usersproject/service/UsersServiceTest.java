package com.agileactors.usersproject.service;

import com.agileactors.usersproject.models.User;
import com.agileactors.usersproject.repositories.UsersRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyObject;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

class UsersServiceTest {
    public static final long USER_ID = 22L;
    private UsersService usersService;

    private UsersRepository usersRepository = mock(UsersRepository.class);

    private User user;
    @BeforeEach
    void setUp(){
        usersService = new UsersService(usersRepository);
        user = new User(10L, "John", "Alexiou", 23, "johnalex@mail.com");
    }

    @Test
    void findAll() {
        List<User> userL = List.of(user, user, user);
        when(usersRepository.findAll()).thenReturn(userL);
        List<User> userList = usersService.findAll();
        assertEquals(userL.get(1).getFirst_name(), userList.get(1).getFirst_name());
        assertEquals(userL.get(0).getAge(), userList.get(0).getAge());
        assertEquals(userL.get(0).getLast_name(), userList.get(0).getLast_name());

    }

    @Test
    void get() {
        when(usersRepository.getOne(USER_ID)).thenReturn(user);
        User user1 = usersService.getOne(USER_ID);
        assertEquals(user.getFirst_name(), user1.getFirst_name());
        assertEquals(user.getAge(), user1.getAge());
        assertEquals(user.getLast_name(), user1.getLast_name());
    }

    @Test
    void create() {
        when(usersRepository.saveAndFlush(anyObject())).thenReturn(user);
        User user1 = usersService.saveAndFlush(user);
        assertEquals(user.getFirst_name(), user1.getFirst_name());
        assertEquals(user.getAge(), user1.getAge());
        assertEquals(user.getLast_name(), user1.getLast_name());
    }

    @Test
    void delete(){
        usersService.deleteById(USER_ID);
        verify(usersRepository).deleteById(anyLong());
    }
    @Test
    void update(){
        User nUser = new User(23L, "Giannis", "Alexopoulos", 24, "giannisalex@mail.com");
        when(usersRepository.getOne(USER_ID)).thenReturn(user);
        when(usersRepository.save(user)).thenReturn(nUser);

        User user1 = usersService.update(USER_ID, user);

        assertEquals(nUser.getFirst_name(), user1.getFirst_name());
        assertEquals(nUser.getAge(), user1.getAge());
        assertEquals(nUser.getLast_name(), user1.getLast_name());

    }

}