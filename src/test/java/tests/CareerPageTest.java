package tests;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.CareersPage;
import pages.HomePage;
import utils.constants.CareerPageConstants;
import utils.constants.PathConstants;

public class CareerPageTest extends TestBase {

    private CareersPage careersPage;
    private HomePage homePage;

    @Override
    void initializePages() {
        homePage = new HomePage(driver);
        careersPage = new CareersPage(driver);
    }

    @Test
    public void testCareerPage() {
        navigateToCareerPage();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(careersPage.isCorrectPageOpened(CareerPageConstants.CAREERS), CareerPageConstants.ERROR_CAREER_PAGE_NOT_OPENED);
        softAssert.assertTrue(careersPage.isElementVisible(PathConstants.H3, CareerPageConstants.LOCATION), CareerPageConstants.ERROR_LOCATION_NOT_VISIBLE);
        softAssert.assertTrue(careersPage.isElementVisible(PathConstants.H2, CareerPageConstants.LIFE_AT_INSIDER), CareerPageConstants.ERROR_LIFE_AT_INSIDER_NOT_VISIBLE);
        softAssert.assertTrue(careersPage.isElementVisible(PathConstants.A, CareerPageConstants.SEE_ALL_TEAMS), CareerPageConstants.ERROR_TEAMS_NOT_VISIBLE);
        softAssert.assertAll();
    }

    private void navigateToCareerPage() {
        homePage.clickCompany();
        homePage.clickCareers();
    }
}
