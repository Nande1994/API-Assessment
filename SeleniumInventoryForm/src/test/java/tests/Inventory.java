package tests;
import Base.MyBaseTest;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import BasicPages.LoginPage;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;


public class Inventory extends MyBaseTest {
        WebDriverWait wait;

        @Test
        public void testLoginShowsWelcomeBack() {
            wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            LoginPage login = new LoginPage(driver);
            login.clickLoginButton();
            login.enterEmailAddress("test1@gmail.com");
            login.enterPassword("Mokubu@1306!!");
            login.clickSubmitButton();

            WebElement welcome = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//*[contains(normalize-space(.),'Welcome back')]")
                    )
            );

            Assert.assertTrue(welcome.isDisplayed(),
                    "Welcome back message should be visible after login");



        // NAVIGATE TO INVENTORY FORM
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[@id='app-root']/nav/div[1]/div[2]/div[1]/button/span[3]"))
        ).click();

        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[@id='app-root']/nav/div[1]/div[2]/div[1]/div/button[2]"))
        ).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.id("tab-btn-web"))).click();

        WebElement inventoryTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h3[contains(text(),'Inventory Form')]")
        ));
        Assert.assertTrue(inventoryTitle.isDisplayed(), "Inventory Form should be visible");

        wait.until(ExpectedConditions.elementToBeClickable(By.id("assessment-instructions"))).click();

        // DEVICE TYPE -> BRAND ENABLED

        By brandLocator = By.xpath("//label[contains(normalize-space(.),'Brand')]/following::select[1]");
        WebElement brand = wait.until(ExpectedConditions.presenceOfElementLocated(brandLocator));
        Assert.assertFalse(brand.isEnabled(), "Brand should be disabled before selecting device type");

        new Select(driver.findElement(By.id("deviceType"))).selectByVisibleText("Phone");
        wait.until(d -> d.findElement(brandLocator).isEnabled());
        Assert.assertTrue(driver.findElement(brandLocator).isEnabled(), "Brand should be enabled after selecting Phone");

        // Brand = Apple
        Select brandSelect = new Select(wait.until(ExpectedConditions.elementToBeClickable(By.id("brand"))));
        brandSelect.selectByVisibleText("Apple");
        Assert.assertEquals(brandSelect.getFirstSelectedOption().getText().trim(), "Apple");

        // Storage = 128GB
        driver.findElement(By.id("storage-128GB")).click();
        By unitPrice = By.id("unit-price-label");
        wait.until(ExpectedConditions.textToBePresentInElementLocated(unitPrice, "R480.00"));

        // Color = Blue
        wait.until(d -> d.findElement(By.id("color")).isEnabled());
        Select colorSelect = new Select(wait.until(ExpectedConditions.elementToBeClickable(By.id("color"))));
        colorSelect.selectByVisibleText("Blue");
        Assert.assertEquals(colorSelect.getFirstSelectedOption().getText().trim(), "Blue");

        // Qty = 2 -> subtotal R960
        WebElement qty = wait.until(ExpectedConditions.elementToBeClickable(By.id("quantity")));
        qty.clear();
        qty.sendKeys("2");

        By subtotalLabel = By.id("subtotal-label");
        wait.until(ExpectedConditions.textToBePresentInElementLocated(subtotalLabel, "R960.00"));
        Assert.assertTrue(driver.findElement(subtotalLabel).getText().contains("R960.00"));

        // Address
        WebElement address = wait.until(ExpectedConditions.elementToBeClickable(By.id("address")));
        address.clear();
        address.sendKeys("123 Test Street");
        Assert.assertEquals(address.getAttribute("value"), "123 Test Street");

        // Next -> review step
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Next']"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inventory-review-step")));

        // Step 10: Express Shipping -> R25.00
        // --------------------
        wait.until(ExpectedConditions.elementToBeClickable(By.id("shipping-option-express"))).click();
        By shippingValue = By.id("breakdown-shipping-value");
        String shippingText = wait.until(ExpectedConditions.visibilityOfElementLocated(shippingValue)).getText();
        Assert.assertTrue(shippingText.contains("R25.00"), "Shipping should be R25.00 but was: " + shippingText);

        // Step 11: 1yr Warranty -> R49.00

        wait.until(ExpectedConditions.elementToBeClickable(By.id("warranty-option-1yr"))).click();
        By warrantyValue = By.id("breakdown-warranty-value");
        String warrantyText = wait.until(ExpectedConditions.visibilityOfElementLocated(warrantyValue)).getText();
        Assert.assertTrue(warrantyText.contains("R49.00"), "Warranty should be R49.00 but was: " + warrantyText);

        // Step 12: Apply discount code SAVE10 + assert message
        // --------------------
        WebElement discountInput = wait.until(ExpectedConditions.elementToBeClickable(By.id("discount-code")));
        discountInput.clear();
        discountInput.sendKeys("SAVE10");

        wait.until(ExpectedConditions.elementToBeClickable(By.id("apply-discount-btn"))).click();

        By discountFeedback = By.id("discount-feedback");
        WebElement feedbackEl = wait.until(ExpectedConditions.visibilityOfElementLocated(discountFeedback));
        String feedbackText = feedbackEl.getText().trim();

        Assert.assertTrue(feedbackText.contains("SAVE10"), "Discount feedback should mention SAVE10 but was: " + feedbackText);
        Assert.assertTrue(feedbackText.contains("-10") || feedbackText.contains("10%"),
                "Discount feedback should indicate 10% discount but was: " + feedbackText);


        // Step 12b: Discount calculation assertion (total must reduce)
        // --------------------
        double subtotalValue = parseMoney(driver.findElement(By.id("breakdown-subtotal-value")).getText());
        double shipping = parseMoney(driver.findElement(By.id("breakdown-shipping-value")).getText());
        double warranty = parseMoney(driver.findElement(By.id("breakdown-warranty-value")).getText());
        double total = parseMoney(driver.findElement(By.id("breakdown-total-value")).getText());
        double beforeDiscount = subtotalValue + shipping + warranty;
        double expectedTotal = beforeDiscount * 0.90;
        Assert.assertEquals(total, expectedTotal, 0.01,
                "Total should equal (Subtotal + Shipping + Warranty) * 0.9 after SAVE10");

        System.out.println("Subtotal: " + subtotalValue);
        System.out.println("Shipping: " + shipping);
        System.out.println("Warranty: " + warranty);
        System.out.println("Expected Total: " + expectedTotal);
        System.out.println("Actual Total: " + total);

        // Step 13: Confirm Purchase
        // --------------------
        By confirmBtn = By.xpath("//button[normalize-space()='Confirm Purchase']");
        wait.until(ExpectedConditions.elementToBeClickable(confirmBtn)).click();

        // Wait for toast to appear (validate it) then wait for it to disappear
        By successToast = By.id("purchase-success-toast");
        WebElement toastEl = wait.until(ExpectedConditions.visibilityOfElementLocated(successToast));
        Assert.assertTrue(toastEl.getText().contains("ORDER SUCCESSFUL"), "Toast should confirm order success");

        // Wait for toast/overlay to disappear so it doesn't block clicks
        By toastOverlay = By.cssSelector("#purchase-success-toast, #toast-message, [data-testid*='toast'], [role='alert']");
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(toastOverlay));
        } catch (TimeoutException ignored) {

        }

        // Step 14: Open Invoice History
        // --------------------
        By viewHistoryBtn = By.id("view-history-btn");
        WebElement historyBtnEl = wait.until(ExpectedConditions.presenceOfElementLocated(viewHistoryBtn));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", historyBtnEl);

        try {
            wait.until(ExpectedConditions.elementToBeClickable(viewHistoryBtn)).click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", historyBtnEl);
        }

        By invoicePanel = By.id("invoice-history-panel");
        WebElement panel = wait.until(ExpectedConditions.visibilityOfElementLocated(invoicePanel));
        Assert.assertTrue(panel.isDisplayed(), "Invoice history panel should be visible");

        // Assert invoices exist
        By allViewButtons = By.cssSelector("#invoice-history-panel button[id^='view-invoice-']");
        List<WebElement> viewButtons = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(allViewButtons));
        Assert.assertTrue(viewButtons.size() > 0, "No invoices found in history");
        System.out.println("Invoices found: " + viewButtons.size());

        // Step 15: “Priority” = open MOST RECENT invoice (by date/time on card)

        By invoiceCards = By.cssSelector("#invoice-history-panel [id^='invoice-actions-INV-']");
        List<WebElement> cards = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(invoiceCards));

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd 'at' HH:mm:ss");

        WebElement newestCard = cards.stream()
                .max(Comparator.comparing(card -> {
                    String dtText = card.findElement(By.xpath(".//*[contains(text(),' at ')]")).getText().trim();
                    return LocalDateTime.parse(dtText, dtf);
                }))
                .orElseThrow(() -> new RuntimeException("No invoices found"));

        WebElement newestViewBtn = newestCard.findElement(By.cssSelector("button[id^='view-invoice-']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", newestViewBtn);
        wait.until(ExpectedConditions.elementToBeClickable(newestViewBtn)).click();

        System.out.println("Opened MOST RECENT invoice successfully");
    }

    private double parseMoney(String text) {
        return Double.parseDouble(text.replace("R", "").replace(",", "").trim());

    }
}