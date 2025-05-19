package OMS.positive;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.BaseTest;
import utils.ExtentReportManager;
import utils.PropertyUtils;

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

    public void search() throws InterruptedException {
        webSteps.passValue("Role","Search Dropdown");
        webSteps.type(PropertyUtils.getProperty("Role_Name"),"Search Box");
        webSteps.click("Search Button");
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

    @Test(priority = 2)
    public void searchRole() throws InterruptedException, AWTException {
        ExtentReportManager.startTest("Roles Functionality", "<b>Search Role</b>");
        ExtentReportManager.testSteps("<b><font color='blue'>Test Case : </font>TC02: Verify that the user can successfully search a role</b>");
        ExtentReportManager.testSteps("<b><font color='blue'>Test Steps : </font></b>" +
                "<br>Step 1- Logged in to the System" +
                "<br>Step 2- Clicked User " +
                "<br>Step 3- Clicked Roles " +
                "<br>Step 4 - Selected Role from 'Search By' dropdown" +
                "<br>Step 4 - Entered Search Input" +
                "<br>Step 5 - Clicked Search"
        );

        search();
        Assert.assertEquals(webSteps.searchElement(1,1).getText().trim(), PropertyUtils.getProperty("Role_Name").trim(), "Search result does not match input value.");
    }

    @Test(priority = 3)
    public void editRole() throws InterruptedException, AWTException {
        ExtentReportManager.startTest("Roles Functionality", "<b>Update Role</b>");
        ExtentReportManager.testSteps("<b><font color='blue'>Test Case : </font>TC03: Verify that the user can successfully update a role</b>");
        ExtentReportManager.testSteps("<b><font color='blue'>Test Steps : </font></b>" +
                "<br>Step 1- Logged in to the System" +
                "<br>Step 2- Clicked User" +
                "<br>Step 3- Clicked Roles" +
                "<br>Step 4 - Searched the role name that needs to be edited" +
                "<br>Step 5 - Clicked Edit Action" +
                "<br>Step 6 - Made the necessary changes" +
                "<br>Step 7 - Clicked Update"
        );
        search();
        webSteps.clickAction(1,1).click();
        webSteps.type(webSteps.generateRandomRoleName(),"Role Name Field");
        webSteps.click("Update Button");

        Assert.assertEquals("Role updated successfully",webSteps.getText("Toast Message"), "Passed");
    }
}
