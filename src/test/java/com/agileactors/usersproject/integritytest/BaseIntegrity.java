package com.agileactors.usersproject.integritytest;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;

public class BaseIntegrity {
    public static final String BASE_ENDPOINT = "http://localhost:8080/api/users/";
    CloseableHttpClient client;
    CloseableHttpResponse httpResponse;

    @BeforeMethod
    public void setup(){
        client = HttpClientBuilder.create().build();
        System.out.println("aaa");
    }

    @AfterMethod
    public void closeResources() throws IOException {
        client.close();
        httpResponse.close();
    }

}
