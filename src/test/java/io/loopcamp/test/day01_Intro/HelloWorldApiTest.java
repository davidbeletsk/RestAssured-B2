package io.loopcamp.test.day01_Intro;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class HelloWorldApiTest {
    String url = "https://sandbox.api.service.nhs.uk/hello-world/hello/world";

    @DisplayName("Hello World GET request")
    @Test
    public void helloWorldGetRequestTest(){
        //send a get request and save response inside the Response object
        Response response = RestAssured.get(url);

        //Print response body in formatted way (in case JSON format) - RESPONSE BODY
        response.prettyPrint();

        //Print status code
        System.out.println("Status code: " + response.statusCode());
        System.out.println("Status Line: " + response.statusLine());

        //assert/validate that Response Code is 200
        Assertions.assertEquals(200, response.statusCode(), "Expected does Not match Actual");

        //You can also assign RESPONSE STATUS CODE into variable and then use variable to assert
        int actualStatusCode = response.statusCode();
        Assertions.assertEquals(200, actualStatusCode, "Expected does Not match Actual");

        String actualResponseBody = response.asString();
        System.out.println(actualResponseBody);
        Assertions.assertTrue(actualResponseBody.contains("Hello World!"));


    }

}