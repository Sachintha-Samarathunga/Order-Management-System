package OMS.Positive;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.BaseTest;
import utils.ExtentReportManager;

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
}
