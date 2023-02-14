package apiTest.day07;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class PostSaveProfile {
    @BeforeClass
    public void beforeClass() {
        baseURI = "http://eurotech.study";
    }


    @Test
    public void postNewUser() {
        NewUserInfo newUserInfo = new NewUserInfo();
        newUserInfo.setEmail("bjorn5@gmail.com");
        newUserInfo.setPassword("test7465");
        newUserInfo.setName("bjornc");
        newUserInfo.setGoogle("bjorncg");
        newUserInfo.setFacebook("bjorncf");
        newUserInfo.setGithub("bjornch");

        Response response = given().contentType(ContentType.JSON).and().body(newUserInfo).when()
                .post("api/users");
        response.prettyPrint();
        Assert.assertEquals(response.statusCode(),200);
        Assert.assertTrue(response.body().asString().contains("token"));

        String token = response.path("token");

        String profileBody = "{\n" +
                "\"company\": \"volvo\",\n" +
                "\"location\": \"malmo\",\n" +
                "\"githubusername\": \"bjorn7\",\n" +
                "\"status\": \"student\",\n" +
                "\"skills\": \"Java\"\n" +
                "}";

        response = given().contentType(ContentType.JSON).and().header("x-auth-token", token)
                .and().body(profileBody).when().post("api/profile");

        Assert.assertEquals(response.statusCode(),200);
        response.prettyPrint();
    }

    @Test
    public void postVerifyUser() {
        NewUserInfo newUserInfo = new NewUserInfo();
        newUserInfo.setEmail("bjorn7@gmail.com");
        newUserInfo.setPassword("test7465");
        newUserInfo.setName("bjornh");
        newUserInfo.setGoogle("bjorncg");
        newUserInfo.setFacebook("bjorncf");
        newUserInfo.setGithub("bjornch");

        Response response = given().contentType(ContentType.JSON).and().body(newUserInfo).when()
                .post("api/users");
        response.prettyPrint();
        Assert.assertEquals(response.statusCode(),200);
        Assert.assertTrue(response.body().asString().contains("token"));

        String token = response.path("token");

        String profileBody = "{\n" +
                "\"company\": \"volvo\",\n" +
                "\"location\": \"malmo\",\n" +
                "\"githubusername\": \"bjorn7\",\n" +
                "\"status\": \"student\",\n" +
                "\"skills\": \"Java\"\n" +
                "}";

        Assert.assertEquals(response.statusCode(),200);

        //verify body
        int idNum = response.path("id");

        response = given().contentType(ContentType.JSON).and().queryParam("id", idNum)
                .when().get("api/profile/userQuery");

        Assert.assertEquals(response.statusCode(),200);

        //verify path
        Assert.assertEquals(response.path("name"), "bjornh");
        Assert.assertEquals(response.path("company"), "volvo");

        //verify with hamcrest matcher
        String email = "bjorn7@gmail.com";
        given().contentType(ContentType.JSON).and().queryParam("id", idNum)
                .when().get("api/profile/userQuery").then().assertThat().body("email", Matchers.equalTo(email)).log().all();

    }
}
