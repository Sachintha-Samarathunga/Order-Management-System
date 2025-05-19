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

    int i=2;

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

    @DataProvider(name = "locationSearchData")
    public Object[][] locationSearchData() {
        return new Object[][]{
                {"Location", PropertyUtils.getProperty("Location_Name")},
                {"Address", PropertyUtils.getProperty("Location_Address")},
                {"City", PropertyUtils.getProperty("Location_City")},
                {"Email", "parallax@gmail.com"},
        };
    }

    @Test(dataProvider = "locationSearchData", priority = 2)
    public void searchLocation (String type,String searchInput) throws InterruptedException {
        ExtentReportManager.startTest("Locations Functionality", "<b>Search Location Using " + type + "</b>");
        ExtentReportManager.testSteps("<b><font color='blue'>Test Case : </font>Verify that the location can search by " + type.toLowerCase() + "</b>");
        ExtentReportManager.testSteps("<b><font color='blue'>Test Steps : </font></b>" +
                "<br>Step 1 - Logged in to the System" +
                "<br>Step 2- Clicked Settings " +
                "<br>Step 3- Clicked Business Settings " +
                "<br>Step 4- Clicked Locations " +
                "<br>Step 5 - Selected '" + type + "' from 'Search By' dropdown" +
                "<br>Step 6 - Entered Search Input" +
                "<br>Step 7 - Clicked Search"
        );

        if(i==5){ i+=1;};
        webSteps.passValue(type,"Search Dropdown");
        webSteps.type(searchInput,"Search Box");
        webSteps.click("Search Button");

        Assert.assertEquals(webSteps.searchElement(1,i++), searchInput.trim());
    }

    @Test(priority = 3)
    public void editLocation() throws InterruptedException, AWTException {
        ExtentReportManager.startTest("Locations Functionality", "<b>Edit Location</b>");
        ExtentReportManager.testSteps("<b><font color='blue'>Test Case : </font>TC03: Verify that the user can successfully edit a location</b>");
        ExtentReportManager.testSteps("<b><font color='blue'>Test Steps : </font></b>" +
                "<br>Step 1- Logged in to the System" +
                "<br>Step 2- Clicked Settings " +
                "<br>Step 3- Clicked Business Settings " +
                "<br>Step 4- Clicked Locations " +
                "<br>Step 3- Selected a Location" +
                "<br>Step 4- Clicked Edit Action" +
                "<br>Step 5- Edited Details" +
                "<br>Step 6- Clicked 'Update' Button"
        );
        webSteps.passValue("Location","SearchBy_Dropdown");
        webSteps.type(PropertyUtils.getProperty("Location_Name"),"SearchBy_SearchBar");
        webSteps.click("SearchBy_SearchButton");
        webSteps.clickAction(1,1).click();
        webSteps.type(webSteps.generateRandomLocationName(),"Location Name Field");
        webSteps.type(webSteps.generateRandomLocationAddress(),"Location Address Field");
        webSteps.type(webSteps.generateRandomLocationCity(),"Location City Field");
        webSteps.type("761236548","Location ContactNo Field");
        webSteps.type("parallax01@gmail.com","Location Email Field");
        webSteps.click("Update Button");
        Assert.assertEquals("Location updated successfully",webSteps.getText("Toast Message"), "Passed");
    }

}
