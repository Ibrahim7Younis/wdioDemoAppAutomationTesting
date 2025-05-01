package wdioDemoTest;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import wdioDemoApp.pages.SwipePage;

public class SwipeTest extends BaseTest{
    SwipePage swipePage;
    @BeforeClass
    public void navigateToSwipePage() {
         swipePage =mainPage.clickOnSwipePageBtn();
        // Verify that the swipe page is displayed
        Assert.assertTrue(swipePage.isSwipePageDisplayed(), "The Swipe page is not displayed");
    }
    @Test
    public void testSwipeUntilSupportedVideosTextDisplayed() {

        // Swipe until the supported videos text is displayed
        swipePage.swipeUntilSupportedVideosTextDisplayed();
        // Verify that the supported videos text is displayed
        Assert.assertTrue(swipePage.isSupportedVideosDisplayed(), "The supported videos text is not displayed");
    }
}
