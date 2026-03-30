package BasicTests;

import BasicBase.BaseTestWithWaits;
import Utilities.ReadXSLdata;
import org.testng.annotations.Test;


public class LoginTestReadXSLData extends BaseTestWithWaits {


    @Test(dataProviderClass = ReadXSLdata.class, dataProvider = "testData")
    public void loginWithValidDetails(String email, String password) {

        loginPageWithWaits.clickLoginButton();
        loginPageWithWaits.enterEmailAddress(email);
        loginPageWithWaits.enterPassword(password);
        loginPageWithWaits.clickSubmitButton();
        loginPageWithWaits.verifyLoginSuccess("Here's an overview of your learning journey");

    }

}


