package com.agileactors.usersproject.integritytest;


import org.apache.http.client.methods.HttpGet;

import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.AssertJUnit.assertEquals;

public class get200 extends BaseIntegrity {

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
