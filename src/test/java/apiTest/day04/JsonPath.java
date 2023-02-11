package apiTest.day04;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static org.testng.Assert.assertEquals;

public class JsonPath {

    String devExUrl = "http://eurotech.study";

    @Test
    public void test1() {

        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().queryParam("id", 74)
                .when().get(devExUrl + "/api/profile/userQuery");
        assertEquals(response.statusCode(),200);
        response.prettyPrint();

        System.out.println("response.path(\"email\") = " + response.path("email"));


        System.out.println("*************JsonPath Method****************");

        io.restassured.path.json.JsonPath jsonData = response.jsonPath();

        int userId = jsonData.getInt("id");
        System.out.println("userId = " + userId);

        String emailJson = jsonData.getString("email");
        String nameJson = jsonData.getString("name");
        String companyJson = jsonData.getString("company");
        String statusJson = jsonData.getString("status");
        int profileIdJson = jsonData.getInt("profileId");

        System.out.println("emailJson = " + emailJson);
        System.out.println("nameJson = " + nameJson);
        System.out.println("companyJson = " + companyJson);
        System.out.println("statusJson = " + statusJson);
        System.out.println("profileIdJson = " + profileIdJson);


    }

    @Test
    public void task() {
        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().queryParam("id", 29)
                .when().get(devExUrl + "/api/profile/userQuery");
        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(), "application/json; charset=utf-8");

        io.restassured.path.json.JsonPath jsonPath = response.jsonPath();
        assertEquals(jsonPath.getInt("id"),29);
        assertEquals(jsonPath.getInt("profileId"),11);
        assertEquals(jsonPath.getString("email"), "oyku@gmail.com");
        assertEquals(jsonPath.getString("name"), "oyku");
        assertEquals(jsonPath.getString("company"), "Microsoft");
        assertEquals(jsonPath.getString("status"), "Student or Learning");
    }

    @Test
    public void task2() {
        Response response = RestAssured.get(devExUrl + "/api/profile");
        io.restassured.path.json.JsonPath jsonPath = response.jsonPath();

        int secondUserId = jsonPath.getInt("id[1]");
        System.out.println("secondUserId = " + secondUserId);

        List <Object> allCompanies = jsonPath.getList("company");
        System.out.println("allCompanies = " + allCompanies);

        Map<String,Object> secondUserInfo = jsonPath.getMap("user[1]");
        System.out.println("secondUserInfo = " + secondUserInfo);
        System.out.println("secondUserInfo.get(\"name\") = " + secondUserInfo.get("name"));

        List<String > secondUserSkills = jsonPath.getList("skills[1]");
        System.out.println("secondUserSkills = " + secondUserSkills);

        //     ********************   GPATH Method ***********************

        //get all user names, which github value is not null

        List<Object> githubNullUsers = jsonPath.getList("user.findAll{it.github!=null}.name");
        System.out.println("githubNullUsers = " + githubNullUsers);

        List<Object> lessThen10Id = jsonPath.getList("user.findAll{it.id>500}.name");
        System.out.println("lessThen10Id = " + lessThen10Id);

        String lessThen10Ids = jsonPath.getString("user.find{it.id>500}.name");
        System.out.println("lessThen10Id = " + lessThen10Ids);
    }
}
