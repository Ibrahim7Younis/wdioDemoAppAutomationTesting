package wdioDemoTest;

import org.testng.annotations.Test;
import wdioDemoApp.pages.LoginPage;

public class gesturesTest extends BaseTest{
    LoginPage loginPage;
    @Test
    public void longPressTest(){
        loginPage = mainPage.clickOnLoginPageBtn();
        loginPage.longPressOnSignupBtn();
    }
}
