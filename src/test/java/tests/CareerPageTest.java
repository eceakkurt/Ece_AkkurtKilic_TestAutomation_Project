package tests;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.CareerPageConstants;
import utils.MessageConstants;
import utils.PathConstants;

public class CareerPageTest extends TestBase{

    @Test
    public void testCareerPage() {
        navigateToCareerPage();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(careersPage.isOnCareersPage(CareerPageConstants.CAREERS), MessageConstants.CAREER_PAGE_NOT_OPENED);
        softAssert.assertTrue(isElementVisible(PathConstants.H3, CareerPageConstants.LOCATION), MessageConstants.LOCATION_NOT_VISIBLE);
        softAssert.assertTrue(isElementVisible(PathConstants.H2, CareerPageConstants.LIFE_AT_INSIDER), MessageConstants.LIFE_AT_INSIDER_NOT_VISIBLE);
        softAssert.assertTrue(isElementVisible(PathConstants.A, CareerPageConstants.SEE_ALL_TEAMS), MessageConstants.TEAMS_NOT_VISIBLE);
        softAssert.assertAll();
    }

    private void navigateToCareerPage() {
        homePage.clickCompany();
        homePage.clickCareers();
    }
}
