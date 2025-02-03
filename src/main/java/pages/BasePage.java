package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

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

    public WebElement findElementByText(String classText, String elementText) {
        return driver.findElement(By.xpath(generateXPath(classText, elementText)));
    }

    public WebElement waitForElementVisible(String classText, String elementText) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(generateXPath(classText, elementText))));
    }

    public WebElement waitForElementClickable(String classText, String elementText) {
        return wait.until(ExpectedConditions.elementToBeClickable(By.xpath(generateXPath(classText, elementText))));
    }

    public boolean waitForElementCompare(String elementText) {
        return wait.until(ExpectedConditions.urlContains(elementText));
    }
}