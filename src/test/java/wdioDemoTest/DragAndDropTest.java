package wdioDemoTest;

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
        dragAndDropPage.dragAndDropAll();
        Assert.assertTrue(dragAndDropPage.isCongratsMessageDisplayed(), "Not all elements were dragged and dropped");
    }
}
