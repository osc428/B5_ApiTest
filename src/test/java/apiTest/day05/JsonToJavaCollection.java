package apiTest.day05;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;

public class JsonToJavaCollection {

    @BeforeClass
    public void beforeClass() {
        baseURI = "http://eurotech.study";
    }

    @Test
    public void test1() {
        Response response = RestAssured.given().queryParam("id", 25).when().get("api/profile/userQuery");

        Assert.assertEquals(response.statusCode(), 200);

        Map<String, Object> jsonDataMap = response.body().as(Map.class);
        System.out.println("jsonDataMap = " + jsonDataMap);
    }

    @Test
    public void test2() {
        Response response = RestAssured.get("api/profile");
        Assert.assertEquals(response.statusCode(), 200);

        //  de-serialization
        List<Map<String ,Object>> allBody = response.body().as(List.class);
        //System.out.println("allBody = " + allBody);

        // print and verify name of the company as "NinjaTeam"
        System.out.println("allBody.get(28).get(\"company\") = " + allBody.get(28).get("company"));
        Assert.assertEquals(allBody.get(28).get("company"), "NinjaTeam");

        Map<String, Object> secondUserInfo = allBody.get(1);
        System.out.println("secondUserInfo = " + secondUserInfo);
    }
}
