package io.loopcamp.utils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HrRestUtils {

    private static String baseURl = ConfigurationReader.getProperty("hr.api.url");


    public static void addNewRegion(int regionId, String regionName){
        Map<String, Object> newRegion = new HashMap<>();
        newRegion.put("region_id", regionId);
        newRegion.put("region_name", regionName);
        Response response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(newRegion)
                .when().post("/regions/");
    }

    public static void deleteRegion(int regionId){

        System.out.println("DELETING region with region_id {" + regionId + "}");
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("region_id", regionId)
                .when().delete(baseURl + "/regions/{"+regionId+"}");
                //.then().log().all();
        //assertEquals(response.getBody(), null, "Region was NOT deleted");
    }
}
