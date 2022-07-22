package com.agileactors.usersproject.integritytest;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ResponseUtils {
    public static String getHeader(CloseableHttpResponse response, String headerName){
        List<Header> headerList = Arrays.asList(response.getAllHeaders());
        Header head = headerList.stream().filter(header -> headerName.equalsIgnoreCase(header.getName()))
                .findFirst().orElseThrow(()->new RuntimeException("Didn't find the header"));
        return head.getValue();
    }

    public static boolean isPresent(CloseableHttpResponse response, String headerName){
        return Arrays.asList(response.getAllHeaders()).stream()
                .anyMatch(header -> headerName.equalsIgnoreCase(header.getName()));
    }

    public static <T> T unmarshall(CloseableHttpResponse response, Class<T> clazz) throws IOException {
        String jsonBody = EntityUtils.toString(response.getEntity());
        return new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false)
                .readValue(jsonBody, clazz);
   }

}
