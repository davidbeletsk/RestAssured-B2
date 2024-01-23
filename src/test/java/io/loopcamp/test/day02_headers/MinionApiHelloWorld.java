package io.loopcamp.test.day02_headers;

import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class MinionApiHelloWorld {

    /**
     When I send GET request to http://your_ip:8000/api/hello
     ---------------------
     Then status code should be 200
     And response body should be equal to "Hello from Minion"
     And content type is "text/plain;charset=ISO-8859-1"
     */
    String endpoint = "http://54.196.207.0:8000/api/hello";
    @DisplayName("GET api/hello")
    @Test
    public void helloApiTest(){
        Response response = when().get(endpoint);

        //System.out.println("Response status code: " + response.statusCode());
        assertEquals(200, response.statusCode());

        assertEquals("Hello from Minion", response.body().asString());

        //System.out.println("response.getContentType() = " + response.getContentType());
        assertEquals("text/plain;charset=ISO-8859-1", response.contentType());
    }
    @DisplayName("GET api/hello - bdd")
    @Test
    public void helloApiBddTest (){
        when().get(endpoint)
                .then().assertThat().statusCode(200)
                .and().contentType("text/plain;charset=ISO-8859-1");
    }
}
