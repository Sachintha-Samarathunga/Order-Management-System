package OMS.Positive;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.BaseTest;
import utils.ExtentReportManager;

import java.awt.*;
import java.io.IOException;

public class User extends BaseTest {

    private String emailAddress = "sanidu@gmail.com";
    int i=1;

    @BeforeMethod
    public void setUp() throws IOException, InterruptedException {

        loadUrl();
        webSteps.login();
        webSteps.click("SideMenu User Tab");
        webSteps.click("SideMenu UserList Tab");
    }

    @Test(priority = 1)
    public void createUser() throws InterruptedException {

        ExtentReportManager.startTest("User Functionality", "<b>Create User</b>");
        ExtentReportManager.testSteps("<b><font color='blue'>Test Case : </font>TC01: Verify that the user can successfully add a user</b>");
        ExtentReportManager.testSteps("<b><font color='blue'>Test Steps : </font></b>" +
                "<br>Step 1- Login to the System" +
                "<br>Step 2- Click User " +
                "<br>Step 3- Click User List" +
                "<br>Step 4- Click 'Add New User' Button" +
                "<br>Step 5- Fill Primary Details" +
                "<br>Step 6- Click 'Save' Button"
        );

        this.emailAddress = webSteps.randomPersonalEmailAddress();

        webSteps.click("AddNewUser Button");

        webSteps.type("Amal", "Name Field");
        webSteps.click("Role Field");
        webSteps.selectFromDropdown();
        webSteps.type("712345678", "Contact Number Field");
        webSteps.type(this.emailAddress, "Email Address Field");
        webSteps.type("AmalaX@%234", "Password Field");
        webSteps.type("AmalaX@%234", "Confirm Password Field");
        webSteps.type("NO: 523, Ja-ela road, Gampaha", "Address Field");
        webSteps.click("Save Button");

        webSteps.waiting();
    }

    @DataProvider(name = "userSearchData")
    public Object[][] userSearchData() {
        return new Object[][]{
                {"Name", "Amal"},
                {"Role", "Admin"},
                {"Email", this.emailAddress},
                {"Address", "Dewalegama,Kegalle"}
        };
    }

    @Test(dataProvider = "userSearchData", priority = 2)
    public void searchUser(String type, String searchInput) throws InterruptedException {
        if(i==3){ i+=1;};
        ExtentReportManager.startTest("User Functionality", "<b>Search Existing User by " + type + "</b>");
        ExtentReportManager.testSteps("<b><font color='blue'>Test Case : </font>Verify that the user can search by " + type.toLowerCase() + "</b>");
        ExtentReportManager.testSteps("<b><font color='blue'>Test Steps : </font></b>" +
                "<br>Step 1 - Login to the System" +
                "<br>Step 2 - Click User" +
                "<br>Step 3 - Click User List" +
                "<br>Step 4 - Select '" + type + "' from 'Search By' dropdown" +
                "<br>Step 5 - Enter Search Input" +
                "<br>Step 6 - Click Search"
        );

        webSteps.passValue(type, "Search Dropdown");
        webSteps.type(searchInput, "Search Box");
        webSteps.click("Search Button");

        Assert.assertEquals(searchInput.trim(), webSteps.searchElement(1,i++).getText().trim());
    }


    @Test(priority = 3)
    public void updateUser() throws InterruptedException {
        ExtentReportManager.startTest("User Functionality", "<b>Update Existing User</b>");
        ExtentReportManager.testSteps("<b><font color='blue'>Test Case : </font>TC03: Verify that the user can successfully edit a user</b>");
        ExtentReportManager.testSteps("<b><font color='blue'>Test Steps : </font></b>" +
                "<br>Step 1- Login to the System" +
                "<br>Step 2- Click User " +
                "<br>Step 3- Click User List" +
                "<br>Step 4- Select a User" +
                "<br>Step 5- Click Edit Action" +
                "<br>Step 6- Edit Details" +
                "<br>Step 7- Click 'Update' Button"
        );

        if(webSteps.searchElement(1,4).getText().equals("kasun@gmail.com")){
            webSteps.clickAction(2, 1).click();
        } else {
            webSteps.clickAction(1, 1).click();
        }

        webSteps.elementToBeVisible("Name Field");
        webSteps.type(webSteps.getRandomNameFromTheList(), "Name Field");
        webSteps.click("Role Field");
        webSteps.selectFromDropdown();
        webSteps.type("712345678", "Contact Number Field");
        webSteps.type(this.emailAddress, "Email Address Field");
        webSteps.type("NO: 200, Galle road, Matara", "Address Field");

        webSteps.waiting();
    }

    @Test(priority = 4)
    public void changeUserPassword() throws InterruptedException, AWTException {
        ExtentReportManager.startTest("User Functionality", "<b>Change User Password</b>");
        ExtentReportManager.testSteps("<b><font color='blue'>Test Case : </font>TC04: Verify that the user can successfully change the user password</b>");
        ExtentReportManager.testSteps("<b><font color='blue'>Test Steps : </font></b>" +
                "<br>Step 1- Login to the System" +
                "<br>Step 2- Click User " +
                "<br>Step 3- Click User List" +
                "<br>Step 4- Click 'Password Change' Action" +
                "<br>Step 6- Enter New Password and Confirm Password" +
                "<br>Step 7- Click 'Reset' Button"
        );


        if(webSteps.searchElement(1,4).getText().equals("kasun@gmail.com")){
            webSteps.clickAction(2, 2).click();
        } else {
            webSteps.clickAction(1, 2).click();
        }
        webSteps.type("AmalaX@%234", "New Password Field");
        webSteps.type("AmalaX@%234", "Confirm Password Field");
        webSteps.click("Reset Button");
        Assert.assertEquals("Password changed successfully",webSteps.getText("Toast Message"), "Passed");
    }

}
