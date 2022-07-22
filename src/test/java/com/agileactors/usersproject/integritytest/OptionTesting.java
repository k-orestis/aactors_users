package com.agileactors.usersproject.integritytest;


import org.apache.http.client.methods.HttpOptions;

import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.assertEquals;

public class OptionTesting extends BaseIntegrity{


    @Test
    public void optionTest() throws IOException {
        String expectedHeader = "GET,HEAD,POST,OPTIONS";
        String header = "Allow";
        HttpOptions request = new HttpOptions(BASE_ENDPOINT);
        httpResponse = client.execute(request);
        assertEquals(ResponseUtils.getHeader(httpResponse, header), expectedHeader);
    }

}
