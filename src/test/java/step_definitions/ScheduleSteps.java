package step_definitions;

import io.cucumber.java.en.And;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import pages.SchedulePage;
import utilities.DBUtils;

import java.util.List;
import java.util.Map;

public class ScheduleSteps {

    SchedulePage schedulePage = new SchedulePage();
    String UIRoomName;
    String query = "select firstname,lastname,r.name as roomname from users\n" +
            "                                          join conference c on users.id = c.reservator_id\n" +
            "                                          join room r on c.room_id = r.id\n" +
            "where room_id=124;";
    @And("User navigates to my schedule page")
    public void userNavigatesToMySchedulePage() {
        schedulePage.goToMySchedule();
    }

    @And("User selects reserved time from schedule and acquires reservation info")
    public void userSelectsReservedTimeFromScheduleAndAcquiresReservationInfo() {
        schedulePage.reservedTime.click();
        List<WebElement> infoList = schedulePage.UIinfoElements;
        for (WebElement webElement : infoList) {
            System.out.println(webElement.getText());
        }

        UIRoomName = schedulePage.roomName.getText();
        System.out.println("UIRoomName = " + UIRoomName);
    }

    @And("User gets reserved time database information")
    public void userGetsReservedTimeDatabaseInformation() {
        Map<String, Object> DBResultMap = DBUtils.getRowMap(query);
        System.out.println("DBResultMap = " + DBResultMap);
        String DBRoomName = (String) DBResultMap.get("roomname");

        Assert.assertEquals(DBRoomName,UIRoomName);

    }
}
