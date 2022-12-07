package step_definitions;

import io.cucumber.java.en.And;
import pages.SchedulePage;

public class ScheduleSteps {

    SchedulePage schedulePage = new SchedulePage();

    @And("User navigates to my schedule page")
    public void userNavigatesToMySchedulePage() {
        schedulePage.goToMySchedule();
    }
}
