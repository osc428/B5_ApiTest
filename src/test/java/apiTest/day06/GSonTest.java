package apiTest.day06;

import com.google.gson.Gson;
import org.testng.annotations.Test;

import java.util.Map;

public class GSonTest {

    @Test
    public void jsonToMap() {

        Gson gson = new Gson();
        String myJsonBody = "{\n" +
                "    \"id\": 0,\n" +
                "    \"name\": \"miky\",\n" +
                "    \"firstName\": \"mike\",\n" +
                "    \"lastName\": \"masters\",\n" +
                "    \"email\": \"mike@gmail.com\",\n" +
                "    \"password\": \"Test1234\",\n" +
                "    \"phone\": \"string\",\n" +
                "    \"userStatus\": 0\n" +
                "  }";


        //gson converting to map
        Map <String,Object> dataMap = gson.fromJson(myJsonBody, Map.class);
        System.out.println("dataMap = " + dataMap);

        //gson converting to object class
        EurotechUser oneUser = gson.fromJson(myJsonBody, EurotechUser.class);
        System.out.println("oneUser.getName() = " + oneUser.getName());

        //serialization (POJO to Json)
        EurotechUser eurotechUser = new EurotechUser(11, "mike2@gmail.com", "mikyy", "eurotech", "retired", "503");
        String jsonUser = gson.toJson(eurotechUser);
        System.out.println("jsonUser = " + jsonUser);


    }
}
