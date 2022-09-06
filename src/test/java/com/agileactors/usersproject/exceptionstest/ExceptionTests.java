package com.agileactors.usersproject.exceptionstest;

import com.agileactors.usersproject.controllers.UsersController;
import com.agileactors.usersproject.exceptions.InvalidIdException;
import com.agileactors.usersproject.exceptions.InvalidPostBodyException;
import com.agileactors.usersproject.exceptions.MailAlreadyExistsException;
import com.agileactors.usersproject.exceptions.WrongMailFormatException;
import com.agileactors.usersproject.integrationtest.BaseIntegrity;
import com.agileactors.usersproject.models.User;
import com.agileactors.usersproject.service.UsersService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

@SpringBootTest
@TestPropertySource(locations = "classpath:application_test.properties")
public class ExceptionTests extends BaseIntegrity {

    @Autowired
    private UsersController usersController;
    @Test
    void getByInvalidId(){
        assertThrows(InvalidIdException.class,
               ()-> usersController.get(7L));

    }
    @Test
    void postInvalidBody(){
        assertThrows(DataAccessException.class, ()-> usersController.create(
                new User(7L, "Nikos", "Nikou", 22, null)
        ));
        assertThrows(InvalidPostBodyException.class, ()-> usersController.create(
                new User(7L, "Nikos", "Nikou", 0, "nikolakis@mail.com")
        ));
    }

    @Test
    void mailAlreadyExists() {
        MailAlreadyExistsException ex = assertThrows(MailAlreadyExistsException.class,
                () -> usersController.create(new User(7L, "Nikos", "Nikou", 18, "nikonlakis@mail.com")));

        assertEquals("nikonlakis@mail.com", ex.getMessage());
    }
    @Test
    void wrongMailFormat() {
        WrongMailFormatException ex = assertThrows(WrongMailFormatException.class,
                () -> usersController.create(new User(7L, "Nikos", "Nikou", 18, "@mail.com")));

        assertEquals("@mail.com", ex.getMessage());
    }

    @Test
    void deleteByInvalidId(){
        assertThrows(InvalidIdException.class, ()-> usersController.delete(7L));
    }
    @Test
    void putByInvalidId(){
        assertThrows(InvalidIdException.class, ()-> usersController.update(7L,new User(7L,
                "Nikos", "Nikou", 18, "nikonlakis@mail.com") ));
    }
}
