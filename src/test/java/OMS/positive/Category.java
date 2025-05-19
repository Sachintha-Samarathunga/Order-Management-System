package OMS.positive;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.BaseTest;
import utils.ExtentReportManager;
import utils.PropertyUtils;

import java.awt.*;
import java.io.IOException;

public class Category extends BaseTest {

    @BeforeMethod
    public void setUp() throws InterruptedException, IOException {
        loadUrl();
        webSteps.login();
        webSteps.waiting();
        webSteps.click("SideMenu Products Tab");
        webSteps.click("SideMenu Categories Tab");
    }

    public void search() throws InterruptedException {
        webSteps.passValue("Category Name","Search Dropdown");
        webSteps.type(PropertyUtils.getProperty("Category_Name"),"Search Box");
        webSteps.click("Search Button");
    }

    @Test(priority = 1)
    public void addCategory() throws InterruptedException, AWTException {
        ExtentReportManager.startTest("Categories Functionality", "<b>Create Category</b>");
        ExtentReportManager.testSteps("<b><font color='blue'>Test Case : </font>TC01: Verify that the user can successfully create a category</b>");
        ExtentReportManager.testSteps("<b><font color='blue'>Test Steps : </font></b>" +
                "<br>Step 1- Logged in to the System" +
                "<br>Step 2- Clicked Products " +
                "<br>Step 3- Clicked Categories " +
                "<br>Step 3- Clicked 'Add New Category' Button" +
                "<br>Step 5- Filled Details" +
                "<br>Step 6- Clicked 'Save' Button"
        );
        webSteps.click("Add New Category Button");
        webSteps.type(webSteps.generateRandomCategoryName(),"Category Name Field");
        webSteps.type("Dummy Category Description","Category Remark Field");
        webSteps.click("Save Button");

        Assert.assertEquals("Category created successfully",webSteps.getText("Toast Message"), "Passed");
    }

    @Test(priority = 2)
    public void searchCategory() throws InterruptedException, AWTException {
        ExtentReportManager.startTest("Categories Functionality", "<b>Search Category</b>");
        ExtentReportManager.testSteps("<b><font color='blue'>Test Case : </font>TC02: Verify that the user can successfully search a category</b>");
        ExtentReportManager.testSteps("<b><font color='blue'>Test Steps : </font></b>" +
                "<br>Step 1- Logged in to the System" +
                "<br>Step 2- Clicked Products " +
                "<br>Step 3- Clicked Categories " +
                "<br>Step 4 - Selected Category Name from 'Search By' dropdown" +
                "<br>Step 4 - Entered Search Input" +
                "<br>Step 5 - Clicked Search"
        );

        search();
        Assert.assertEquals(webSteps.searchElement(1,1).getText().trim(), PropertyUtils.getProperty("Category_Name"), "Search result does not match input value.");
    }

    @Test(priority = 3)
    public void updateCategory() throws InterruptedException, AWTException {
        ExtentReportManager.startTest("Categories Functionality", "<b>Update Category</b>");
        ExtentReportManager.testSteps("<b><font color='blue'>Test Case : </font>TC03: Verify that the user can successfully update a category</b>");
        ExtentReportManager.testSteps("<b><font color='blue'>Test Steps : </font></b>" +
                "<br>Step 1- Logged in to the System" +
                "<br>Step 2- Clicked Products " +
                "<br>Step 3- Clicked Categories " +
                "<br>Step 4 - Searched the category name that needs to be update" +
                "<br>Step 5 - Clicked Edit Action" +
                "<br>Step 6 - Made the necessary changes" +
                "<br>Step 7 - Clicked Update"
        );

        search();
        webSteps.clickAction(1,1).click();
        webSteps.type(webSteps.generateRandomCategoryName(),"Category Name Field");
        webSteps.type("Updated dummy Category Description","Category Remark Field");
        webSteps.click("Update Button");
        Assert.assertEquals("Category updated successfully",webSteps.getText("Toast Message"), "Passed");
    }
}
