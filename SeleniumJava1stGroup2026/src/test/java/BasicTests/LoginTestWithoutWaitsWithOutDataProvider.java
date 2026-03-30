package BasicTests;

import BasicBase.BaseTestWithoutWaits;
import org.testng.annotations.Test;


public class LoginTestWithoutWaitsWithOutDataProvider extends BaseTestWithoutWaits {


    @Test(dataProvider = "testdata")
    public void loginWithValidDetails() throws InterruptedException {

        loginPageWithoutWaits.clickLoginButton();
        Thread.sleep(2000);
        loginPageWithoutWaits.enterEmailAddress("aveetestuser@gmail.com");
        loginPageWithoutWaits.enterPassword("Testing123456!");
        Thread.sleep(2000);
        loginPageWithoutWaits.clickSubmitButton();
        Thread.sleep(5000);
        loginPageWithoutWaits.verifyLoginSuccess("Welcome back, Avela! \uD83D\uDC4B");

    }


}


