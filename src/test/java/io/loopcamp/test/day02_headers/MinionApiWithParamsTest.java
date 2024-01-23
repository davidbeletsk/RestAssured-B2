package io.loopcamp.test.day02_headers;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 Given accept type is Json
 And Id path parameter value is 5
 When user sends GET request to /api/minions/{id}
 ------------------------
 Then response status code should be 200
 And response content-type: application/json
 And "Blythe" should be in response payload(body)
 */

public class MinionApiWithParamsTest {

    String apiMethod = "http://54.196.207.0:8000/api/minions";

    @DisplayName("GET /api/minions/{id}")
    @Test
    public void getSingleMinionTest(){
        int id = 5;

        //OPTION 1
        given().accept(ContentType.JSON) //ContentType.JSON -- > this is ENUM, saying = hey api, i need to get data as JSON
                .when().get(apiMethod + "/" + id ); // we basically concatenated id into the URL/
        //OPTION 2
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 5) // id value is passed using the .pathParam() method / more readable & understandable
                .when().get(apiMethod + "/{id}"); //5

        //response.prettyPrint();

        //Then response status code should be 200
        //System.out.println("response.statusCode() = " + response.statusCode());
        //assertEquals(200, response.statusCode());
        assertEquals(HttpStatus.SC_OK, response.statusCode());

        //And response content-type: application/json
        //System.out.println("response.getHeader(\"content-type\") = " + response.getHeader("content-type"));
        assertEquals(ContentType.JSON.toString(), response.contentType());

        //And "Blythe" should be in response payload(body)
        assertTrue(response.body().asString().contains("Blythe"));

    }

    /**
     *         Given accept type is Json
     *         And Id parameter value is 500
     *         When user sends GET request to /api/minions/{id}
     *          -----------------------------
     *         Then response status code should be 404
     *         And response content-type: application/json
     *         And "Not Found" message should be in response payload
     */

    @DisplayName("GET /api/minions/{id} with invalid id")
    @Test
    public void getSingleMinionNotFoundTest () {
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 500)
                .when().get(apiMethod + "/{id}");


        //System.out.println(response.statusCode());
        //assertEquals(404, response.statusCode());
        assertEquals( HttpStatus.SC_NOT_FOUND, response.statusCode());

        assertEquals(ContentType.JSON.toString(), response.contentType());
        //assertEquals("application/json", response.contentType());


        //assertEquals("Not Found", response.body().asString());
        assertTrue(response.body().asString().contains("Not Found"));



    }

}
