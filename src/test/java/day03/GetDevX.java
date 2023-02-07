package day03;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GetDevX {

    String devXURL = "http://eurotech.study";

    @Test
    public void test1() {

        /*
        Given accept type is Json
        When user send GETS request to /api/profile
        Then verify that response status code is 200
        and verify that body contains "Microsoft"
         */

        Response response = RestAssured.given().accept(ContentType.JSON).when().get(devXURL + "/api/profile");
        Assert.assertEquals(response.getStatusCode(),200);
        Assert.assertTrue(response.body().asString().contains("Microsoft"));

    }


}
