package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import pages.CareersPage;
import pages.HomePage;

public class TestBase {
    WebDriver driver;
    HomePage homePage;
    CareersPage careersPage;

    @BeforeClass
    @Parameters("baseURL")
    public void setup(String baseURL) {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(baseURL);
        homePage = new HomePage(driver);
        careersPage = new CareersPage(driver);
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    public boolean isElementVisible(String path, String text) {
        return careersPage.waitForElementVisible(path, text).isDisplayed();
    }
}
