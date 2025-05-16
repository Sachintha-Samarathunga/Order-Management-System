package OMS.Positive;


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

    @BeforeMethod
    public void setUp() throws InterruptedException, IOException {
        loadUrl();
        webSteps.login();
        webSteps.waiting();
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

        Assert.assertEquals("Unit created successfully",webSteps.getText("ToastMessage"), "Passed");
    }

    @DataProvider(name = "searchUnit")
    public Object[][] searchUnit() {
        return new Object[][] {
                { "Unit Name",1, PropertyUtils.getProperty("Unit_Name")},
                { "Short Name",2, PropertyUtils.getProperty("Short_Name")}
        };
    }
    @Test(priority = 2, dataProvider = "searchUnit")
    public void searchUnit(String searchBy,int tableColumnIndex, String searchInput) throws InterruptedException, AWTException {
        ExtentReportManager.startTest("Units Functionality", "<b>Search Unit</b>");
        ExtentReportManager.testSteps(
                "<b><font color='blue'>Test Case : </font>TC02: Verify that the user can successfully search an unit</b> " +
                        "<br><b><font color='blue'>Test Steps : </font></b>" +
                        "<br>Step 1 - Logged to the System" +
                        "<br>Step 2 - Clicked Products" +
                        "<br>Step 3 - Clicked Units" +
                        "<br>Step 4 - Selected '" + searchBy + "' from 'Search By' dropdown" +
                        "<br>Step 5 - Entered Search Input" +
                        "<br>Step 6 - Clicked Search"
        );
        webSteps.passValue(searchBy,"SearchBy_Dropdown");
        webSteps.type(searchInput, "SearchBy_SearchBar");
        webSteps.click("SearchBy_SearchButton");
//        String actualResult = webSteps.getTableCellText(1, tableColumnIndex);
//        Assert.assertEquals(actualResult, searchInput, "Search result does not match input value.");
    }
//
//    @Test(priority = 3)
//    public void editUnit() throws InterruptedException, AWTException {
//        ExtentReportManager.startTest("Units Functionality", "<b>Edit Unit</b>");
//        ExtentReportManager.testSteps("<b><font color='blue'>Test Case : </font>TC03: Verify that the user can successfully edit an unit</b>");
//        ExtentReportManager.testSteps("<b><font color='blue'>Test Steps : </font></b>" +
//                "<br>Step 1- Login to the System" +
//                "<br>Step 2- Click Products " +
//                "<br>Step 3- Click Units " +
//                "<br>Step 4 - Search the unit name that needs to be edited" +
//                "<br>Step 5 - Click Edit Action" +
//                "<br>Step 6 - Make the necessary changes" +
//                "<br>Step 7 - Click Update"
//        );
//        webSteps.passValue("Short Name","SearchBy_Dropdown");
//        webSteps.type(PropertyUtils.getProperty("Short_Name"),"SearchBy_SearchBar");
//        webSteps.click("SearchBy_SearchButton");
//        webSteps.click("Action1");
//        webSteps.type(webSteps.generateRandomUnitName(),"Unit_Name");
//        webSteps.type(webSteps.generateRandomUnitShortName(),"Short_Name");
//        webSteps.click("AllowDecimalCheckBox");
//        webSteps.click("UpdateButton");
//        Assert.assertEquals("Unit updated successfully",webSteps.getText("ToastMessage"), "Passed");
//    }
}