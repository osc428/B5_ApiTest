package apiTest.day02;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SimpleGetRequest {
    @Test
    public void Test1()
    {
        Response response = RestAssured.get("http://eurotech.study/api/profile");

        //status code
        System.out.println(response.statusCode());

        //body
        response.prettyPrint();
    }

    String devXURL = "http://eurotech.study";
    @Test
    public void Test2(){
        Response response = RestAssured.given().accept(ContentType.JSON).when().get(devXURL + "/api/profile");
        System.out.println("response.statusCode() = " + response.statusCode());
        Assert.assertEquals(response.statusCode(),200);
        Assert.assertEquals(response.contentType(),"application/json; charset=utf-8");
       // System.out.println("response.getHeaders() = " + response.getHeaders());
    }

    @Test
    public void Test3(){
        RestAssured.given().accept(ContentType.JSON).when().get(devXURL + "/api/profile")
                .then().assertThat().statusCode(200).and().contentType("application/json; charset=utf-8");

    }

    @Test
    public void Test4() {
        //Verify that body has Mike
        Response response = RestAssured.given().accept(ContentType.JSON).when().get(devXURL + "/api/profile");
        Assert.assertEquals(response.statusCode(),200);
        //response.body().asPrettyString();
        Assert.assertTrue(response.body().asString().contains("Mike"));
        String s = response.body().asString();
        System.out.println(s.indexOf("Mike"));

        //header dan date verify edeceksek
    }

}
