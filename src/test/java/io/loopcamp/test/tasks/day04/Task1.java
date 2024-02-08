package io.loopcamp.test.tasks.day04;

import io.loopcamp.utils.HRApiTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class Task1 extends HRApiTestBase {

    /**
     * - Given accept type is Json
     * - Path param value- US
     * - When users sends request to /countries
     * - Then status code is 200
     * - And Content - Type is Json
     * - And country_id is US
     * - And Country_name is United States of America
     * - And Region_id is 2
     */
    public void jsonPathCountriesTest() {

//        Response response = given().accept(ContentType.JSON)
//                .pathParam("value", "US")


    }

}
