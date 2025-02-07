package tests;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import utils.constants.CommonConstants;
import utils.enums.ScrollAmount;
import utils.enums.ScrollDirection;

import java.io.File;
import java.io.IOException;

public class TestBase implements ITestListener {
    WebDriver driver;

    @BeforeClass
    @Parameters({"baseURL", "browser", "firefoxBinary"})
    public void setup(String baseURL, String browser, String firefoxBinary) {
        driverSettings(baseURL, browser, firefoxBinary);
        initializePages();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        System.out.println("Test closed with result: " + result.getStatus());
        if (ITestResult.FAILURE == result.getStatus()) {
            takeScreenshot(result.getName());
        }
    }

    @AfterClass
    public void closeBrowser() {
        driver.quit();
    }

    void initializePages() {

    }

    public void waitFor(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void scrollPage(ScrollDirection direction, ScrollAmount amount) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String script = (direction == ScrollDirection.VERTICAL)
                ? "window.scrollBy(0, " + amount.getValue() + ");"
                : "window.scrollBy(" + amount.getValue() + ", 0);";
        js.executeScript(script);
    }

    public void takeScreenshot(String testName) {
        File screenshotDir = new File("src/test/resources/screenshots");
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileHandler.copy(screenshot, new File(screenshotDir + "/" + testName + ".png"));
        } catch (IOException e) {
            System.out.println("Screenshot error: " + e.getMessage());
        }
    }

    private void driverSettings(String baseURL, String browser, String firefoxBinary) {
        driver = createDriver(browser, firefoxBinary);
        driver.manage().window().maximize();
        driver.get(baseURL);
    }

    private WebDriver createDriver(String browser, String firefoxBinary) {
        WebDriver driver;
        if (browser.equalsIgnoreCase(CommonConstants.CHROME)) {
            driver = getChromeDriver();
        } else if (browser.equalsIgnoreCase(CommonConstants.FIREFOX)) {
            driver = getFirefoxDriver(firefoxBinary);
        } else {
            throw new IllegalArgumentException("Invalid browser: " + browser);
        }
        return driver;
    }

    private ChromeDriver getChromeDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        return new ChromeDriver(options);
    }

    private FirefoxDriver getFirefoxDriver(String firefoxBinary) {
        FirefoxOptions options = new FirefoxOptions();
        options.addPreference("dom.webnotifications.enabled", false);
        options.addPreference("network.stricttransportsecurity.preloadlist", false);
        options.addPreference("security.cert_pinning.enforcement_level", 0);
        options.addPreference("dom.security.https_only_mode", false);
        options.addPreference("services.settings.server", "");
        options.setAcceptInsecureCerts(true);
        options.setBinary(firefoxBinary);
        return new FirefoxDriver(options);
    }
}
