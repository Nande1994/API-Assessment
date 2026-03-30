package BasicTests;

import BasicBase.BaseTestWithWaits;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class LoginTestWithwaitsAndDataProvider extends BaseTestWithWaits {


    @Test(dataProvider = "testdata")
    public void loginWithValidDetails(String email,String password) throws InterruptedException {

        loginPageWithWaits.clickLoginButton();
        loginPageWithWaits.enterEmailAddress(email);
        loginPageWithWaits.enterPassword(password);
        loginPageWithWaits.clickSubmitButton();
        loginPageWithWaits.verifyLoginSuccess("Here's an overview of your learning journey");

    }

    @DataProvider(name = "testdata")
    public Object[][] getData() {
        return new Object[][]{
                {"aveetestuser@gmail.com", "Testing123456!"},
        };
    }
}


