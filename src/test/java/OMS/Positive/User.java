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

    private String emailAddress = "sanidu@gmail.com";
    int i=1;

    @BeforeMethod
    public void setUp(Method method) throws IOException, InterruptedException {

        setUpReport(method);
        loadUrl();
        webSteps.login();
        webSteps.click("SideMenu User Tab");
        webSteps.click("SideMenu UserList Tab");
    }

    @Test(priority = 1, description = "TS001: Create User with Valid Credentials")
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

    @Test(dataProvider = "userSearchData", priority = 2, description = "TS002: Search User with Valid Filters")
    public void searchUser(String type, String searchInput) throws InterruptedException {
        if(i==3){ i+=1;};
        ExtentReportManager.testSteps("<b><font color='blue'>Test Case : </font>TC001: Search User with " + type + "</b>");

        webSteps.passValue(type, "Search Dropdown");
        webSteps.type(searchInput, "Search Box");
        webSteps.click("Search Button");

        Assert.assertEquals(searchInput.trim(), webSteps.searchElement(1,i++).getText().trim());
    }


    @Test(priority = 3, description = "TS003: Update Existing User from the List")
    public void updateUser() throws InterruptedException {
        if(webSteps.searchElement(1,4).getText().equals("kasun@gmail.com")){
            webSteps.editAction(2).click();
        } else {
            webSteps.editAction(1).click();
        }

        webSteps.elementToBeVisible("Name Field");
        webSteps.type(webSteps.getRandomNameFromTheList(), "Name Field");
        webSteps.click("Role Field");
        webSteps.selectFromDropdown();
//        webSteps.type("712345678", "Contact Number Field");
        webSteps.type(this.emailAddress, "Email Address Field");
        webSteps.type("NO: 200, Galle road, Matara", "Address Field");

        webSteps.waiting();
    }

}
