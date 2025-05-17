package OMS.Positive;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.BaseTest;
import utils.ExtentReportManager;
import utils.PropertyUtils;
import java.awt.*;
import java.io.IOException;

public class Brands extends BaseTest {

    String brandName;

    @BeforeMethod
    public void setUp() throws InterruptedException, IOException {
        loadUrl();
        webSteps.login();
        webSteps.waiting();
        webSteps.click("SideMenu Products Tab");
        webSteps.click("SideMenu Brands Tab");
    }

    public void search() throws InterruptedException {
        webSteps.passValue("Brand Name","Search Dropdown");
        webSteps.type(PropertyUtils.getProperty("Brand_Name"),"Search Box");
        webSteps.click("Search Button");
    }

    @Test(priority = 1)
    public void addBrand() throws InterruptedException, AWTException {
        ExtentReportManager.startTest("Brands Functionality", "<b>Create Brand</b>");
        ExtentReportManager.testSteps("<b><font color='blue'>Test Case : </font>TC01: Verify that the user can successfully create a brand</b>");
        ExtentReportManager.testSteps("<b><font color='blue'>Test Steps : </font></b>" +
                "<br>Step 1- Logged in to the System" +
                "<br>Step 2- Clicked Products " +
                "<br>Step 3- Clicked Brands " +
                "<br>Step 3- Clicked 'Add New Brand' Button" +
                "<br>Step 5- Filled Details" +
                "<br>Step 6- Clicked 'Save' Button"
        );
        webSteps.click("Add New Brand Button");
        webSteps.type(webSteps.generateRandomBrandName(),"Brand Name Field");
        webSteps.type("Testing_Brand Description","Brand Remark Field");
        webSteps.click("Save Button");

        Assert.assertEquals("Brand created successfully",webSteps.getText("Toast Message"), "Passed");
    }

    @Test(priority = 2)
    public void searchBrand() throws InterruptedException, AWTException {
        ExtentReportManager.startTest("Brands Functionality", "<b>Search Brand</b>");
        ExtentReportManager.testSteps("<b><font color='blue'>Test Case : </font>TC02: Verify that the user can successfully search a brand</b>");
        ExtentReportManager.testSteps("<b><font color='blue'>Test Steps : </font></b>" +
                "<br>Step 1- Logged in to the System" +
                "<br>Step 2- Clicked Products " +
                "<br>Step 3- Clicked Brands " +
                "<br>Step 4 - Selected Brand Name from 'Search By' dropdown" +
                "<br>Step 4 - Entered Search Input" +
                "<br>Step 5 - Clicked Search"
        );

        search();
        Assert.assertEquals(webSteps.searchElement(1,1).getText().trim(),PropertyUtils.getProperty("Brand_Name"),"Search result does not match input value.");
    }

    @Test(priority = 3)
    public void editBrand() throws InterruptedException, AWTException {
        ExtentReportManager.startTest("Brands Functionality", "<b>Update Brand</b>");
        ExtentReportManager.testSteps("<b><font color='blue'>Test Case : </font>TC03: Verify that the user can successfully update a brand</b>");
        ExtentReportManager.testSteps("<b><font color='blue'>Test Steps : </font></b>" +
                "<br>Step 1- Logged in to the System" +
                "<br>Step 2- Clicked Products " +
                "<br>Step 3- Clicked Brands " +
                "<br>Step 4 - Searched the brand name that needs to be edited" +
                "<br>Step 5 - Clicked Edit Action" +
                "<br>Step 6 - Made the necessary changes" +
                "<br>Step 7 - Clicked Update"
        );
        search();
        webSteps.clickAction(1,1).click();
        webSteps.type(webSteps.generateRandomBrandName(),"Brand Name Field");
        webSteps.type("Updated Brand Description","Brand Remark Field");
        webSteps.click("Update Button");

        Assert.assertEquals("Brand updated successfully",webSteps.getText("Toast Message"), "Passed");
    }
}