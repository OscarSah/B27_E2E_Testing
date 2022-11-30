package step_definitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.ConfigurationReader;
import utilities.DBUtils;
import utilities.Driver;

import java.util.List;
import java.util.Map;

public class SpartanStepDefs {

    Long expectedPhoneNumber;
    Long actualNumber;
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
        // WebPage and DB verification
        Assert.assertEquals(expectedPhoneNumber,actualNumber);

    }

    @And("User gets the number of {string} from webpage")
    public void userGetsTheNumberOfFromWebpage(String name) {
        Driver.get().get(ConfigurationReader.get("UIspartanUrl"));
        WebElement phoneElement = Driver.get().findElement(By.xpath("//td[.='Nels']/following-sibling::*[1]"));
        actualNumber = Long.valueOf(phoneElement.getText());
        System.out.println("actualNumber = " + actualNumber);
    }
}
