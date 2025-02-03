package pages;

import org.openqa.selenium.WebDriver;
import utils.HomePageConstants;
import utils.PathConstants;

public class HomePage extends BasePage {
    public HomePage(WebDriver driver) {
        super(driver);
    }

    public boolean comparePageTitle(String expectedTitle) {
        String actualTitle = getPageTitle();
        return actualTitle.contains(expectedTitle);
    }

    public void clickCareers() {
        waitForElementClickable(PathConstants.A, HomePageConstants.CAREERS).click();
    }

    public void clickCompany() {
        waitForElementClickable(PathConstants.A, HomePageConstants.COMPANY).click();
    }
}
