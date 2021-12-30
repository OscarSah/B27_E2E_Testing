package step_definitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import utilities.DBUtils;

import java.util.List;
import java.util.Map;

public class SpartanStepDefs {

    Long expectedPhoneNumber;
    @When("the User retrives the phone number for the spartan {string}")
    public void the_User_retrives_the_phone_number_for_the_spartan(String name) {

        String query ="select * from spartans where name='"+name+"'";

        Map<String, Object> rowMap = DBUtils.getRowMap(query);
        System.out.println("rowMap = " + rowMap);
        expectedPhoneNumber = Long.valueOf((String) rowMap.get("PHONE"));

    }

    @Then("the phone number from DB should be {}")
    public void the_phone_number_from_DB_should_be(Long actualPhone) {

        Assert.assertEquals("Phone numbers do NOT match",expectedPhoneNumber,actualPhone);
    }
}
