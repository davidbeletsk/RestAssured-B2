package io.loopcamp.test.day02_headers;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class MinionHeadersTest {

    String requestUrl = "http://54.196.207.0:8000/api/minions";
    @DisplayName("GET api/minions and expect XML format")
    @Test
    public void getAllMinionsHeadersTest (){

        when().get(requestUrl)
                .then().assertThat().statusCode(200)
                .and().contentType(ContentType.XML);
                //.and().contentType("application/xml");
    }
    @DisplayName("GET /api/minions with requested header")
    @Test
    public void acceptTypeHeader(){
        //given().accept("application/json")
        given().accept(ContentType.JSON)
                .when().get(requestUrl)
                .then().assertThat().statusCode(200)
                //.and().contentType("application/json")
                .and().contentType(ContentType.JSON);
    }

    @DisplayName("GET /api/minions with requested header JSON - red headers")
    @Test
    public void readResponseHeadersTest(){
        Response response  = given().accept(ContentType.JSON)
                .when().get(requestUrl);
        //response.prettyPrint();
        assertEquals(200, response.statusCode());

        // How to read each header - .getHeader(String str - Key);
        System.out.println("Date Header: " + response.getHeader("Date"));
        System.out.println("Content-type Header: " + response.getHeader("Content-Type"));
        System.out.println("Connection Header: " + response.getHeader("Connection"));


        // How to read all Headers - .getHeaders();
        System.out.println("\nAll Headers: \n" + response.getHeaders());

        // How would validate if any of the header is not empty
        assertTrue(response.getHeader("Keep-Alive") != null);

    }




}
