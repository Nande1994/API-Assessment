package BasicTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class loginToNdosiWebSite {

    WebDriver driver;

    @Test
    public void loginWithValidDetailsb() throws InterruptedException {

        driver= new ChromeDriver();
        driver.get("https://ndosisimplifiedautomation.vercel.app/");
        driver.manage().window().maximize();
        driver.findElement(By.xpath("//*[@id=\"app-root\"]/nav/div[1]/div[3]/button/span[2]")).click();
        driver.findElement(By.id("login-email")).sendKeys("azania@gmail.com");
        driver.findElement(By.id("login-password")).sendKeys("Newlands@12345");
        driver.findElement(By.id("login-submit")).click();

        Thread.sleep(5000);

        driver.findElement(By.xpath("//*[@id=\"app-main-content\"]/section/div[1]/h2")).isDisplayed();

        String myCoursesText = driver.findElement(By.xpath("//*[@id=\"app-main-content\"]/section/div[1]/h2")).getText();
        System.out.println(myCoursesText);
        Assert.assertEquals(myCoursesText,"Welcome back, Boika! \uD83D\uDC4B");
        driver.findElement(By.xpath("//*[@id=\"app-root\"]/nav/div[1]/div[3]/div/button")).isDisplayed();
        driver.findElement(By.xpath("//*[@id=\"app-root\"]/nav/div[1]/div[3]/div/button")).click();

        Thread.sleep(3000);

        driver.findElement(By.xpath("//*[@id=\"app-root\"]/nav/div[1]/div[3]/div/div/button[3]/span[2]")).click();
        Thread.sleep(3000);

        String dashBillText = driver.findElement(By.xpath("/html/body/div/div/div/div/div[1]/div/h1")).getText();
        System.out.println(dashBillText);
        Assert.assertEquals(dashBillText,"\uD83D\uDC68\u200D\uD83C\uDFEB Instructor Dashboard");

        driver.findElement(By.xpath("//*[@id=\"app-root\"]/div/div/div[1]/button")).click();
        driver.findElement(By.xpath("//*[@id=\"app-root\"]/nav/div[1]/div[3]/div/button/span[2]")).click();

        Thread.sleep((3000));
        driver.findElement(By.xpath("//*[@id=\"app-root\"]/nav/div[1]/div[3]/div/div/button[4]/span[2]")).click();

        Thread.sleep(3000);

        String toolAp = driver.findElement(By.xpath("//*[@id=\"app-root\"]/div/div[2]/div[1]/div/h2")).getText();
        System.out.println(toolAp);
        Assert.assertEquals(toolAp, "\uD83D\uDD27 Admin Panel");

        Thread.sleep(3000);

        String adDash = driver.findElement(By.xpath("//*[@id=\"app-root\"]/div/div[3]/div/div[1]/h1")).getText();
        System.out.println(adDash);
        Assert.assertEquals(adDash, "\uD83D\uDD10 Admin Dashboard");

        driver.findElement(By.xpath("//*[@id=\"app-root\"]/div/div[3]/div/div[3]/div/button[1]")).click();
        driver.findElement(By.xpath("//*[@id=\"app-root\"]/div/div[2]/div[2]/button")).click();

        driver.findElement(By.xpath("//*[@id=\"app-root\"]/nav/div[1]/div[3]/div/button")).click();

        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id=\"app-root\"]/nav/div[1]/div[3]/div/div/button[5]")).click();














        ;




    }

}
