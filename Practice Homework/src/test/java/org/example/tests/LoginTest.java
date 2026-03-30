package org.example.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginTest {
    WebDriver driver ;

    @Test
    public void LoginWithValidDetails() {

        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        // Use headless mode so tests can run in environments without a display
        options.addArguments("--headless=new", "--disable-gpu", "--window-size=1920,1080");
        driver = new ChromeDriver(options);

        // small implicit wait to help with element lookups on slower pages
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://ndosisimplifiedautomation.vercel.app/");
        driver.manage().window().maximize();

        // uses contains() on the id attribute
        boolean isHeroVisible = driver.findElement(By.xpath("//*[contains(@id,'overview-hero')]//h1")).isDisplayed();
        System.out.println("isHeroVisible=" + isHeroVisible);

        boolean isFeatureIconVisible =
                driver.findElement(By.xpath("//*[contains(@id,'overview-section')]//button[contains(.,'Start')]") )
                        .isDisplayed();

        boolean isJobReadyVisible = driver.findElement(
                By.xpath("//div[@class='feature-card']//h3[text()='Job-Ready Skills']")
        ).isDisplayed();
        Assert.assertTrue(isJobReadyVisible, "Job-Ready Skills heading is not visible");

        boolean isInstructorImgVisible = driver.findElement(By.xpath("//*[@id='overview-instructor']//img")).isDisplayed();
        System.out.println("isInstructorImgVisible=" + isInstructorImgVisible);

    }


    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}

