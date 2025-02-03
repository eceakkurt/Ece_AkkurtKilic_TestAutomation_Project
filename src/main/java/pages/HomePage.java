package pages;

import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {
    public HomePage(WebDriver driver) {
        super(driver);
    }

    public boolean comparePageTitle(String expectedTitle) {
        String actualTitle = getPageTitle();
        return actualTitle.contains(expectedTitle);
    }
}
