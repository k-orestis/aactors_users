package com.agileactors.usersproject.integritytest;

import com.agileactors.usersproject.models.User;
import org.apache.http.client.methods.HttpGet;
import org.testng.annotations.Test;
import java.io.IOException;
import static org.testng.Assert.assertEquals;


public class getById extends BaseIntegrity{


    @Test
    public void getById() throws IOException {


        HttpGet get = new HttpGet(BASE_ENDPOINT + "4");
        httpResponse = client.execute(get);
        User user = ResponseUtils.unmarshall(httpResponse, User.class);

        //assertEquals(user.getFirst_name(), usersService.getOne(4L).getFirst_name());
        assertEquals(user.getFirst_name(), "Nikos");
    }
}
