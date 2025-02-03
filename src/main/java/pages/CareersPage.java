package pages;

import org.openqa.selenium.WebDriver;
import utils.CareerPageConstants;
import utils.PathConstants;

public class CareersPage extends BasePage {

    public CareersPage(WebDriver driver) {
        super(driver);
    }

    public String getCurrentUrl() {
        return waitForElementCompare(CareerPageConstants.CAREERS) ? driver.getCurrentUrl() : "";
    }

    public boolean isOnCareersPage(String text) {
        String currentUrl = getCurrentUrl();
        return !currentUrl.isEmpty() && currentUrl.contains(text);
    }

    public void clickQATeam() {
        waitForElementClickable(PathConstants.H3, CareerPageConstants.QUALITY_ASSURANCE).click();
    }

    public void clickSeeAllTeams() {
        waitForElementClickable(PathConstants.A, CareerPageConstants.SEE_ALL_TEAMS).click();
    }
}
