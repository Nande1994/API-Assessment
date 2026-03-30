package Base;

import BasicPages.LoginPage;
import Utilities.BrowserFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.time.Duration;

public class MyBaseTest {

    BrowserFactory browserFactory = new BrowserFactory();

    public final String url = "https://ndosisimplifiedautomation.vercel.app/";
    public final String browserChoice = "chrome";

    public WebDriver driver;
    public LoginPage loginPage;
    public WebDriverWait wait;

    @BeforeClass
    public void setUp() {
        driver = browserFactory.startBrowser(browserChoice, url);
        loginPage = new LoginPage(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @AfterClass
    public void tearDown() {
        browserFactory.closeBrowser(driver);
    }
}