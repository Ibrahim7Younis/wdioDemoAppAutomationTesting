package wdioDemoApp.pages;

import io.appium.java_client.AppiumBy;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

public class FormsPage extends MainPage {
    public FormsPage(AndroidDriver driver) {
        super(driver);
    }
    private final By formScreenTitle = AppiumBy.xpath("//android.widget.TextView[@text=\"Form components\"]");
    private final By inputField = AppiumBy.accessibilityId("text-input");
    private final By inputResult = AppiumBy.accessibilityId("input-text-result");
    private final By switchBtn = AppiumBy.accessibilityId("switch");
    private final By dropDownListBtn = AppiumBy.accessibilityId("Dropdown");
    private final By dropDownListDisplayedText =AppiumBy.xpath("//android.widget.EditText[@resource-id=\"text_input\"]");
    private final By activeBtn = AppiumBy.accessibilityId("button-Active");
    private final By activeBtnPopup = AppiumBy.xpath("//android.widget.TextView[@text=\"This button is active\"]");

    public boolean isFormScreenDisplayed() {
        return isElementDisplayed(formScreenTitle);
    }

    public void insertInput(String text) {
        sendText(inputField, text);
    }
    public String getInputResult() {
        return getText(inputResult);
    }

    public void activateSwitch() {
        if(!isSwitchBtnActive()) {
            clickElement(switchBtn);
        }
    }
    public void deactivateSwitch() {
        if(isSwitchBtnActive()) {
            clickElement(switchBtn);
        }
    }
    public boolean isSwitchBtnActive() {
        return driver.findElement(switchBtn).getDomAttribute("checked").equals("true");
    }

    public String selectOptionFromDropDownList(short index) {
        clickElement(dropDownListBtn);
        //Dynamic xpath to select the option from the dropdown list according to the index(starts from 1)
        By option = AppiumBy.xpath("//android.widget.CheckedTextView[@resource-id=\"android:id/text1\"][" + index + "]");
        //Get the text of the selected option to assert that it is actually selected
        String selectedOption =getText(option).trim();
        clickElement(option);
        return selectedOption;
    }
    public String getDropDownListDisplayedText() {
        //Get the text of the displayed option to assert it against the selected
        return getText(dropDownListDisplayedText).trim();
    }

    public void clickOnActiveBtn() {
        clickElement(activeBtn);
    }
    public boolean isActiveBtnPopupDisplayed() {
       return isElementDisplayed(activeBtnPopup);
    }
    public void discardActiveBtnPopup(){
        try {
            clickElement(discardMessageBtn);
        } catch (NoSuchElementException e) {
            System.out.println("The active button popup is not displayed");
        }
    }
}
