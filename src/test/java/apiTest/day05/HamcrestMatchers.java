package apiTest.day05;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class HamcrestMatchers {

    @BeforeClass
    public void beforeClass() {
        baseURI = "http://eurotech.study";                      // slash eksikligini otomatik tamamlar
    }



    @Test
    public void test() {
        String devExUrl = "http://eurotech.study";
        Response response = RestAssured.get(devExUrl + "/api/profile/userQuery");
        assertEquals(response.statusCode(),200);
        response.prettyPrint();
    }

    @Test
    public void getOneUser() {

        given().accept(ContentType.JSON)
                .queryParam("id", 528)
                .when().get("/api/profile/userQuery")
                .then().assertThat().statusCode(200)
                .and()
                .assertThat().contentType("application/json; charset=utf-8");
    }

    @Test
    public void getOneUserHamcrestMatcher() {

        given().accept(ContentType.JSON)
                .queryParam("id", 528)
                .when().get("/api/profile/userQuery")
                .then().assertThat().statusCode(200)
                .and()
                .assertThat().contentType("application/json; charset=utf-8")
                .and()
                .assertThat().body("id", Matchers.equalTo(528),
                        "email",Matchers.equalTo("eurotech@gmail.com"),
                        "name",Matchers.equalTo("Teacher"),
                        "company", Matchers.equalTo("Eurotech Study"))
                .and()
                .assertThat()
                .header("Content-Type", Matchers.equalTo("application/json; charset=utf-8"));
    }

    @Test
    public void UserHamcrestMatcher() {

        given().accept(ContentType.JSON)
                .queryParam("id", 528)
                .when().log().all().get("/api/profile/userQuery")
                .then()
                .assertThat()
                .header("Content-Type", Matchers.equalTo("application/json; charset=utf-8")).log().all()
                .and().header("Date", Matchers.notNullValue());
    }

    @Test
    public void UserHamcrestMatcherGPath() {

        given().accept(ContentType.JSON)
                .when().get("/api/profile")
                .then()
                .assertThat()
                .body("user.email", Matchers.hasItem("talisca@gmail.com")).log().all();
    }

    @Test
    public void UserHamcrestMatcherGPath2() {

        given().accept(ContentType.JSON)
                .when().get("/api/profile")
                .then()
                .assertThat()
                .body("user.name", Matchers.hasItems("Rashid", "Eminem", "sylvester")).log().all();
    }

    @Test
    public void UserHamcrestMatcherGPath3() {

        given().accept(ContentType.JSON)
                .when().get("/api/profile")
                .then()
                .assertThat()
                .body("company[1]", Matchers.equalTo("Eurotech"), "skills[1][4]", Matchers.equalTo("API"));
    }

}
