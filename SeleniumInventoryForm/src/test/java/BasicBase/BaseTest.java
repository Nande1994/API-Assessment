package BasicBase;
import BasicPages.LoginPage;
import Utilities.BrowserFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
public class BaseTest {

    BrowserFactory browserFactory = new BrowserFactory();
    public final String url = "https://ndosisimplifiedautomation.vercel.app/";
    public final String browserChoice = "chrome";

    public WebDriver driver; // initialized per test
    public LoginPage loginPage;

    @BeforeMethod
    public void setUp() {
        driver = browserFactory.startBrowser(browserChoice, url);
        loginPage = new LoginPage(driver);
    }

    @AfterMethod
    public void tearDown() {
    }
}

