package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.time.Duration;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static dataProviders.repositoryFileReader.constructElement;
import static dataProviders.repositoryFileReader.findElementRepo;

public class webSteps {
    protected static WebDriver driver;
    private final String businessName;
    private final String email;
    private final String password;


    public webSteps(WebDriver driver) {
        webSteps.driver = driver;

        // Load email and password from properties file
        Properties properties = PropertyLoader.loadProperties("src/main/resources/dataset.properties");
        this.businessName = properties.getProperty("business");
        this.email = properties.getProperty("email");
        this.password = properties.getProperty("password");
    }

    public void login() throws InterruptedException {
        waiting();
        type(businessName, "Business Name");
        type(email, "Email");
        type(password, "Password");
        click("Login Button");
        waiting();
    }

    public void type(String text, String locator) throws InterruptedException {
        By xpath = constructElement(findElementRepo(locator));
        WebElement inputField = driver.findElement(xpath);

        elementToBeVisible(locator);
        inputField.click();

        String existingValue = inputField.getAttribute("value");
        if (existingValue != null && !existingValue.isEmpty()) {
            inputField.clear();
            inputField.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        }

        inputField.sendKeys(text);
        waiting();
    }

    public void passValue(String text, String locator){
        By xpath = constructElement(findElementRepo(locator));
        driver.findElement(xpath).sendKeys(text);
    }


    public void clearInputField(String locator) {
        By xpath = constructElement(findElementRepo(locator));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            WebElement inputField = wait.until(ExpectedConditions.elementToBeClickable(xpath));
            inputField.click();
            inputField.clear();
            inputField.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        } catch (Exception e) {
            System.out.println("Failed to clear input field: " + e.getMessage());
        }
    }


    public void click(String locator) throws InterruptedException {
        By xpath = constructElement(findElementRepo(locator));
        WebElement button =  driver.findElement(xpath);
        button.click();

        waiting();
    }

    public String getText(String locator) {
        By xpath = constructElement(findElementRepo(locator));
        return driver.findElement(xpath).getText();
    }

    public void waiting() throws InterruptedException {
        Thread.sleep(2000);
    }


    public void scrollToElement(String locator) throws InterruptedException {
        By xpath = constructElement(findElementRepo(locator));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(10000));
        WebElement webElement = wait.until(ExpectedConditions.presenceOfElementLocated(xpath));
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", webElement);
        waiting();

    }
    
    public String randomPersonalEmailAddress(){
        return ("example" + ThreadLocalRandom.current().nextInt(0, 10000) + "@gmail.com");
    }

    public void select(String locator, int loop, int bool) throws InterruptedException, AWTException {
        By xpath = constructElement(findElementRepo(locator));
        click(locator);

        Robot robot = new Robot();

        if(bool==1){
            for (int i=1;i<=loop; i++){
                robot.keyPress(KeyEvent.VK_DOWN);
                robot.keyRelease(KeyEvent.VK_DOWN);

                Thread.sleep(100);
            }

            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);


        }else if(bool==0){
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);

        } else {
            System.out.println("The boolean value is invalid");
        }

        waiting();
    }

    // Method for wait until an element to be clickable
    public void waitUntilElementToBeClickable(String locator){
        By xpath = constructElement(findElementRepo(locator));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(xpath));
    }

    // Method for wait until an element to be visible
    public void elementToBeVisible(String locator){
        By xpath = constructElement(findElementRepo(locator));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(xpath));
    }

    public void implicitWait(String locator){
        By xpath = constructElement(findElementRepo(locator));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {

            wait.until(ExpectedConditions.presenceOfElementLocated(xpath));
        } catch (TimeoutException e) {
            System.out.println("Element not found after login: " + e.getMessage());

        }
    }


    //date picker
    public void selectDate(String datePickerLocator, String date) throws InterruptedException {

        click(datePickerLocator);

        // Wait for the calendar to be visiblee
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[contains(@role, 'grid')]")));

        // Locate the button for the specified date
        String dateButtonXPath = String.format("//button[@name='day' and text()='%s']", date);
        WebElement dateButton = driver.findElement(By.xpath(dateButtonXPath));

        // Click the date button
        dateButton.click();
        waiting();
    }

    // Common method to upload a file from resources folder
    public void uploadFile(String fileName, String locator) throws InterruptedException {
        click(locator);

        // Construct path to the image inside resources folder
        String resourcePath = "src/main/resources/images/" + fileName;
        File file = new File(resourcePath);

        if (!file.exists()) {
            throw new RuntimeException("File not found: " + resourcePath);
        }

        // Get absolute path
        String absolutePath = file.getAbsolutePath();

        // Copy path to clipboard
        StringSelection selection = new StringSelection(absolutePath);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);

        try {
            waiting();
            Robot robot = new Robot();

            // Simulate Ctrl + V and Enter
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            waiting();

            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);

            waiting();

        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
    }

    public void selectFromDropdown(){
        WebElement dropdown = driver.findElement(By.xpath("//div[@class='overflow-y-auto max-h-60']"));
        List<WebElement> childDivs = dropdown.findElements(By.xpath("./div"));

        Random random = new Random();
        int randomIndex = random.nextInt(childDivs.size());

        childDivs.get(randomIndex).click();

//        WebElement parentDiv = driver.findElement(By.xpath("//div[@class='overflow-y-auto max-h-60']"));
//        List<WebElement> childDivs = parentDiv.findElements(By.tagName("div"));
//
//        for (WebElement child : childDivs) {
//            System.out.println(child.getText());
//        }
    }

    public WebElement searchElement(int row,int col){
        return driver.findElement(By.xpath("//tr["+row+"]/td["+col+"]"));
    }

    public WebElement clickAction(int row, int order){
        return driver.findElement(By.xpath("//tr["+row+"]/td[last()]//child::button["+order+"]"));
    }

    public String getRandomNameFromTheList(){
        String[] names = {
                    "Sanidu", "Saman", "Sahan", "Thisara", "Themiya",
                    "Sarath", "Sadaru", "kamal"
            };

        Random random = new Random();
        int index = random.nextInt(names.length);
        return names[index];
    }

    public String generateRandomUnitName() {
        int count = PropertyUtils.getIntProperty("Count");
        String randomUnitName = "Unit_" + count++;
        PropertyUtils.setIntProperty("Count", count);
        PropertyUtils.setProperty("Unit_Name", randomUnitName);
        return randomUnitName;
    }

    public String generateRandomUnitShortName() {
        int count = PropertyUtils.getIntProperty("Count");
        String randomUnitShortName = "UT_" + count++;
        PropertyUtils.setProperty("Short_Name", randomUnitShortName);
        return randomUnitShortName;
    }

    public String generateRandomBrandName() {
        int count = PropertyUtils.getIntProperty("Count");
        String randomBrandName = "Brand_" + count++;
        PropertyUtils.setProperty("Brand_Name", randomBrandName);
        return randomBrandName;
    }
}
