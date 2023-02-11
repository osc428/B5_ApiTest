package apiTest.day03;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class pathMethod {
    String devExUrl = "http://eurotech.study";


    @Test
    public void getAllProfiles() {

        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get(devExUrl + "/api/profile");
        Assert.assertEquals(response.statusCode(),200);
        // response.prettyPrint();

        //Finding first element's ID
        int firstID = response.path("id[0]");
        System.out.println("firstID = " + firstID);
        //Finding lsat element's ID
        int lastID = response.path("id[-1]");
        System.out.println("lastID = " + lastID);

        System.out.println("--------------------------------------");

        //Finding second element's COMPANY
        String secondCompany = response.path("company[1]");
        System.out.println("secondCompany = " + secondCompany);

        System.out.println("--------------------------------------");

        //GET first skills' skills as List
        List<String> firstSkills= response.path("skills[0]");
        for (String skill : firstSkills) {
            System.out.println(skill);
        }

        //System.out.println("firstSkills.size() = " + firstSkills.size());

        Object firstSkillsSecondSkill = response.path("skills[0][1]");
        System.out.println("firstSkillsSecondSkill = " + firstSkillsSecondSkill);

        System.out.println("--------------------------------------");

        Map<String,Object> firstUserMap = response.path("user[0]");
        System.out.println("firstUserMap = " + firstUserMap);

        for (String user : firstUserMap.keySet()) {
            // System.out.println("user = " + user); // Bu yöntem sadece key veriyor
            System.out.println(user + " : "+ firstUserMap.get(user));
        }

        System.out.println("--------------------------------------");

        Object firstUserId = response.path("user[0].id");
        System.out.println("firstUserId = " + firstUserId);

        Object secondUserId = response.path("user[1].id");//"user.id[1]"aynı sonucu veriri ama kullanmayın
        System.out.println("secondUserId = " + secondUserId);

        System.out.println("--------------------------------------");
        //GET all user IDs
        List<String> userIDs = response.path("user.id");
        System.out.println("userIDs.size() = " + userIDs.size());
        System.out.println("userIDs = " + userIDs);

        System.out.println("--------------------------------------");

        //GET all ids (NOT user id)
        List<Integer> allIDs = response.path("id");
        for (Integer id : allIDs) {
            System.out.println("id = "+id);
        }

    }
}
