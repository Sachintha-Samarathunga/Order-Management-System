package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.time.Duration;
import java.util.Map;
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
        type(businessName, "businessName");
        type(email, "email");
        type(password, "password");
        click("loginBtn");

        ExtentReportManager.testSteps("Successfully logged into the system");
        waiting();
    }

    public void type(String text, String locator) throws InterruptedException {
        By xpath = constructElement(findElementRepo(locator));
        WebElement inputField = driver.findElement(xpath);

        inputField.click();

        String existingValue = inputField.getAttribute("value");
        if (existingValue != null && !existingValue.isEmpty()) {
            inputField.clear();
            inputField.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        }

        inputField.sendKeys(text);
        waiting();
    }

    public void dismissAlert(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.alertIsPresent());

        Alert alert = driver.switchTo().alert();
        alert.dismiss();
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



    // Common method to click an element
    public void click(String locator) throws InterruptedException {
        By xpath = constructElement(findElementRepo(locator));
        WebElement button =  driver.findElement(xpath);
        button.click();
        waiting();
    }

    // to click on any point of the main map
    public void clickOnPointOfMap(int x,int y) throws InterruptedException {
        By xpath = constructElement(findElementRepo("Main_Map"));
        WebElement map = driver.findElement(xpath);
        Actions actions = new Actions(driver);

        actions.moveToElement(map, x, y).click().perform();
        waiting();
    }

    public void zoomInMap() throws InterruptedException {
        By xpath = constructElement(findElementRepo("Main_Map"));
        WebElement map = driver.findElement(xpath);

        Actions actions = new Actions(driver);
        for (int i = 0; i < 3; i++) {  // Adjust zoom level (increase/decrease iterations)
            actions.moveToElement(map).sendKeys(Keys.chord(Keys.CONTROL, Keys.ADD)).perform();
            Thread.sleep(1000);
        }
        waiting();
        waiting();
    }

    public void zoomMap() throws InterruptedException {
        By xpath = constructElement(findElementRepo("Main_Map"));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Loop to simulate smooth zoom with JavaScript wheel events
        for (int i = 0; i < 2; i++) {
            WebElement map = driver.findElement(xpath); // ✅ Use your actual XPath here

            js.executeScript(
                    "arguments[0].dispatchEvent(new WheelEvent('wheel', { deltaY: -20, bubbles: true }));",
                    map
            );

            Thread.sleep(500); // Delay between scrolls for smoothness
        }
    }

    public void dragTheMap(int x, int y) throws InterruptedException {
        By xpath = constructElement(findElementRepo("Main_Map"));
        WebElement map = driver.findElement(xpath);

        Actions actions = new Actions(driver);
        actions.moveToElement(map, x, y).click().perform();
        waiting();
        waiting();
    }


    // Common method to get text from an element
    public String getText(String locator) {
        By xpath = constructElement(findElementRepo(locator));
        return driver.findElement(xpath).getText();
    }

    // Method to wait 2000ms
    public void waiting() throws InterruptedException {
        Thread.sleep(2000);
    }

    // Method for scroll to given element
    public void scrollToElement(String locator) throws InterruptedException {
        By xpath = constructElement(findElementRepo(locator));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(10000));
        WebElement webElement = wait.until(ExpectedConditions.presenceOfElementLocated(xpath));
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", webElement);
        waiting();

    }

    // Method to generate random personal email address
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

    public void MoveMap(String locator) throws InterruptedException {
        By xpath = constructElement(findElementRepo(locator));
        WebElement map = driver.findElement(xpath);

        Actions actions = new Actions(driver);

        actions.clickAndHold(map)
                .moveByOffset(-200, 0) // Move left (adjust offset based on need)
                .moveByOffset(0, -150) // Move up
                .release()
                .perform();


        waiting();

        actions.moveToElement(map).scrollByAmount(0, -500).perform();

        waiting();
    }

    public void dragMap() throws AWTException, InterruptedException {
        Robot robot = new Robot();

        robot.mouseMove(1350,350);
        waiting();
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);

        for (int i = 0; i < 10; i++) {
            robot.mouseMove(1350 - (i * 40), 250 + (i * 20));
            Thread.sleep(50);
        }
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        waiting();
    }

    public void scrollMouse(int x) throws AWTException {
        Robot robot = new Robot();
        robot.mouseWheel(5);
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

    // Helper method to generate a random string
    public String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder result = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            result.append(characters.charAt(index));
        }
        return result.toString();
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

//    // Common method to upload a file
//    public void uploadFile(String filePath, String locator) throws InterruptedException {
//
//        click(locator);
//
//        String data = "C:\\Users\\Sachintha\\Videos\\"+filePath;
//        StringSelection selection = new StringSelection(data);
//
//        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);
//
//        try {
//            waiting();
//            Robot robot = new Robot();
//            robot.keyPress(KeyEvent.VK_CONTROL);
//            robot.keyPress(KeyEvent.VK_V);
//            robot.keyRelease(KeyEvent.VK_V);
//            robot.keyRelease(KeyEvent.VK_CONTROL);
//
//            waiting();
//
//            robot.keyPress(KeyEvent.VK_ENTER);
//            robot.keyRelease(KeyEvent.VK_ENTER);
//
//            waiting();
//
//        } catch (AWTException e) {
//            throw new RuntimeException(e);
//        }
//    }

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


}
