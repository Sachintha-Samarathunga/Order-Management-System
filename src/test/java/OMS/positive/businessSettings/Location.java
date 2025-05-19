package OMS.positive.businessSettings;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.BaseTest;
import utils.ExtentReportManager;
import utils.PropertyUtils;

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
        webSteps.type(webSteps.generateRandomLocationName(),"Location Name Field");
        webSteps.type(webSteps.generateRandomLocationAddress(),"Location Address Field");
        webSteps.type(webSteps.generateRandomLocationCity(),"Location City Field");
        webSteps.type("761236547","Location ContactNo Field");
        webSteps.type("parallax@gmail.com","Location Email Field");
        webSteps.click("Save Button");

        Assert.assertEquals("Location created successfully",webSteps.getText("Toast Message"));
    }

}
