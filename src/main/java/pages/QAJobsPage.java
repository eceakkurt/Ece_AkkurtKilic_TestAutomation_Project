package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.constants.PathConstants;
import utils.constants.QAJobsPageConstants;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class QAJobsPage extends BasePage {

    public QAJobsPage(WebDriver driver) {
        super(driver);
    }

    public void clickSeeAllQAJobs() {
        waitForElementClickable(PathConstants.A, QAJobsPageConstants.SEE_ALL_QA_JOBS).click();
    }

    public void filterLocation() {
        waitForElementClickable(PathConstants.SPAN, PathConstants.ALL).click();
        waitForElementClickable(PathConstants.LI, QAJobsPageConstants.CITY).click();
    }

    public List<WebElement> jobList() {
        return findElementListByText(PathConstants.DIV, QAJobsPageConstants.JOB_LIST);
    }

    public List<String> getAllPositions() {
        List<WebElement> elementList = findElementListByText(PathConstants.P, QAJobsPageConstants.POSITION);
        return mapElementsToText(elementList);
    }

    public List<String> getAllDepartments() {
        List<WebElement> elementList = findElementListByText(PathConstants.SPAN, QAJobsPageConstants.DEPARTMENT);
        return mapElementsToText(elementList);
    }

    public List<String> getAllLocations() {
        List<WebElement> elementList = findElementListByText(PathConstants.DIV, QAJobsPageConstants.LOCATION);
        return mapElementsToText(elementList);
    }

    public void clickViewRole() {
        String originalWindow = driver.getWindowHandle();
        waitForElementClickable(PathConstants.A, QAJobsPageConstants.VIEW_ROLE).click();
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));
        Set<String> windowHandles = driver.getWindowHandles(); // Get all open windows
        for (String window : windowHandles) {
            if (!window.equals(originalWindow)) {
                driver.switchTo().window(window); // Switch to new tab
                break;
            }
        }
    }

    private List<String> mapElementsToText(List<WebElement> elements) {
        return elements
                .stream()
                .map(WebElement::getText)
                .map(String::trim)
                .collect(Collectors.toList());
    }
}
