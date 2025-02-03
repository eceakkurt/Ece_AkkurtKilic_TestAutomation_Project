package tests;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.CommonConstants;
import utils.MessageConstants;

public class HomePageTest extends TestBase {

    @Test
    public void testHomePage() {
        boolean isOnHomePage = homePage.comparePageTitle(CommonConstants.NAME);
        Assert.assertTrue(isOnHomePage, MessageConstants.HOME_PAGE_NOT_OPENED);
    }
}
