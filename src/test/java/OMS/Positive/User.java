package OMS.Positive;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.BaseTest;
import utils.ExtentReportManager;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class User extends BaseTest {

    @BeforeMethod
    public void setUp() throws IOException, InterruptedException {
        loadUrl();
        webSteps.login();
    }

    @Test
    public void createUser() throws InterruptedException {

        ExtentReportManager.startTest("User Management", "<b>Test Cases for User Management</b>");
        ExtentReportManager.testSteps("<b><font color='blue'>Test Case : </font>TC001: Create User with Valid Credentials</b>");

        webSteps.click("sideMenuUser");
        webSteps.click("sideMenuUserList");

        webSteps.click("addNewUserBtn");

        webSteps.click("userRoleField");

        WebElement parentDiv = driver.findElement(By.xpath("//div[@class='overflow-y-auto max-h-60']"));

// Then, find all child divs inside it
        List<WebElement> childDivs = parentDiv.findElements(By.tagName("div"));

// Now you can loop through childDivs if you want
        for (WebElement child : childDivs) {
            System.out.println(child.getText());
        }
    }

}
