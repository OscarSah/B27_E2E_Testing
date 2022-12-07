package pages;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SchedulePage extends TopNavigationBar{


    @FindBy(xpath = "//td[@rowspan='4']")
    public WebElement reservedTime;

    @FindBy(xpath = "//p[@class='title is-6']")
    public List<WebElement> UIinfoElements;

    @FindBy(xpath = "(//p[@class='title is-size-4'])[1]")
    public WebElement roomName;

}
