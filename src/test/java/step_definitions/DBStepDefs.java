package step_definitions;

import org.junit.Assert;
import utilities.DBUtils;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


import java.util.Map;

public class DBStepDefs {

    Map<String,Object> dbMap;
    public static String DBName;
    public static String DBRole;
    public static String DBTeam;
    public static String DBBatch;
    public static String DBCampus;

    @When("User sends a query to DataBase with {string}")
    public void user_sends_a_query_to_DataBase_with(String email) {
        String query = "select firstname, lastname, role, name,location,batch_number\n" +
                "from users u join (select t.id,name,batch_number,location from\n" +
                "team t join campus c on t.campus_id=c.id) r\n" +
                "on u.team_id=r.id where u.email='"+email+"';";

        dbMap = DBUtils.getRowMap(query);
        System.out.println(dbMap);
    }

    @Then("User gets DataBase information")
    public void user_gets_DataBase_information() {
        DBName = dbMap.get("firstname")+" "+dbMap.get("lastname");
        DBCampus = (String) dbMap.get("location");
        DBBatch = "#"+dbMap.get("batch_number");
        DBRole = (String) dbMap.get("role");
        DBTeam = (String) dbMap.get("name");

    }


    @Then("User verifies UI info with DB")
    public void userVerifiesUIInfoWithDB() {
        Assert.assertEquals(DBName,UIStepDefs.UIname);
    }
}
