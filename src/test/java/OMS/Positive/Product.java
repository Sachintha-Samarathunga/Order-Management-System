package OMS.Positive;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.BaseTest;
import utils.ExtentReportManager;
import utils.PropertyUtils;

import java.awt.*;
import java.io.IOException;

public class Product extends BaseTest {

    @BeforeMethod
    public void setUp() throws InterruptedException, IOException {

        loadUrl();
        webSteps.login();
        webSteps.waiting();
        webSteps.click("SideMenu Products Tab");
        webSteps.click("SideMenu ProductList Tab");
    }

    public void search() throws InterruptedException {
        webSteps.passValue("Product Name","Search Dropdown");
        webSteps.type(PropertyUtils.getProperty("Product_Name"),"Search Box");
        webSteps.click("Search Button");
    }

    @Test(priority = 1)
    public void addProduct() throws InterruptedException, AWTException {
        ExtentReportManager.startTest("Products Functionality", "<b>Create Product</b>");
        ExtentReportManager.testSteps("<b><font color='blue'>Test Case : </font>TC01: Verify that the user can successfully create a product</b>");
        ExtentReportManager.testSteps("<b><font color='blue'>Test Steps : </font></b>" +
                "<br>Step 1- Logged in to the System" +
                "<br>Step 2- Clicked Products " +
                "<br>Step 3- Clicked Product List " +
                "<br>Step 3- Clicked 'Add New Product' Button" +
                "<br>Step 5- Filled Details" +
                "<br>Step 6- Clicked 'Save' Button"
        );
        webSteps.click("Add New Product Button");
        webSteps.type(webSteps.generateRandomProductName(),"Product Name Field");
        webSteps.searchFromDropdown("Unit_Name","Product Unit Field");
        webSteps.searchFromDropdown("Brand_Name","Product Brand Field");
        webSteps.searchFromDropdown("Category_Name","Product Category Field");
        webSteps.type("1000","Product SellingPrice Field");
        webSteps.type("Dummy Product Description","Product Description Field");
        webSteps.click("Save Button");

        Assert.assertEquals("Product created successfully",webSteps.getText("Toast Message"), "Passed");
    }

    @Test(priority = 2)
    public void searchProduct() throws InterruptedException, AWTException {
        ExtentReportManager.startTest("Products Functionality", "<b>Search Product</b>");
        ExtentReportManager.testSteps("<b><font color='blue'>Test Case : </font>TC02: Verify that the user can successfully search a product</b>");
        ExtentReportManager.testSteps("<b><font color='blue'>Test Steps : </font></b>" +
                "<br>Step 1- Logged in to the System" +
                "<br>Step 2- Clicked Products " +
                "<br>Step 3- Clicked Product List " +
                "<br>Step 4 - Selected Product Name from 'Search By' dropdown" +
                "<br>Step 4 - Entered Search Input" +
                "<br>Step 5 - Clicked Search"
        );

        search();
        Assert.assertEquals(webSteps.searchElement(1,2).getText().trim(), PropertyUtils.getProperty("Product_Name"), "Search result does not match input value.");
    }

    @Test(priority = 3)
    public void editProduct() throws InterruptedException, AWTException {
        ExtentReportManager.startTest("Products Functionality", "<b>Update Product</b>");
        ExtentReportManager.testSteps("<b><font color='blue'>Test Case : </font>TC03: Verify that the user can successfully update a product</b>");
        ExtentReportManager.testSteps("<b><font color='blue'>Test Steps : </font></b>" +
                "<br>Step 1- Logged in to the System" +
                "<br>Step 2- Clicked Products " +
                "<br>Step 3- Clicked Product List " +
                "<br>Step 4 - Searched the product name that needs to be edited" +
                "<br>Step 5 - Clicked Edit Action" +
                "<br>Step 6 - Made the necessary changes" +
                "<br>Step 7 - Clicked Update"
        );

        search();
        webSteps.clickAction(1,1).click();
        webSteps.type(webSteps.generateRandomProductName(),"Product Name Field");
        webSteps.type("Updated dummy Product Description","Product Description Field");
        webSteps.click("Save Button");

        Assert.assertEquals("Product updated successfully",webSteps.getText("Toast Message"), "Passed");
    }
}
