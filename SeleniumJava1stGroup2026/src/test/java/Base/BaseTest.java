package Base;

import BasicPages.LoginPageWithWaits;
import Utilities.BrowserFactory;
import Utilities.Screenshots;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest {

    BrowserFactory browserFactory = new BrowserFactory();
    public final String url = "https://ndosisimplifiedautomation.vercel.app/";
    public final String browserChoice = "chrome";

    public WebDriver driver; // initialized per test
    public LoginPageWithWaits loginPageWithWaits;
    public Screenshots screenshots;

    @BeforeClass
    public void setUp() {
        driver = browserFactory.startBrowser(browserChoice, url);
        loginPageWithWaits  =new LoginPageWithWaits(driver);
        screenshots = new Screenshots();
    }

    @AfterClass
    public void tearDown() {
        browserFactory.closeBrowser();
    }

}
