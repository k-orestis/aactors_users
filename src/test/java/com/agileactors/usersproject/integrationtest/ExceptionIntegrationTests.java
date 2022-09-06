package com.agileactors.usersproject.integrationtest;

import com.agileactors.usersproject.models.User;
import com.github.database.rider.junit5.DBUnitExtension;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.Assert.assertEquals;


@ExtendWith({DBUnitExtension.class, SpringExtension.class})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestPropertySource(locations = "classpath:application_test.properties")
class ExceptionIntegrationTests extends BaseIntegrity{

   public static TestRestTemplate testRestTemplate;

    @BeforeAll
    public static void set(){
       testRestTemplate = new TestRestTemplate();
    }

    @Test
    public void getByInvalidId(){
        ResponseEntity<String> response = testRestTemplate.
              getForEntity(BASE_ENDPOINT + "7", String.class);

        assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
        assertEquals(response.getBody(), "Invalid ID" );
    }
    @Test
    public void deleteByInvalidId(){
        HttpEntity<String> request = new HttpEntity<String>("");
        ResponseEntity<?> response = testRestTemplate
                .exchange(BASE_ENDPOINT + "7", HttpMethod.DELETE, request, String.class);


        assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
        assertEquals(response.getBody(), "Invalid ID" );
    }

    @Test
    public void postWithInvalidMailFormat(){
        user.setMail("aaaa");
        HttpEntity<User> request = new HttpEntity<>(user);
        ResponseEntity<?> response = testRestTemplate
                .exchange(BASE_ENDPOINT , HttpMethod.POST, request, String.class);


        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
        assertEquals(response.getBody(), user.getMail() + " has wrong format" );
    }

    @Test
    public void postWithExistingMail(){
        user.setMail("annaprat@mail.com");
        HttpEntity<User> request = new HttpEntity<>(user);
        ResponseEntity<?> response = testRestTemplate
                .exchange(BASE_ENDPOINT , HttpMethod.POST, request, String.class);


        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
        assertEquals(response.getBody(), user.getMail() + " already exists" );
    }

    @Test
    public void postWithNotAllFieldsFilled(){
        user.setAge(0);
        HttpEntity<User> request = new HttpEntity<>(user);
        ResponseEntity<?> response = testRestTemplate
                .exchange(BASE_ENDPOINT , HttpMethod.POST, request, String.class);


        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
        assertEquals(response.getBody(), "All fields required" );
    }

    @Test
    public void putWithInvalidMailFormat(){
        user.setMail("aaaa");
        HttpEntity<User> request = new HttpEntity<>(user);
        ResponseEntity<?> response = testRestTemplate
                .exchange(BASE_ENDPOINT + "5", HttpMethod.PUT, request, String.class);


        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
        assertEquals(response.getBody(), user.getMail() + " has wrong format" );
    }

    @Test
    public void putWithExistingMail(){
        user.setMail("annaprat@mail.com");
        HttpEntity<User> request = new HttpEntity<>(user);
        ResponseEntity<?> response = testRestTemplate
                .exchange(BASE_ENDPOINT + "5", HttpMethod.PUT, request, String.class);


        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
        assertEquals(response.getBody(), user.getMail() + " already exists" );
    }

    @Test
    public void putWithNotAllFieldsFilled(){
        user.setAge(0);
        HttpEntity<User> request = new HttpEntity<>(user);
        ResponseEntity<?> response = testRestTemplate
                .exchange(BASE_ENDPOINT + "5", HttpMethod.PUT, request, String.class);


        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
        assertEquals(response.getBody(), "All fields required" );
    }

}
