package io.loopcamp.test.tasks.day07;

import io.loopcamp.utils.HRApiTestBase;
import io.loopcamp.utils.HrRestUtils;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.*;


public class Task1  extends HRApiTestBase {

    /**
     * given accept is json
     * and content type is json
     * When I send post request to "/regions/"
     * With json:
     * {
     *     "region_id":100,
     *     "region_name":"Test Region"
     * }
     * Then status code is 201
     * content type is json
     * region_id is 100
     * region_name is Test Region
     */


    /**
     * given accept is json
     * When I send post request to "/regions/100"
     * Then status code is 200
     * content type is json
     * region_id is 100
     * region_name is Test Region
     */

    @Test
    public void postThenValidateFirst(){

        Map<String, Object> newRegion = new HashMap<>();
        newRegion.put("region_id", 100);
        newRegion.put("region_name", "Test Region");

        Response response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(newRegion)
                .when().post("/regions/");

        response.prettyPrint();
        assertThat(response.statusCode(), is(HttpStatus.SC_CREATED));
        assertThat(response.contentType(), is(ContentType.JSON + ""));

        int newRegionId = response.jsonPath().getInt("region_id");

        Response response1 = given().accept(ContentType.JSON)
                .and().pathParam("region_id", newRegionId)
                .when().get("/regions/{region_id}");

        Map<String, Object> getRegionMap = response1.as(HashMap.class);
        assertThat(getRegionMap.get("region_id"), equalTo(newRegionId));
        assertThat(getRegionMap.get("region_name"), equalTo("Test Region"));

        HrRestUtils.deleteRegion(newRegionId);
    }

}
