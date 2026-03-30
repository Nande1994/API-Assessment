package BasicPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPageWithoutWaits {

    @FindBy(xpath = "//*[@id='app-root']/nav/div[1]/div[3]/button/span[2]")
    WebElement loginButton;

    @FindBy(id = "login-email")
    WebElement loginEmailField;

    @FindBy(id = "login-password")
    WebElement loginPasswordField;

    @FindBy(id = "login-submit")
    WebElement loginSubmitButton;

    @FindBy(xpath = "//h2[contains(text(), 'Welcome back')]")
    WebElement welcomeBackMessage;

    public void clickLoginButton() {
        loginButton.click();
    }

    public void enterEmailAddress(String email) {
        loginEmailField.sendKeys(email);
    }

    public void enterPassword(String password) {
        loginPasswordField.sendKeys(password);
    }

    public void clickSubmitButton() {
        loginSubmitButton.click();
    }

    public void verifyLoginSuccess(String expectedMessage) {
        welcomeBackMessage.isDisplayed();
        String actualMessage = welcomeBackMessage.getText();
        if (!actualMessage.equals(expectedMessage)) {
            throw new AssertionError("Expected message: " + expectedMessage + ", but got: " + actualMessage);
        }
    }
}
