package com.agileactors.usersproject.controllers;

import com.agileactors.usersproject.models.User;
import com.agileactors.usersproject.repositories.UsersRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RestController;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
class UsersControllerTest {

    @Autowired
    private UsersRepository usersRepository;
    @BeforeEach
    void init(){

    }

    @Test
    void list() {
        usersRepository.save(new User(8L, "LLLL", "AAAA", 12));
    }

    @Test
    void get() {
    }

    @Test
    void create() {
    }
}