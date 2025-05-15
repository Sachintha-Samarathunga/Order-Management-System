package OMS.Positive;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.BaseTest;
import utils.ExtentReportManager;

import java.awt.*;
import java.io.IOException;

public class Contact extends BaseTest {

    @BeforeMethod
    public void setUP() throws IOException, InterruptedException {
        loadUrl();
        webSteps.login();
        webSteps.click("SideMenu Contacts Tab");
    }

    @Test(priority = 1)
    public void createContact() throws InterruptedException, AWTException {
        ExtentReportManager.startTest("Contacts Functionality", "<b>Create Customer</b>");
        ExtentReportManager.testSteps("<b><font color='blue'>Test Case : </font>TC01: Verify that the user can successfully create a customer</b>");
        ExtentReportManager.testSteps("<b><font color='blue'>Test Steps : </font></b>" +
                "<br>Step 1- Login to the System" +
                "<br>Step 2- Navigated to the contacts " +
                "<br>Step 3- Clicked 'Add New Customer' Button" +
                "<br>Step 5- Filled Primary Details" +
                "<br>Step 6- Clicked 'Save' Button"
        );

        webSteps.click("AddNewCustomer Button");
        webSteps.type("Sanjaya","Contacts-Contact Name Field");
        webSteps.type("CU000101","Contacts-ReferenceID");
        webSteps.type("761234567","Contacts-Contact No.1");
        webSteps.type("767654321","Contacts-Contact No.2");
        webSteps.type("181/A, Galle road, Matara","Contacts-Shipping Address Field");
        webSteps.click("Contacts-City Field");
        webSteps.selectFromDropdown();
        webSteps.type("contact@gmail.com","Contacts-Email Field");
        //webSteps.click("AddContact_SaveButton");
        //Assert.assertEquals("Contact created successfully",webSteps.getText("ToastMessage"), "Passed");
    }

    @Test(priority = 2)
    public void updateContact() throws InterruptedException, AWTException {
        ExtentReportManager.startTest("Contacts Functionality", "<b>Update Customer</b>");
        ExtentReportManager.testSteps("<b><font color='blue'>Test Case : </font>TC01: Verify that the user can successfully update a customer</b>");
        ExtentReportManager.testSteps("<b><font color='blue'>Test Steps : </font></b>" +
                "<br>Step 1- Login to the System" +
                "<br>Step 2- Navigated to the contacts" +
                "<br>Step 3- Selected a Customer" +
                "<br>Step 4- Clicked Edit Action" +
                "<br>Step 5- Edit Details" +
                "<br>Step 6- Clicked 'Update' Button"
        );
        webSteps.clickAction(1,1);
        webSteps.type("kalani","Contacts-Contact Name Field");
        webSteps.type("CU000102","Contacts-ReferenceID");
        webSteps.type("761234568","Contacts-Contact No.1");
        webSteps.type("767654321","Contacts-Contact No.2");
        webSteps.type("250/A, Galle road, Panadura","Contacts-Shipping Address Field");
        webSteps.click("Contacts-City Field");
        webSteps.selectFromDropdown();
        webSteps.type("contact10@gmail.com","Contacts-Email Field");
        //webSteps.click("EditCustomer_UpdateButton");
        //Assert.assertEquals("Contact created successfully",webSteps.getText("ToastMessage"), "Passed");
    }
}
