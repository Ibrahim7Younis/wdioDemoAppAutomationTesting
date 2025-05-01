package wdioDemoTest;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import wdioDemoApp.DataModels.LoginDM;
import wdioDemoApp.ExcelIndices.LoginIndices;
import wdioDemoApp.constants.GeneralConstants;
import wdioDemoApp.pages.LoginPage;
import wdioDemoApp.pages.MainPage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoginTest extends BaseTest {
    LoginPage loginPage;
    @BeforeClass
    public void navigateToSignUpPage() {
         loginPage = mainPage.clickOnLoginPageBtn();
        loginPage.clickSignUpForm();
    }

    @Test(description = "tests email format ", dataProvider = "loginDP")
    public void testInvalidEmail(LoginDM loginDM) {

        // Enter the email
        boolean errorMessage = loginPage.invalidEmail(loginDM);
        // Assert the result of the Email validation
        Assert.assertTrue(errorMessage, "Email validation failed for: " + loginDM.getEmail());
    }
    @Test( description = "tests password format ", dataProvider = "loginDP")
    public void testInvalidPassword(LoginDM loginDM) {

        // Enter the password
        boolean errorMessage = loginPage.invalidPassword(loginDM);
        // Assert the result of the Email validation
        Assert.assertTrue(errorMessage, "Email validation failed for: " + loginDM.getEmail());
    }
    @Test(description = "test different password in the confirmation password field")
    public void testDifferentConfirmationPassword() {


        loginPage.enterEmail(testDataConfigsProps.getProperty(GeneralConstants.VALID_EMAIL));
        loginPage.enterPassword(testDataConfigsProps.getProperty(GeneralConstants.VALID_PASSWORD));
        loginPage.enterConfirmPassword(testDataConfigsProps.getProperty(GeneralConstants.INVALID_PASSWORD));
        loginPage.clickSignUpButton();
        // Assert the result of the password confirmation validation
        boolean errorMessage = loginPage.isConfirmPasswordErrorMessageDisplayed();
        Assert.assertTrue(errorMessage, "Password validation failed");
    }

    @Test(priority = 1)
    public void testSuccessSignUp() {

        loginPage.enterEmail(testDataConfigsProps.getProperty(GeneralConstants.VALID_EMAIL));
        loginPage.enterPassword(testDataConfigsProps.getProperty(GeneralConstants.VALID_PASSWORD));
        loginPage.enterConfirmPassword(testDataConfigsProps.getProperty(GeneralConstants.VALID_PASSWORD));
        loginPage.clickSignUpButton();

        Assert.assertTrue(loginPage.isSuccessSignUpMessageDisplayed());
        loginPage.discardMessage();
    }



    @Test(priority = 2)
    public void validLogin(){

        loginPage.clickLoginForm();
        loginPage.enterEmail(testDataConfigsProps.getProperty(GeneralConstants.VALID_EMAIL));
        loginPage.enterPassword(testDataConfigsProps.getProperty(GeneralConstants.VALID_PASSWORD));
        loginPage.clickLoginButton();
        // Assert success login
        Assert.assertTrue(loginPage.isSuccessLoginMessageDisplayed());
        loginPage.discardMessage();
    }



    @DataProvider(name = "loginDP")
    public Object[][] loginDP() {
        Object[][] DP = new Object[testData.size()][1];
        for (int i = 0; i < testData.size(); i++)
            DP[i][0] = testData.get(i);
        return DP;
    }
    @BeforeClass
    public List<LoginDM> loginTestDriven() throws IOException {
        Object[][] object = getSheetData("src/test/resources/testDataFiles/DDTestCases.xlsx", "Login");


        for (int i = 0; i < object.length; i++) {
            LoginDM loginDM = new LoginDM();
            loginDM.setEmail(object[i][LoginIndices.EMAIL].toString());
            loginDM.setPassword(object[i][LoginIndices.PASSWORD].toString());
            testData.add(loginDM);
        }
        return testData;
    }
    List<LoginDM> testData = new ArrayList<>();
}
