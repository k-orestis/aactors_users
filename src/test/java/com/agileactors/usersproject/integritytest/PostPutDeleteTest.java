package com.agileactors.usersproject.integritytest;

import com.agileactors.usersproject.models.User;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;

import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.assertEquals;

public class PostPutDeleteTest extends BaseIntegrity{

        @Test(priority = 1)
        public void post() throws IOException {             //POST test

            HttpPost request = new HttpPost(BASE_ENDPOINT);

            String json = "{\"first_name\":\"Manos\",\"last_name\":\"Manou\", \"age\":19}";
            request.setEntity(new StringEntity(json, ContentType.APPLICATION_JSON));

            httpResponse = client.execute(request);

            int actualStatus = httpResponse.getStatusLine().getStatusCode();
            assertEquals(actualStatus, 200);

            User user = ResponseUtils.unmarshall(httpResponse, User.class);
            assertEquals(user.getFirst_name(), "Manos");
            assertEquals(user.getLast_name(), "Manou");
            assertEquals(user.getAge(), 19);
        }

        @Test(priority = 2)
        public void put() throws IOException {              //PUT test
            HttpPut request = new HttpPut(BASE_ENDPOINT + "7");

            String json = "{\"first_name\":\"Eric\",\"last_name\":\"Koutsopoulos\", \"age\":25}";
            request.setEntity(new StringEntity(json, ContentType.APPLICATION_JSON));

            httpResponse = client.execute(request);

            int actualStatus = httpResponse.getStatusLine().getStatusCode();

            User user = ResponseUtils.unmarshall(httpResponse, User.class);

            assertEquals(actualStatus, 200);
            assertEquals(user.getFirst_name(), "Eric");
            assertEquals(user.getLast_name(), "Koutsopoulos");
            assertEquals(user.getAge(), 25);
        }

        @Test(priority = 3)
        public void delete() throws IOException {           //DELETE test

            HttpDelete httpDelete = new HttpDelete(BASE_ENDPOINT + "7");

            httpResponse = client.execute(httpDelete);

            int actualStatus = httpResponse.getStatusLine().getStatusCode();
            assertEquals(actualStatus, 200);
        }


}
