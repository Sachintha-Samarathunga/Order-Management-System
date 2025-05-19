package OMS.positive.businessSettings;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.BaseTest;
import utils.ExtentReportManager;

import java.awt.*;
import java.io.IOException;

public class Location extends BaseTest {

    @BeforeMethod
    public void setUp() throws InterruptedException, IOException {
        loadUrl();
        webSteps.login();
        webSteps.waiting();
        webSteps.click("Settings Icon");
        webSteps.click("Business Settings");
        webSteps.click("Locations Tab");
    }

    @Test(priority = 1)
    public void addLocation() throws InterruptedException, AWTException {
        ExtentReportManager.startTest("Locations Functionality", "<b>Create Location</b>");
        ExtentReportManager.testSteps("<b><font color='blue'>Test Case : </font>TC01: Verify that the user can successfully create a location</b>");
        ExtentReportManager.testSteps("<b><font color='blue'>Test Steps : </font></b>" +
                "<br>Step 1- Logged in to the System" +
                "<br>Step 2- Clicked Settings " +
                "<br>Step 3- Clicked Business Settings " +
                "<br>Step 4- Clicked Locations " +
                "<br>Step 5- Clicked 'Add New Location' Button" +
                "<br>Step 6- Filled Details" +
                "<br>Step 7- Clicked 'Save' Button"
        );
        webSteps.click("Add New Location Button");
        webSteps.type(webSteps.generateRandomLocationName(),"Location_Name");
        webSteps.type(webSteps.generateRandomLocationAddress(),"Location_Address");
        webSteps.type(webSteps.generateRandomLocationCity(),"Location_City");
        webSteps.type(webSteps.generateRandomLocationContactNo(),"Location_ContactNo");
        webSteps.type(webSteps.generateRandomLocationEmail(),"Location_Email");
        webSteps.click("SaveButton");
        webSteps.implicitWait("ToastMessage");
        Assert.assertEquals("Location created successfully",webSteps.getText("ToastMessage"));
    }
}
