package com.agileactors.usersproject.integritytest;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.AssertJUnit.assertEquals;

public class get200 {
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
    @Test
    public void baseReturns200() throws IOException {
        HttpGet get = new HttpGet(BASE_ENDPOINT);
         httpResponse = client.execute(get);
       int status = httpResponse.getStatusLine().getStatusCode();
        assertEquals(status, 200);

    }
    @Test
    public void baseIdReturns200() throws IOException {
        HttpGet get = new HttpGet(BASE_ENDPOINT + "5");
        httpResponse = client.execute(get);
        int status = httpResponse.getStatusLine().getStatusCode();
        assertEquals(status, 200);

    }


}
