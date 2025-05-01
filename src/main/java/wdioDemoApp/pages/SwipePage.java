package wdioDemoApp.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.apache.poi.POIDocument;
import org.openqa.selenium.*;

public class SwipePage extends MainPage{
    public SwipePage(AndroidDriver driver) {
        super(driver);
    }
    private final By swipePageTitle = AppiumBy.xpath("//android.widget.TextView[@text=\"Swipe horizontal\"]");
    private final By supportedVideosText = AppiumBy.xpath("//android.view.ViewGroup[@resource-id=\"__CAROUSEL_ITEM_3_READY__\"]/android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView[@text=\"SUPPORT VIDEOS\"]");

    public boolean isSwipePageDisplayed() {
        return isElementDisplayed(swipePageTitle);
    }
    public boolean isSupportedVideosDisplayed() {
        try{
            WebElement element = driver.findElement(supportedVideosText);
            /* element isDisplayed was returning true although the element before it was displaying in the screen, it was returning
            true because part of it was displayed, so I used the getLocation and getSize methods to check if the
            element is in the middle of the screen
            */
            //
            Point location = element.getLocation();
            Dimension size = element.getSize();
            // Check if the element is in the middle of the screen
            double elementCenterPercent = (location.x + size.width/2.0) / driver.manage().window().getSize().width;
        if(elementCenterPercent > 0.3 && elementCenterPercent < 0.7) {
            System.out.println("SUPPORT VIDEOS text is displayed");
            return true;
        }else {return false;}
        }catch (NoSuchElementException e) {
            return false;
        }
    }

    public void swipeUntilSupportedVideosTextDisplayed() {
    swipeUntilVideoTextDisplayed(10);
    }


    public void swipeUntilVideoTextDisplayed(int maxSwipes) {
        String lastPageSource = "";
        String direction = "left"; // Start by swiping left

        for (int i = 0; i < maxSwipes; i++) {
            if(isSupportedVideosDisplayed()){
                // stop if element found
                break;}
            String currentPageSource = driver.getPageSource();
            // If page didn't change after swipe, switch direction because we reached the end of the page
            if (currentPageSource.equals(lastPageSource)) {
                if (direction.equals("left")) {
                    direction = "right"; // Switch to right
                } else {
                    direction = "left"; // Switch back to left
                }
                System.out.println("swiping To " + direction);
            }

            // Perform the swipe
            if (direction.equals("left")) {
                scrollLeft();
            } else {
                scrollRight();
            }
            lastPageSource = currentPageSource;
        }
    }




}
