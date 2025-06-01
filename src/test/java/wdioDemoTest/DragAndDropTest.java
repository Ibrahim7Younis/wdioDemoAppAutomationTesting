package wdioDemoTest;

import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.DeviceRotation;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import wdioDemoApp.pages.DragAndDropPage;

public class DragAndDropTest extends BaseTest{
    private DragAndDropPage dragAndDropPage;
    @BeforeClass
    public void openDragAndDropPage() {
        dragAndDropPage = mainPage.clickOnDragAndDropPageBtn();
        Assert.assertTrue(dragAndDropPage.isPageLoaded(), "Drag and Drop page is not loaded");
    }
    @Test
    public void DragAndDropAllElements(){
        driver.pressKey(new KeyEvent(AndroidKey.BACK));
        driver.pressKey(new KeyEvent(AndroidKey.HOME));
        driver.pressKey(new KeyEvent(AndroidKey.ENTER));

        driver.setClipboardText("Hello");
        String text = driver.getClipboardText();
        dragAndDropPage.dragAndDropAll();
        Assert.assertTrue(dragAndDropPage.isCongratsMessageDisplayed(), "Not all elements were dragged and dropped");
    }
}
