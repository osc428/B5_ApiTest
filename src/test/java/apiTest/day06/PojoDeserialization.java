package apiTest.day06;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;

public class PojoDeserialization {

    @BeforeClass
    public void beforeClass() {
         baseURI = "http://eurotech.study";
    }

    @Test
    public void test1() {
        Response response = RestAssured.given().queryParam("id", 528).when().get("api/profile/userQuery");
        System.out.println("response.statusCode() = " + response.statusCode());

        EurotechUser oneUser = response.body().as(EurotechUser.class);

        System.out.println("oneUser.getId() = " + oneUser.getId());
        System.out.println("oneUser.getEmail() = " + oneUser.getEmail());
        System.out.println("oneUser.getCompany() = " + oneUser.getCompany());
        System.out.println("oneUser.getName() = " + oneUser.getName());
        System.out.println("oneUser.getCompany() = " + oneUser.getCompany());
        System.out.println("oneUser.getStatus() = " + oneUser.getStatus());

        Assert.assertEquals(oneUser.getId(), 528.0);


    }
}
