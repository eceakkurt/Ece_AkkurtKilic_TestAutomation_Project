package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.constants.CommonConstants;
import utils.constants.PathConstants;

import java.time.Duration;
import java.util.List;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public String generateXPath(String classText, String elementText) {
        return "//" + classText + "[contains(text(),'" + elementText + "')]";
    }

    public String generateClassXPath(String classText, String elementText) {
        return "//" + classText + "[contains(@class,'" + elementText + "')]";
    }

    public WebElement waitForElementVisible(String classText, String elementText) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(generateXPath(classText, elementText))));
    }

    public WebElement waitForElementClickable(String classText, String elementText) {
        return wait.until(ExpectedConditions.elementToBeClickable(By.xpath(generateXPath(classText, elementText))));
    }

    public List<WebElement> findElementListByText(String classText, String elementText) {
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(generateClassXPath(classText, elementText))));
    }

    public void acceptCookies() {
        waitForElementClickable(PathConstants.A, CommonConstants.ACCEPT).click();
    }

    public boolean isCorrectPageOpened(String text) {
        return driver.getCurrentUrl().contains(text);
    }

    public boolean isElementVisible(String path, String text) {
        return waitForElementVisible(path, text).isDisplayed();
    }
}