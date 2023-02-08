package day03;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class PetStoreWithParameters {


/*
    @BeforeClass
    public void beforeClass() {
        RestAssured.baseURI = "http://eurotech.study";
    }

    @Test
    public void test() {
        Response response = RestAssured.get("/api/profile");
        response.prettyPrint();
    }

    */


    String baseUrl = "https://petstore.swagger.io/v2";

    @Test
    public void pathParam() {

        int petId = 13;
        Response id = RestAssured.given().accept(ContentType.JSON).and().pathParam("petId", petId).
                when().get(baseUrl + "/pet/{petId}");           // pathParam daki String ile get icindeki suslus parantez ici ayni olmali !!

        Assert.assertEquals(id.statusCode(),200);

    }

    @Test
    public void ikinciYontem() {
        Response response = RestAssured.given().accept(ContentType.JSON).when().get(baseUrl + "/pet/13");
        Assert.assertEquals(response.statusCode(),200);

    }

    @Test
    public void queryParameterPet() {
        /*            TASK
                Given accept type is Json
                And query  parameter is status sold
                When user sends GET request to /pet/findByStatus
                The response status code 200
                And content type is application/json
                And "cats" should be in payload/ body
 */

        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().queryParam("status", "sold")
                .when().get(baseUrl + "/pet/findByStatus");

        Assert.assertEquals(response.statusCode(),200);
        Assert.assertEquals(response.contentType(),"application/json");
        Assert.assertTrue(response.body().asString().contains("cats"));

    }

    @Test
    public void queryParamWithMapPet() {

        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("status","sold");
        //queryMap.put("status","available");

        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().queryParams(queryMap)
                .when().get(baseUrl + "/pet/findByStatus");
        response.prettyPrint();
        Assert.assertEquals(response.statusCode(),200);


    }
    String devXURL = "http://eurotech.study";
    @Test
    public void devExVerifyWithPath() {
         /*
                     Verify that all information in the body

    "id": 681,
    "email": "instructorihsan@eurotech.com",
    "name": "Ihsan",
    "company": "EuroTech",
    "status": "Instructor",
    "profileId": 402
}
         */

        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().queryParam("id", 681)
                .when().get(devXURL + "/api/profile/userQuery");

        Assert.assertEquals(response.statusCode(),200);
        response.prettyPrint();

        System.out.println("response.path(\"id\") = " + response.path("id"));
        System.out.println("response.path(\"email\") = " + response.path("email"));
        System.out.println("response.path(\"name\") = " + response.path("name"));
        System.out.println("response.path(\"company\") = " + response.path("company"));
        System.out.println("response.path(\"status\") = " + response.path("status"));
        System.out.println("response.path(\"profileId\") = " + response.path("profileId"));

        int actualId= response.path("id");
        Assert.assertEquals(actualId,681);
        Assert.assertEquals(response.path("email"),"instructorihsan@eurotech.com");
        Assert.assertEquals(response.path("name"),"Ihsan");
        Assert.assertEquals(response.path("company"),"EuroTech");

    }

    @Test
    public void task() {
        Response response = RestAssured.given().accept(ContentType.JSON).and().pathParam("Id", 15776076).
                when().get(baseUrl + "/pet/{Id}");           // pathParam daki String ile get icindeki suslus parantez ici ayni olmali !!

        Assert.assertEquals(response.statusCode(),200);
        Assert.assertEquals((Integer)response.path("category.id"),-53417368);
        Assert.assertEquals(response.path("category.name"),"fugiat");
    }
}
