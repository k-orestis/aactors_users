package com.agileactors.usersproject.repository;

import com.agileactors.usersproject.integrationtest.BaseIntegrity;
import com.agileactors.usersproject.models.User;
import com.agileactors.usersproject.repositories.UsersRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestPropertySource(locations = "classpath:application_test.properties")
public class UsersRepositoryTest extends BaseIntegrity {

    @Autowired
    private UsersRepository usersRepository;

    @Test
    void testFindAll() {
        List<User> users = usersRepository.findAll();
        assertEquals(6, users.size(), "We should have 6 users!");
    }

    @Test
    void testFindByIdSuccess() {
         Optional<User> user = usersRepository.findById(2L);
        assertEquals(2, user.get().getUser_id().intValue());
        assertEquals("Anna", user.get().getFirst_name());
        assertEquals("Pratikaki", user.get().getLast_name());
        assertEquals(34, user.get().getAge());
    }


    @Test
    void testSave() {
        User user = new User(7L, "Stavros", "Kosmapetraros", 28, "stavroskosma@mail.com");
        User newUser = usersRepository.saveAndFlush(user);
        assertEquals("Stavros", newUser.getFirst_name());
        assertEquals("Kosmapetraros", newUser.getLast_name());
        assertEquals(28, newUser.getAge());
        usersRepository.deleteById(newUser.getUser_id());
    }

    @Test
    void testDelete() {
        User user = new User(8L, "Giorgos", "Nikou", 32, "giorgosnikou@mail.com");
        User newUser = usersRepository.saveAndFlush(user);
        usersRepository.deleteById(newUser.getUser_id());
        assertFalse(usersRepository.existsById(newUser.getUser_id()));
    }
}