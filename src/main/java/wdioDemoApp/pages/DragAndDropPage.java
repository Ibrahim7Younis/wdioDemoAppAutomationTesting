package wdioDemoApp.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class DragAndDropPage extends MainPage{
    public DragAndDropPage(AndroidDriver driver) {
        super(driver);
    }
    private By dragAndDropScreen = AppiumBy.accessibilityId("Drag-drop-screen");
    private By dropL1 = AppiumBy.accessibilityId("drop-l1");
    private By dropC1 = AppiumBy.accessibilityId("drop-c1");
    private By dropR1 = AppiumBy.accessibilityId("drop-r1");
    private By dropL2 = AppiumBy.accessibilityId("drop-l2");
    private By dropC2 = AppiumBy.accessibilityId("drop-c2");
    private By dropR2 = AppiumBy.accessibilityId("drop-r2");
    private By dropL3 = AppiumBy.accessibilityId("drop-l3");
    private By dropC3 = AppiumBy.accessibilityId("drop-c3");
    private By dropR3 = AppiumBy.accessibilityId("drop-r3");

    private By dragL1 = AppiumBy.accessibilityId("drag-l1");
    private By dragC1 = AppiumBy.accessibilityId("drag-c1");
    private By dragR1 = AppiumBy.accessibilityId("drag-r1");
    private By dragL2 = AppiumBy.accessibilityId("drag-l2");
    private By dragC2 = AppiumBy.accessibilityId("drag-c2");
    private By dragR2 = AppiumBy.accessibilityId("drag-r2");
    private By dragL3 = AppiumBy.accessibilityId("drag-l3");
    private By dragC3 = AppiumBy.accessibilityId("drag-c3");
    private By dragR3 = AppiumBy.accessibilityId("drag-r3");
    private By congratsMessage = AppiumBy.xpath("//android.widget.TextView[@text=\"Congratulations\"]");


    public boolean isPageLoaded() {
        return isElementDisplayed(dragAndDropScreen);
    }
    public boolean isCongratsMessageDisplayed(){
        return isElementDisplayed(congratsMessage);
    }
    public void dragAndDropL1() {
        dragAndDrop(driver.findElement(dragL1), driver.findElement(dropL1));
    }
    public void dragAndDropC1() {
        dragAndDrop(driver.findElement(dragC1), driver.findElement(dropC1));
    }
    public void dragAndDropR1() {
        dragAndDrop(driver.findElement(dragR1), driver.findElement(dropR1));
    }
    public void dragAndDropL2() {
        dragAndDrop(driver.findElement(dragL2), driver.findElement(dropL2));
    }
    public void dragAndDropC2() {
        dragAndDrop(driver.findElement(dragC2), driver.findElement(dropC2));
    }
    public void dragAndDropR2() {
        dragAndDrop(driver.findElement(dragR2), driver.findElement(dropR2));
    }
    public void dragAndDropL3() {
        dragAndDrop(driver.findElement(dragL3), driver.findElement(dropL3));
    }
    public void dragAndDropC3() {
        dragAndDrop(driver.findElement(dragC3), driver.findElement(dropC3));
    }
    public void dragAndDropR3() {
        dragAndDrop(driver.findElement(dragR3), driver.findElement(dropR3));
    }
    public void dragAndDropAll(){
        dragAndDropL1();
        dragAndDropC1();
        dragAndDropR1();
        dragAndDropL2();
        dragAndDropC2();
        dragAndDropR2();
        dragAndDropL3();
        dragAndDropC3();
        dragAndDropR3();
    }
    

}
