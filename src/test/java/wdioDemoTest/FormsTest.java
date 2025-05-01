package wdioDemoTest;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import wdioDemoApp.constants.GeneralConstants;
import wdioDemoApp.pages.FormsPage;

public class FormsTest extends BaseTest{
    FormsPage formsPage;

    @BeforeClass
    public void navigateToFormsPage() {
        formsPage =mainPage.clickOnFormsPageBtn();
        Assert.assertTrue(formsPage.isFormScreenDisplayed(), "The Forms page is not displayed");
    }

    @Test
    public void testInputField(){
        formsPage.insertInput(testDataConfigsProps.getProperty(GeneralConstants.TEST_STRING));
        // get the displayed text in "you have typed" field
        String result = formsPage.getInputResult();
        // Assert that the displayed text is the same as the input text
        Assert.assertEquals(result, testDataConfigsProps.getProperty(GeneralConstants.TEST_STRING), "The input field is not working as expected");
    }
    @Test()
    public void testActivateSwitch() {
        formsPage.activateSwitch();
        Assert.assertTrue(formsPage.isSwitchBtnActive(), "The switch was not activated");
    }
    // depends on activate switch method to make sure that the switch is activated before deactivating it
    @Test(dependsOnMethods = "testActivateSwitch")
    public void testDeactivateSwitch() {
        formsPage.deactivateSwitch();
        Assert.assertFalse(formsPage.isSwitchBtnActive(), "The switch was not deactivated");
    }
    @Test
    public void testDropDownList() {
        // Select an option and save its text in a variable
        String selectOption = formsPage.selectOptionFromDropDownList((short) 2);
        // get the displayed text in the drop down list after selecting option
        String displayedText = formsPage.getDropDownListDisplayedText();
        // Assert that the displayed text is the same as the selected option
        Assert.assertEquals(selectOption, displayedText, "The selected option is not displayed in the drop down list");
    }
    @Test
    public void testActiveBtn() {
        formsPage.clickOnActiveBtn();
        // Assert that the popup after clicking the active button is displayed
        Assert.assertTrue(formsPage.isActiveBtnPopupDisplayed(), "The active button is not displayed");
        // if the popup is displayed, click on it to close it
        //(if this line of code after assertion reached then the pop up displayed)
        formsPage.discardActiveBtnPopup();
    }

}
