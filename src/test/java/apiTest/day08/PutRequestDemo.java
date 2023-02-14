package apiTest.day08;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class PutRequestDemo {

    @BeforeClass
    public void beforeClass() {
        baseURI = "http://eurotech.study";
    }

    @Test
    public void addExperience() {

        String expBody = "{\n" +
                "  \"title\": \"Tester\",\n" +
                "  \"company\": \"Volvo\",\n" +
                "  \"location\": \"Malmo\",\n" +
                "  \"from\": \"2021-01-10\",\n" +
                "  \"to\": \"2021-07-21\",\n" +
                "  \"current\": false,\n" +
                "  \"description\": \"Junior\"\n" +
                "}";

        Response response = given().contentType(ContentType.JSON).and().header("x-auth-token", Authorization.getToken()).and().body(expBody).when().post("/api/profile/experience");
        Assert.assertEquals(response.statusCode(),201);

    }
}
