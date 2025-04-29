package OMS.Positive;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.BaseTest;
import utils.ExtentReportManager;

import java.io.IOException;

public class User extends BaseTest {

    private String emailAddress;
    int i=1;

    @BeforeMethod
    public void setUp() throws IOException, InterruptedException {
        loadUrl();
        webSteps.login();
        webSteps.click("SideMenu User Tab");
        webSteps.click("SideMenu UserList Tab");
    }

    @Test
    public void createUser() throws InterruptedException {

        this.emailAddress = webSteps.randomPersonalEmailAddress();

        ExtentReportManager.startTest("User Management", "<b>Test Cases for User Management</b>");
        ExtentReportManager.testSteps("<b><font color='blue'>Test Case : </font>TC001: Create User with Valid Credentials</b>");

        webSteps.click("AddNewUser Button");

        webSteps.type("Amal", "Name Field");
        ExtentReportManager.testSteps("Entered name");
        webSteps.click("Role Field");
        webSteps.selectFromDropdown();
        ExtentReportManager.testSteps("Selected user role");
        webSteps.type("712345678", "Contact Number Field");
        ExtentReportManager.testSteps("Entered mobile number");
        webSteps.type(this.emailAddress, "Email Address Field");
        ExtentReportManager.testSteps("Entered email address");
        webSteps.type("AmalaX@%234", "Password Field");
        ExtentReportManager.testSteps("Set the password");
        webSteps.type("AmalaX@%234", "Confirm Password Field");
        ExtentReportManager.testSteps("Confirmed password");
        webSteps.type("NO: 523, Ja-ela road, Gampaha", "Address Field");
        ExtentReportManager.testSteps("Entered address");

        webSteps.waiting();
    }

    @DataProvider(name = "userSearchData")
    public Object[][] userSearchData() {
        return new Object[][]{
                {"Name", "Kasun Bandara"},
                {"Role", "Admin"},
                {"Email", "kasun@gmail.com"},
                {"Address", "Dewalegama,Kegalle"}
        };
    }

    @Test(dataProvider = "userSearchData", priority = 2)
    public void searchUser(String type, String searchInput) throws InterruptedException {
        if(i==3){ i+=1;};
        ExtentReportManager.startTest("User Management", "<b>Test Cases for User Management</b>");
        ExtentReportManager.testSteps("<b><font color='blue'>Test Case : </font>TC001: Search User with " + type + "</b>");

        webSteps.passValue(type, "Search Dropdown");
        webSteps.type(searchInput, "Search Box");
        webSteps.click("Search Button");

        Assert.assertEquals(searchInput.trim(), webSteps.searchElement(1,i++).getText().trim());

    }

}
