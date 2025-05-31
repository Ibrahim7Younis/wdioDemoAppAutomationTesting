package wdioDemoApp.pages;


import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Map;

public class MainPage {
AndroidDriver driver;
    public MainPage(AndroidDriver driver) {
        this.driver = driver;
    }

    private final By loginPageBtn = AppiumBy.accessibilityId("Login");
    private final By formsPageBtn = AppiumBy.accessibilityId("Forms");
    private final By swipePageBtn = AppiumBy.accessibilityId("Swipe");
    private final By dragPageBtn = AppiumBy.accessibilityId("Drag");
    // used xpath with the text to make it work with different android versions
    protected final By discardMessageBtn = AppiumBy.xpath("//android.widget.Button[@text='OK']");

    public LoginPage clickOnLoginPageBtn(){
        clickElement(loginPageBtn);
        return new LoginPage(driver);
    }
    public FormsPage clickOnFormsPageBtn(){
        clickElement(formsPageBtn);
        return new FormsPage(driver);
    }
    public SwipePage clickOnSwipePageBtn(){
        clickElement(swipePageBtn);
        return new SwipePage(driver);
    }
    public DragAndDropPage clickOnDragAndDropPageBtn(){
        clickElement(dragPageBtn);
        return new DragAndDropPage(driver);
    }


    protected void clickElement(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
    }
    protected void sendText(By locator, String text) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.clear();
        element.sendKeys(text);
    }
    protected String getText(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return element.getText();
    }
    protected boolean isElementDisplayed(By locator) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(7));
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void scrollRight() {
        try {
            // Get the screen size
            Dimension size = driver.manage().window().getSize();
            // Calculate the start and end points to perform the touch action between the two
            int startX = (int) (size.width * 0.2);
            int endX = (int) (size.width * 0.8);
            int y = size.height / 2;

            new TouchAction<>((PerformsTouchActions) driver)
                    // Press at the start point
                    .press(PointOption.point(startX, y))
                    // Wait for a short duration
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
                    // Move to the end point
                    .moveTo(PointOption.point(endX, y))
                    // Release the touch
                    .release()
                    // commit
                    .perform();
        } catch (Exception e) {
            System.err.println("Error during right scroll: " + e.getMessage());
        }
    }

    public void scrollLeft() {
        try {
            Dimension size = driver.manage().window().getSize();
            int startX = (int) (size.width * 0.8);
            int endX = (int) (size.width * 0.2);
            int y = size.height / 2;

            new TouchAction<>((PerformsTouchActions)  driver)
                    .press(PointOption.point(startX, y))
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
                    .moveTo(PointOption.point(endX, y))
                    .release()
                    .perform();
        } catch (Exception e) {
            System.err.println("Error during left scroll: " + e.getMessage());
        }
    }

    public void longPress(By locator) {
        RemoteWebElement longPress = (RemoteWebElement) driver.findElement(locator);

        driver.executeScript("gesture: longPress", Map.of("elementId", longPress.getId(), "pressure", 0.5, "duration", 1000));
    }

    public void scrollDownToText(String text) {
        try {
            driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollable(true).scrollIntoView(textContains(\"" + text + "\"))"));
        } catch (TimeoutException e) {
            System.err.println("Text not found: " + text);
        }
    }
    public void dragAndDrop(WebElement source, WebElement target) {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence dragAndDrop = new Sequence(finger, 1);

        // Press on source
        dragAndDrop.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.fromElement(source), 0, 0));
        dragAndDrop.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));

        // Wait and move to target
        dragAndDrop.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.fromElement( target), 0, 0));

        // Release
        dragAndDrop.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(List.of(dragAndDrop));
    }


}
