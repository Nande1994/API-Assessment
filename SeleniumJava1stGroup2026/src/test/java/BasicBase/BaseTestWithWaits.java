package BasicBase;

import BasicPages.LoginPageWithWaits;
import Utilities.BrowserFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTestWithWaits {

    BrowserFactory browserFactory = new BrowserFactory();
    public final String url = "https://ndosisimplifiedautomation.vercel.app/";
    public final String browserChoice = "chrome";

    public WebDriver driver; // initialized per test
    public LoginPageWithWaits loginPageWithWaits;

    @BeforeMethod
    public void setUp() {
        driver = browserFactory.startBrowser(browserChoice, url);
        loginPageWithWaits  =new LoginPageWithWaits(driver);
    }

    @AfterMethod
   public void tearDown() {

    }


}
