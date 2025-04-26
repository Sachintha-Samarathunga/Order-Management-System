package OMS.Positive;

import org.openqa.selenium.Alert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.internal.junit.ExactComparisonCriteria;
import utils.BaseTest;
import utils.ExtentReportManager;

import java.io.IOException;

public class Login extends BaseTest {

    @BeforeMethod
    public void setup() throws IOException, InterruptedException {

        loadUrl();
    }

    @Test()
    public void login() throws InterruptedException {
        ExtentReportManager.startTest("Login", "<b>Login Functionality</b>");
        ExtentReportManager.testSteps("<b><font color='blue'>Test Case : </font>TC001: Verify Login with valid credentials</b>");
        ExtentReportManager.testSteps("<b><font color='blue'>Opened the application URL</font></b>");

        webSteps.implicitWait("businessName");
        webSteps.type("sm-oms-1", "businessName");
        ExtentReportManager.testSteps("Entered Business Name");
        webSteps.type("kasun@gmail.com", "email");
        ExtentReportManager.testSteps("Entered Email Address");
        webSteps.type("12345678", "password");
        ExtentReportManager.testSteps("Entered Password");
        webSteps.click("loginBtn");
        ExtentReportManager.testSteps("Clicked Login Button");

        webSteps.waiting();



        Thread.sleep(5000);
    }
}
