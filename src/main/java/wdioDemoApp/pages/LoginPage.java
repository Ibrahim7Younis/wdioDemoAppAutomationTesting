package wdioDemoApp.pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import wdioDemoApp.DataModels.LoginDM;


public class LoginPage extends MainPage {
    public LoginPage(AndroidDriver driver) {
        super(driver);
    }
    private final By loginForm = AppiumBy.accessibilityId("button-login-container");
    private final By signUpForm = AppiumBy.accessibilityId("button-sign-up-container");
    private final By emailField = AppiumBy.accessibilityId("input-email");
    private final By emailErrorMessage = AppiumBy.xpath("//android.widget.TextView[@text=\"Please enter a valid email address\"]");
    private final By passwordField = AppiumBy.accessibilityId("input-password");
    private final By passwordErrorMessage = AppiumBy.xpath("//android.widget.TextView[@text=\"Please enter at least 8 characters\"]");
    private final By confirmPasswordField = AppiumBy.accessibilityId("input-repeat-password");
    private final By confirmPasswordErrorMessage = AppiumBy.xpath("//android.widget.TextView[@text=\"Please enter the same password\"]");
    private final By loginButton = AppiumBy.accessibilityId("button-LOGIN");
    private final By signUpButton = AppiumBy.accessibilityId("button-SIGN UP");
    //used this xpath with the next android popups to make the code work with different android versions
    private final By successSignUpMessage = AppiumBy.xpath("//android.widget.TextView[@text='You successfully signed up!']");
    private final By successLoginMessage = AppiumBy.xpath("//android.widget.TextView[@text='You are logged in!']");





    public void enterEmail(String email) {
        sendText(emailField, email);
    }

    public void enterPassword(String password) {
        sendText(passwordField, password);
    }
    public void enterConfirmPassword(String password) {
        sendText(confirmPasswordField, password);
    }
    public void clickLoginButton() {
        clickElement(loginButton);
    }
    public void clickSignUpButton() {
        clickElement(signUpButton);
    }

    public void clickSignUpForm() {
        clickElement(signUpForm);
    }
    public void clickLoginForm() {
        clickElement(loginForm);
    }
    public boolean isSuccessSignUpMessageDisplayed() {
        return isElementDisplayed(successSignUpMessage);
    }
    public boolean isSuccessLoginMessageDisplayed() {
       return isElementDisplayed(successLoginMessage);
    }
    public boolean isEmailErrorMessageDisplayed() {
       return isElementDisplayed(emailErrorMessage);
    }
    public boolean isPasswordErrorMessageDisplayed() {
       return isElementDisplayed(passwordErrorMessage);
    }
    public boolean isConfirmPasswordErrorMessageDisplayed() {
        return isElementDisplayed(confirmPasswordErrorMessage);
    }
    public void discardMessage(){
        clickElement(discardMessageBtn);
    }

    public boolean invalidEmail(LoginDM loginDM) {
        //get the email from data model
        enterEmail(loginDM.getEmail());
        clickElement(signUpButton);
        //check if the email error message is displayed
        return isEmailErrorMessageDisplayed();
    }
    public boolean invalidPassword(LoginDM loginDM) {
        //get the password from data model
        enterPassword(loginDM.getPassword());
        clickElement(signUpButton);
        //check if the password error message is displayed
        return isPasswordErrorMessageDisplayed();
    }

    public void longPressOnSignupBtn() {
        longPress(signUpForm);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
