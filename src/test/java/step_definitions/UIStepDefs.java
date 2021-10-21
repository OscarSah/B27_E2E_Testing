package step_definitions;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.SelfPage;
import pages.SignInPage;
import utilities.ConfigurationReader;
import utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class UIStepDefs {

    SignInPage signInPage = new SignInPage();
    SelfPage selfPage =new SelfPage();
    WebDriverWait wait = new WebDriverWait(Driver.get(),15);
    public static String UIname;
    public static String UIrole;
    public static String UIcampus;
    public static String UIteam;
    public static String UIbatch;

    @Given("User logs in with {string} and {string}")
    public void user_logs_in_with_and(String email, String password) {
        Driver.get().get(ConfigurationReader.get("url")); // Driver class line 34 already calls for the browser type
        signInPage.email.sendKeys(email);
        signInPage.password.sendKeys(password);
        signInPage.signInButton.click();

    }

    @When("User navigates to mySelf page")
    public void user_navigates_to_mySelf_page()  {

       wait.until(ExpectedConditions.visibilityOf(selfPage.my));

       selfPage.goToSelf();


    }

    @Then("User gets the UI info")
    public void user_gets_the_UI_info() {
            wait.until(ExpectedConditions.visibilityOf(selfPage.campus));
            UIname = selfPage.name.getText();
            UIbatch = selfPage.batch.getText();
            UIcampus = selfPage.campus.getText();
            UIrole = selfPage.role.getText();
            UIteam = selfPage.team.getText();

        System.out.println("UIcampus = " + UIcampus);
        System.out.println("UIteam = " + UIteam);

    }
}
