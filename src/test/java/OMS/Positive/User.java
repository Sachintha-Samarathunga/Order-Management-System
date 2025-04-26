package OMS.Positive;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
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

        WebElement element = driver.findElement(By.xpath("//div[@class='overflow-y-auto max-h-60']"));

        List<WebElement> totalLinks = element.findElements(By.tagName("label"));
        int totalLinkSize = totalLinks.size();
        System.out.println("Total Links by Way1 : " + totalLinkSize);

    }

}
