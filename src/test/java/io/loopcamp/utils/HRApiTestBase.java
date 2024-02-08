package io.loopcamp.utils;

import org.junit.jupiter.api.BeforeEach;

import static io.restassured.RestAssured.*;


public class HRApiTestBase {

    @BeforeEach
    public void setUp(){
        //String url = ConfigurationReader.getProperty("hr.api.url");
        baseURI = ConfigurationReader.getProperty("hr.api.url");
    }
}
