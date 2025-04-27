package OMS.Positive;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.BaseTest;
import utils.ExtentReportManager;

import java.io.IOException;

public class User extends BaseTest {

    private String emailAddress;

    @BeforeMethod
    public void setUp() throws IOException, InterruptedException {
        loadUrl();
        webSteps.login();
    }

    @Test
    public void createUser() throws InterruptedException {

        this.emailAddress = webSteps.randomPersonalEmailAddress();

        ExtentReportManager.startTest("User Management", "<b>Test Cases for User Management</b>");
        ExtentReportManager.testSteps("<b><font color='blue'>Test Case : </font>TC001: Create User with Valid Credentials</b>");

        webSteps.click("sideMenuUser");
        webSteps.click("sideMenuUserList");

        webSteps.click("addNewUserBtn");

        webSteps.type("Amal", "userNameField");
        ExtentReportManager.testSteps("Entered name");
        webSteps.click("userRoleField");
        webSteps.selectFromDropdown();
        ExtentReportManager.testSteps("Selected user role");
        webSteps.type("712345678", "userPhoneField");
        ExtentReportManager.testSteps("Entered mobile number");
        webSteps.type(this.emailAddress, "userEmailField");
        ExtentReportManager.testSteps("Entered email address");
        webSteps.type("AmalaX@%234", "passwordField");
        ExtentReportManager.testSteps("Set the password");
        webSteps.type("AmalaX@%234", "confirmPasswordField");
        ExtentReportManager.testSteps("Confirmed password");
        webSteps.type("NO: 523, Ja-ela road, Gampaha", "addressField");
        ExtentReportManager.testSteps("Entered address");

        webSteps.waiting();
    }

}
