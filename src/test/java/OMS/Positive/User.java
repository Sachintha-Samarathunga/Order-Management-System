package OMS.Positive;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.BaseTest;
import utils.ExtentReportManager;

import java.io.IOException;
import java.lang.reflect.Method;

public class User extends BaseTest {

    private String emailAddress = "kasun@gmail.com";
    int i=1;

    @BeforeMethod
    public void setUp(Method method) throws IOException, InterruptedException {

        setUpReport(method);
        loadUrl();
        webSteps.login();
        webSteps.click("SideMenu User Tab");
        webSteps.click("SideMenu UserList Tab");
    }

    @Test(priority = 1, description = "TC001: Create User with Valid Credentials")
    public void createUser() throws InterruptedException {

        this.emailAddress = webSteps.randomPersonalEmailAddress();

        webSteps.click("AddNewUser Button");

        webSteps.type("Amal", "Name Field");
        webSteps.click("Role Field");
        webSteps.selectFromDropdown();
//        webSteps.type("712345678", "Contact Number Field");
        webSteps.type(this.emailAddress, "Email Address Field");
        webSteps.type("AmalaX@%234", "Password Field");
        webSteps.type("AmalaX@%234", "Confirm Password Field");
        webSteps.type("NO: 523, Ja-ela road, Gampaha", "Address Field");

        webSteps.waiting();
    }

    @DataProvider(name = "userSearchData")
    public Object[][] userSearchData() {
        return new Object[][]{
                {"Name", "Kasun Bandara"},
                {"Role", "Admin"},
                {"Email", this.emailAddress},
                {"Address", "Dewalegama,Kegalle"}
        };
    }

    @Test(dataProvider = "userSearchData", priority = 2, description = "TC002: Search User with Valid Filters")
    public void searchUser(String type, String searchInput) throws InterruptedException {
        if(i==3){ i+=1;};
        ExtentReportManager.testSteps("<b><font color='blue'>Test Case : </font>TC001: Search User with " + type + "</b>");

        webSteps.passValue(type, "Search Dropdown");
        webSteps.type(searchInput, "Search Box");
        webSteps.click("Search Button");

        Assert.assertEquals(searchInput.trim(), webSteps.searchElement(1,i++).getText().trim());
    }


}
