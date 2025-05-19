package OMS.positive;


import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.BaseTest;

import utils.ExtentReportManager;
import utils.PropertyUtils;

import java.awt.*;
import java.io.IOException;

public class Unit extends BaseTest {

    static int i=1;

    @BeforeMethod
    public void setUp() throws InterruptedException, IOException {
        loadUrl();
        webSteps.login();
        webSteps.click("SideMenu Products Tab");
        webSteps.click("SideMenu Units Tab");
    }

    @Test(priority = 1)
    public void addUnit() throws InterruptedException, AWTException {
        ExtentReportManager.startTest("Units Functionality", "<b>Create Unit</b>");
        ExtentReportManager.testSteps("<b><font color='blue'>Test Case : </font>TC01: Verify that the user can successfully create an unit</b>");
        ExtentReportManager.testSteps("<b><font color='blue'>Test Steps : </font></b>" +
                "<br>Step 1- Logged into the System" +
                "<br>Step 2- Clicked Products " +
                "<br>Step 3- Clicked Units " +
                "<br>Step 3- Clicked 'Add New Unit' Button" +
                "<br>Step 5- Filled Details" +
                "<br>Step 6- Clicked 'Save' Button"
        );
        webSteps.click("Add New Unit Button");
        webSteps.type(webSteps.generateRandomUnitName(),"Unit Name Field");
        webSteps.type(webSteps.generateRandomUnitShortName(),"Short  Name Field");
        webSteps.click("Save Button");

        Assert.assertEquals("Unit created successfully",webSteps.getText("Toast Message"), "Passed");
    }

    @DataProvider(name = "searchUnit")
    public Object[][] searchUnit() {
        return new Object[][] {
                { "Unit Name", PropertyUtils.getProperty("Unit_Name")},
                { "Short Name", PropertyUtils.getProperty("Short_Name")}
        };
    }
    @Test(priority = 2, dataProvider = "searchUnit")
    public void searchUnit(String searchBy, String searchInput) throws InterruptedException, AWTException {
        ExtentReportManager.startTest("Units Functionality", "<b>Search Unit</b>");
        ExtentReportManager.testSteps(
                "<b><font color='blue'>Test Case : </font>TC0"+(i+1)+": Verify that the user can successfully search an unit by "+searchBy+"</b> " +
                        "<br><b><font color='blue'>Test Steps : </font></b>" +
                        "<br>Step 1 - Logged to the System" +
                        "<br>Step 2 - Clicked Products" +
                        "<br>Step 3 - Clicked Units" +
                        "<br>Step 4 - Selected '" + searchBy + "' from 'Search By' dropdown" +
                        "<br>Step 5 - Entered Search Input" +
                        "<br>Step 6 - Clicked Search"
        );
        webSteps.passValue(searchBy,"Search Dropdown");
        webSteps.type(searchInput, "Search Box");
        webSteps.click("Search Button");

        Assert.assertEquals(searchInput.trim(), webSteps.searchElement(1,i++).getText().trim());
    }
//
    @Test(priority = 3)
    public void editUnit() throws InterruptedException, AWTException {
        ExtentReportManager.startTest("Units Functionality", "<b>Update Unit</b>");
        ExtentReportManager.testSteps("<b><font color='blue'>Test Case : </font>TC04: Verify that the user can successfully update an unit</b>");
        ExtentReportManager.testSteps("<b><font color='blue'>Test Steps : </font></b>" +
                "<br>Step 1- Logged in to the System" +
                "<br>Step 2- Clicked Products " +
                "<br>Step 3- Clicked Units " +
                "<br>Step 4 - Searched the unit name that needs to be edited" +
                "<br>Step 5 - Clicked Edit Action" +
                "<br>Step 6 - Made the necessary changes" +
                "<br>Step 7 - Clicked Update"
        );
        webSteps.passValue("Short Name","Search Dropdown");
        webSteps.type(PropertyUtils.getProperty("Short_Name"),"Search Box");
        webSteps.click("Search Button");
        webSteps.clickAction(1,1).click();
        webSteps.type(webSteps.generateRandomUnitName(),"Unit Name Field");
        webSteps.type(webSteps.generateRandomUnitShortName(),"Short  Name Field");
        webSteps.click("Allow Decimal Checkbox");
        webSteps.click("Update Button");
        Assert.assertEquals("Unit updated successfully",webSteps.getText("Toast Message"), "Passed");
    }
}