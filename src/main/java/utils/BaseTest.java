package utils;

import com.formdev.flatlaf.FlatLightLaf;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


public class BaseTest {
    protected static String browser = null;
    protected WebDriver driver;
    protected String baseUrl;
    protected webSteps webSteps;

    @BeforeSuite
    public void setupReport() {
        ExtentReportManager.initReport();
    }

    @AfterMethod
    public void tearDownBrowser(ITestResult result) {
        configureTestReport(result);
    }

    @AfterSuite
    public void finalizeReport() {
        ExtentReportManager.flushReport();
        ExtentReportManager.openReport();
    }

    public void setUpBrowser() {
        if (browser == null) {
            browser = getUserBrowserInput();
        }

        switch (browser.toLowerCase()) {
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            case "edge":
                driver = new EdgeDriver();
                break;
            default:
                throw new RuntimeException("Browser is not supported: " + browser);
        }
    }



    public void loadUrl() throws InterruptedException, IOException {
        Properties properties = new Properties();
        FileInputStream fis = new FileInputStream("src/main/resources/config.properties");
        properties.load(fis);
        baseUrl = properties.getProperty("baseUrl");

        setUpBrowser();
        webSteps = new webSteps(driver);

        driver.manage().window().maximize();
        driver.get(baseUrl);
        webSteps.waiting();
    }

    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    private String getUserBrowserInput() {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }

        Object[] options = {"Chrome", "Firefox", "Edge"};

        int choice = JOptionPane.showOptionDialog(null,
                "Select a browser:",
                "Browser Selection",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]); // Default to Chrome

        if (choice == -1) {
            throw new RuntimeException("No browser selected. Test aborted!");
        }

        return options[choice].toString().toLowerCase();
    }


    // common method for extent report configuration
    protected void configureTestReport(@NotNull ITestResult result){
        if (result.getStatus() == ITestResult.FAILURE) {
            ExtentReportManager
                    .logFail("❌ <b><font color='red'> FAILED : </font></b>" + result.getThrowable().getMessage());
        } else {
            ExtentReportManager.logPass("✅ <b><font color='green'> PASSED </font></b>");
        }

        ExtentReportManager.captureScreenshot(driver, result);
        tearDown();
    }
}
