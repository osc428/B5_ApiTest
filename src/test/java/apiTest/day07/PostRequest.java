package apiTest.day07;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class PostRequest {
    /* Task:
    * Given request body is
    * {
  "email": "bjorn@eurotech.com",
  "password": "test9876",
  "name": "bjorn",
  "google": "bjorng",
  "facebook": "bjornf",
  "github": "bjornh"
    }
    * when user dens POST request to 'api/users'
    * Then status code is 200
    * and token should be created
    * */


    @BeforeClass
    public void beforeClass() {
        baseURI = "http://eurotech.study";
    }

    @Test
    public void testpostNewUser() {

        String jsonBody = "{\n" +
                "  \"email\": \"bjorn@eurotech.com\",\n" +
                "  \"password\": \"test9876\",\n" +
                "  \"name\": \"bjorn\",\n" +
                "  \"google\": \"bjorng\",\n" +
                "  \"facebook\": \"bjornf\",\n" +
                "  \"github\": \"bjornh\"\n" +
                "    }";
        Response response = given().contentType(ContentType.JSON).and().body(jsonBody)
                .when().post("api/users");

        Assert.assertEquals(response.statusCode(),200);
        Assert.assertTrue(response.body().asString().contains("token"));

    }

    @Test
    public void test2() {



    }
}
