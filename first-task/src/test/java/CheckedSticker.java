import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class CheckedSticker {
    private WebDriver driverChrome;

    @Before
    public void initializeDriver(){
        driverChrome = new ChromeDriver();
        driverChrome.get("http://localhost/litecart");
    }

    @After
    public void stopDriver() {driverChrome.quit();}

    @Test
    public void checkSticker() throws InterruptedException {
        driverChrome.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        int products = driverChrome.findElements(By.cssSelector("div#box-campaign-products " +
                "div.product.column.shadow.hover-light")).size();

        int product = 1;
        while (product <= products) {
            Thread.sleep(1000);
            WebElement sticker = driverChrome.findElement(By.cssSelector("div#box-campaign-products " +
                    "div[div.product.column.shadow.hover-light]:nth-child("+ product +")"));
            if (sticker.findElements(By.cssSelector("div[class='sticker']")).size() > 0) {
                AssertionError assertError = new AssertionError();
                System.out.println("FAILED. Found an item without a sticker or with more than one sticker." +assertError.getMessage());
                Assert.fail();
            }

            product++;
        }
        System.out.println("Success!");
    }
}
