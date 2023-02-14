package apiTest.day07;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

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
        Map<String , Object> requestMap = new HashMap<>();
        requestMap.put("email", "bjorn2@gmail.com");
        requestMap.put("password", "tesr6543");
        requestMap.put("name", "bjornb");
        requestMap.put("google", "bjornbg");
        requestMap.put("facebook", "bjornbf");
        requestMap.put("github", "bjornbh");

        Response response = given().contentType(ContentType.JSON).and().body(requestMap).when().post("api/users");

        Assert.assertEquals(response.statusCode(),200);
        Assert.assertTrue(response.body().asString().contains("token"));
        response.prettyPrint();

    }


    @Test
    public void test3() {
        NewUserInfo newUserInfo = new NewUserInfo();
        newUserInfo.setEmail("bjorn@gmail.com");
        newUserInfo.setPassword("test7465");
        newUserInfo.setName("bjornc");
        newUserInfo.setGoogle("bjorncg");
        newUserInfo.setFacebook("bjorncf");
        newUserInfo.setGithub("bjornch");

        Response response = given().contentType(ContentType.JSON).and().body(newUserInfo).when().post("api/users");

        Assert.assertEquals(response.statusCode(),200);
        Assert.assertTrue(response.body().asString().contains("token"));
        response.prettyPrint();
    }


    @Test
    public void test4() {
        // NewUserInfo newUserInfo = new NewUserInfo("abc@husein.com", );            //parametlreli yle ayni islem
     }



}
