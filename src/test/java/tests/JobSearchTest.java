package tests;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.LeverApplicationFormPage;
import pages.QAJobsPage;
import utils.constants.PathConstants;
import utils.constants.QAJobsPageConstants;
import utils.enums.ScrollAmount;
import utils.enums.ScrollDirection;

import java.util.List;

public class JobSearchTest extends TestBase {

    private QAJobsPage qaJobsPage;
    private LeverApplicationFormPage leverApplicationFormPage;
    private boolean hasAnyFail = false;

    @Override
    void initializePages() {
        qaJobsPage = new QAJobsPage(driver);
        leverApplicationFormPage = new LeverApplicationFormPage(driver);
    }

    @Test
    public void testJobSearch() {
        SoftAssert softAssert = new SoftAssert();
        testIfPageOpened(softAssert);
        waitFor(10000);
        testIfJobsListed(softAssert);
        waitFor(7000);
        testIfListedJobsAreCorrect(softAssert);
        testIfJobDetailOpened(softAssert);
        softAssert.assertAll();
    }

    private void testIfPageOpened(SoftAssert softAssert) {
        qaJobsPage.acceptCookies();
        qaJobsPage.clickSeeAllQAJobs();
        boolean isElementVisible = qaJobsPage.isElementVisible(PathConstants.SPAN, QAJobsPageConstants.QA);
        if (!isElementVisible) {
            takeScreenshot(getClass().getName());
        }
        softAssert.assertTrue(isElementVisible, QAJobsPageConstants.ERROR_JOB_PAGE_NOT_OPENED);
    }

    private void testIfJobsListed(SoftAssert softAssert) {
        qaJobsPage.filterLocation();
        scrollPage(ScrollDirection.VERTICAL, ScrollAmount.MEDIUM);
        boolean isJobListEmpty = qaJobsPage.jobList().isEmpty();
        if (isJobListEmpty) {
            takeScreenshot(getClass().getName());
        }
        softAssert.assertFalse(isJobListEmpty, QAJobsPageConstants.ERROR_JOB_LIST_EMPTY);
    }

    private void testIfListedJobsAreCorrect(SoftAssert softAssert) {
        hasAnyFail = false;
        List<String> positions = qaJobsPage.getAllPositions();
        List<String> departments = qaJobsPage.getAllDepartments();
        List<String> locations = qaJobsPage.getAllLocations();

        for (String position : positions) {
            boolean isValidPosition = position.contains(QAJobsPageConstants.QA);
            if (!isValidPosition) {
                hasAnyFail = true;
            }
            softAssert.assertTrue(isValidPosition, QAJobsPageConstants.ERROR_POSITIONS);
        }
        for (String department : departments) {
            boolean isValidDepartment = department.contains(QAJobsPageConstants.QA);
            if (!isValidDepartment) {
                hasAnyFail = true;
            }
            softAssert.assertTrue(isValidDepartment, QAJobsPageConstants.ERROR_DEPARTMENTS);
        }
        for (String location : locations) {
            boolean isValidLocation = location.contains(QAJobsPageConstants.CITY);
            if (!isValidLocation) {
                hasAnyFail = true;
            }
            softAssert.assertTrue(isValidLocation, QAJobsPageConstants.ERROR_LOCATIONS);
        }
        if (hasAnyFail) {
            takeScreenshot(getClass().getName());
        }
    }

    private void testIfJobDetailOpened(SoftAssert softAssert) {
        qaJobsPage.clickViewRole();
        leverApplicationFormPage.checkApplyButton();
        boolean isCorrectPageOpened = leverApplicationFormPage.isCorrectPageOpened(QAJobsPageConstants.JOB_DETAIL_PAGE_URL);
        softAssert.assertTrue(isCorrectPageOpened, QAJobsPageConstants.ERROR_LEVER_APP_PAGE_NOT_OPENED);
    }
}
