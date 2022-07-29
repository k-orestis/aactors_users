package com.agileactors.usersproject.integrationtest;

import com.agileactors.usersproject.models.User;
import com.agileactors.usersproject.service.UsersService;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseIntegrity {
    public static final String BASE_ENDPOINT = "http://localhost:8080/api/users/";

    @Autowired
    protected UsersService usersService;
    public Long id;
    User user;
    @BeforeEach
    public void setup(){
        user = new User(22L, "Stratos", "Kosmapetris", 22, "stratoskosma@mail.com");
    }
    /*
    @AfterAll
    public static void tearDownAll() throws SQLException, FileNotFoundException {
        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());

        String mysqlUrl = "jdbc:mysql://localhost:3307/users_test";
        Connection con = DriverManager.getConnection(mysqlUrl, "root", "rootroot");
        ScriptRunner sr = new ScriptRunner(con);
        //Creating a reader object
        Reader reader = new BufferedReader(new FileReader("/home/orestis/IdeaProjects/users-database/DDL.sql"));
        //Running the script
        sr.runScript(reader);
        Reader reader1 = new BufferedReader(new FileReader("/home/orestis/IdeaProjects/users-database/DML.sql"));
        //Running the script
        sr.runScript(reader1);
    }*/


}
