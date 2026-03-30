package Homework;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;

public class test {
    WebDriver driver;

    @Test
    public void test() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Admin\\Downloads\\chromedriver_win32 (1)\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://ndosisimplifiedautomation.vercel.app/#overview/");
        driver.manage().window().maximize();


        driver.quit();
    }
}
