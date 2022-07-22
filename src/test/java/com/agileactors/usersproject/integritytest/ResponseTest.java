package com.agileactors.usersproject.integritytest;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ResponseTest extends BaseIntegrity {

    @Ignore
    @Test
    public void serverIsUsers() throws IOException {
        HttpGet get = new HttpGet(BASE_ENDPOINT);
        httpResponse = client.execute(get);
        assertEquals(BASE_ENDPOINT, ResponseUtils.getHeader(httpResponse, "Server"));

    }
    @Ignore
    @Test
    public void ETag() throws IOException{
        HttpGet get = new HttpGet(BASE_ENDPOINT);
        httpResponse = client.execute(get);
        assertTrue(ResponseUtils.isPresent(httpResponse, "ETag"));
    }
}
