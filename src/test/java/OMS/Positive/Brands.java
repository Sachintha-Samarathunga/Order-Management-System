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
        webSteps.click("ClickProducts");
        webSteps.click("ClickBrands");
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
        webSteps.type(webSteps.generateRandomBrandName(),"Brand  Name Field");
        webSteps.type("Testing_Brand Description","Brand Remark Field");
        webSteps.click("Save Button");

        Assert.assertEquals("Brand created successfully",webSteps.getText("Toast Message"), "Passed");
    }

//    @Test(priority = 2)
//    public void searchBrand() throws InterruptedException, AWTException {
//        extentReportManager.startTest("Brands Functionality", "<b>Search Brand</b>");
//        extentReportManager.testSteps("<b><font color='blue'>Test Case : </font>TC02: Verify that the user can successfully search a brand</b>");
//        extentReportManager.testSteps("<b><font color='blue'>Test Steps : </font></b>" +
//                "<br>Step 1- Login to the System" +
//                "<br>Step 2- Click Products " +
//                "<br>Step 3- Click Brands " +
//                "<br>Step 4 - Select Brand Name from 'Search By' dropdown" +
//                "<br>Step 4 - Enter Search Input" +
//                "<br>Step 5 - Click Search"
//        );
//        webSteps.passValue("Brand Name","SearchBy_Dropdown");
//        webSteps.type(PropertyUtils.getProperty("Brand_Name"),"SearchBy_SearchBar");
//        webSteps.click("SearchBy_SearchButton");
//        String actualResult = webSteps.getTableCellText(1, 1);
//        Assert.assertEquals(actualResult, PropertyUtils.getProperty("Brand_Name"), "Search result does not match input value.");
//    }
//
//    @Test(priority = 3)
//    public void editBrand() throws InterruptedException, AWTException {
//        extentReportManager.startTest("Brands Functionality", "<b>Edit Brand</b>");
//        extentReportManager.testSteps("<b><font color='blue'>Test Case : </font>TC03: Verify that the user can successfully edit a brand</b>");
//        extentReportManager.testSteps("<b><font color='blue'>Test Steps : </font></b>" +
//                "<br>Step 1- Login to the System" +
//                "<br>Step 2- Click Products " +
//                "<br>Step 3- Click Brands " +
//                "<br>Step 4 - Search the brand name that needs to be edited" +
//                "<br>Step 5 - Click Edit Action" +
//                "<br>Step 6 - Make the necessary changes" +
//                "<br>Step 7 - Click Update"
//        );
//        webSteps.passValue("Brand Name","SearchBy_Dropdown");
//        webSteps.type(PropertyUtils.getProperty("Brand_Name"),"SearchBy_SearchBar");
//        webSteps.click("SearchBy_SearchButton");
//        webSteps.click("Action1");
//        webSteps.type(webSteps.generateRandomBrandName(),"Brand_Name");
//        webSteps.type("Testing_Edit Brand Description","Brand_Remark");
//        webSteps.click("UpdateButton");
//        Assert.assertEquals("Brand updated successfully",webSteps.getText("ToastMessage"), "Passed");
//    }
}