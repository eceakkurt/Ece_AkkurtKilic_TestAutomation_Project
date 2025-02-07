package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import utils.constants.CommonConstants;
import utils.constants.HomePageConstants;

public class HomePageTest extends TestBase {

    private HomePage homePage;

    @Override
    void initializePages() {
        homePage = new HomePage(driver);
    }

    @Test
    public void testHomePage() {
        boolean isOnHomePage = homePage.comparePageTitle(CommonConstants.NAME);
        Assert.assertTrue(isOnHomePage, HomePageConstants.ERROR_HOME_PAGE_NOT_OPENED);
    }
}
