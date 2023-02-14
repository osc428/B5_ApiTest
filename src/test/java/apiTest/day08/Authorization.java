package apiTest.day08;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class Authorization {

    @BeforeClass
    public void beforeClass() {
        baseURI = "http://eurotech.study";
    }

    @Test
    public void test1() {
        String loginBody = "{\n" +
                "\"email\": \"osman@gmail.com\",\n" +
                "\"password\": \"Os123456\"\n" +
                "}";

        Response response = given().contentType(ContentType.JSON).and().body(loginBody).when().post("api/auth");
        Assert.assertEquals(response.statusCode(),200);
        System.out.println("response.path(\"token\") = " + response.path("token"));
    }

    public static String getToken(){
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("email", "osman@gmail.com");
        tokenMap.put("password", "Os123456");

        Response response = given().contentType(ContentType.JSON).and().body(tokenMap).when().post("api/auth");

        return response.path("token");
    }
}
