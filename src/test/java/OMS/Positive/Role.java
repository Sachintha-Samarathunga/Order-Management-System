package OMS.Positive;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.BaseTest;
import utils.ExtentReportManager;

import java.awt.*;
import java.io.IOException;

public class Role extends BaseTest {

    @BeforeMethod
    public void setUp() throws InterruptedException, IOException {
        loadUrl();
        webSteps.login();
        webSteps.waiting();
        webSteps.click("SideMenu User Tab");
        webSteps.click("SideMenu Roles Tab");
    }

    @Test(priority = 1)
    public void addRole() throws InterruptedException, AWTException {
        ExtentReportManager.startTest("Roles Functionality", "<b>Create Role</b>");
        ExtentReportManager.testSteps("<b><font color='blue'>Test Case : </font>TC01: Verify that the user can successfully create a role</b>");
        ExtentReportManager.testSteps("<b><font color='blue'>Test Steps : </font></b>" +
                "<br>Step 1- Logged in to the System" +
                "<br>Step 2- Clicked User " +
                "<br>Step 3- Clicked Roles " +
                "<br>Step 3- Clicked 'Add New Role' Button" +
                "<br>Step 5- Filled Details" +
                "<br>Step 6- Clicked 'Save' Button"
        );
        webSteps.click("Add New Role Button");
        webSteps.type(webSteps.generateRandomRoleName(),"Role Name Field");
        webSteps.click("Add Permissions Button");
        webSteps.click("All Permissions Checkbox");
        webSteps.click("Save Button");

        Assert.assertEquals("Role created successfully",webSteps.getText("Toast Message"), "Passed");
    }
}
