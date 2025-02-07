package pages;

import org.openqa.selenium.WebDriver;
import utils.constants.LeverApplicationFormPageConstants;
import utils.constants.PathConstants;

public class LeverApplicationFormPage extends BasePage {

    public LeverApplicationFormPage(WebDriver driver) {
        super(driver);
    }

    public void checkApplyButton() {
        waitForElementVisible(PathConstants.A, LeverApplicationFormPageConstants.APPLY_JOB);
    }
}
