package BasicBase;

import BasicPages.LoginPageWithoutWaits;
import Utilities.BrowserFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

public class BaseTestWithoutWaits {

    BrowserFactory browserFactory = new BrowserFactory();
    public final String url = "https://ndosisimplifiedautomation.vercel.app/";
    public final String browserChoice = "chrome";

    public WebDriver driver; // initialized per test
    public LoginPageWithoutWaits loginPageWithoutWaits;

    @BeforeMethod
    public void setUp() {
        driver = browserFactory.startBrowser(browserChoice, url);
        loginPageWithoutWaits = PageFactory.initElements(driver, LoginPageWithoutWaits.class);
    }

    @AfterMethod
    public void tearDown() {
        browserFactory.closeBrowser();
    }

}
