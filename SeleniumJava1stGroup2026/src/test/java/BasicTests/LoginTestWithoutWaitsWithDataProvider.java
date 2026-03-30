package BasicTests;

import BasicBase.BaseTestWithoutWaits;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class LoginTestWithoutWaitsWithDataProvider extends BaseTestWithoutWaits {


    @Test(dataProvider = "testdata")
    public void loginWithValidDetails(String email,String password) throws InterruptedException {

        loginPageWithoutWaits.clickLoginButton();
        Thread.sleep(2000);
        loginPageWithoutWaits.enterEmailAddress(email);
        loginPageWithoutWaits.enterPassword(password);
        Thread.sleep(2000);
        loginPageWithoutWaits.clickSubmitButton();
        Thread.sleep(5000);
        loginPageWithoutWaits.verifyLoginSuccess("Welcome back, Avela! \uD83D\uDC4B");

    }

    @DataProvider(name = "testdata")
    public Object[][] getData() {
        return new Object[][]{
                {"aveetestuser@gmail.com", "Testing123456!"},
                {"Funda@gmail.com", "Funda@2026"},
                {"Kingmolebatsi2@gmail.com", "!2345678"},
                {"tumi@dima.com", "123@4567"}
        };
    }
}


