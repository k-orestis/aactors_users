package com.agileactors.usersproject.integritytest;


import com.agileactors.usersproject.models.User;
import com.agileactors.usersproject.service.UsersService;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.assertEquals;


public class getById {
    public static final String BASE_ENDPOINT = "http://localhost:8080/api/users/";

    private UsersService usersService;
    CloseableHttpClient client;
    CloseableHttpResponse httpResponse;

    @BeforeMethod
    public void setup(){
        client = HttpClientBuilder.create().build();
    }

    @AfterMethod
    public void closeResources() throws IOException {
        client.close();
        httpResponse.close();
    }

    @Test
    public void getById() throws IOException {
        HttpGet get = new HttpGet(BASE_ENDPOINT + "4");
        httpResponse = client.execute(get);
        User user = ResponseUtils.unmarshall(httpResponse, User.class);
        //assertEquals(user.getFirst_name(), usersService.getOne(4L).getFirst_name());
        assertEquals(user.getFirst_name(), "Nikos");
    }
}
