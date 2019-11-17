import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.By.tagName;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class WorkWithBasket {
    private WebDriver driverChrome;
    private WebDriverWait wait;

    public boolean isElementPresent(WebDriver driverChrome, By locator) {
        try {
            driverChrome.findElement(locator);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }


    @Before
    public void initializeDriver() {
        driverChrome = new ChromeDriver();
        wait = new WebDriverWait(driverChrome, 10);
    }

    @After
    public void stopDriver() {driverChrome.quit();}

    @Test
    public void workWithTheBasket() {
        driverChrome.navigate().to("http://localhost/litecart/");

        for (int i = 1; i <= 3; i++) {
            driverChrome.findElement(By.className("product")).click();
            if (isElementPresent(driverChrome, tagName("select"))) {
                Select select = new Select(driverChrome.findElement(tagName("select")));
                select.selectByIndex(1);
            }
            driverChrome.findElement(By.cssSelector("[name='add_cart_product']")).click();
            wait.until(textToBe(By.cssSelector("span.quantity"), String.valueOf(i)));
            driverChrome.navigate().to("http://localhost/litecart/");
        }
        driverChrome.findElement(By.cssSelector("a.link[href='http://localhost/litecart/en/checkout']")).click();
        wait.until(visibilityOfElementLocated(By.cssSelector("#box-checkout-cart > ul > li:nth-child(1) > a > img"))).click();

        Integer row = driverChrome.findElements(By.cssSelector("td.item")).size();
        for (int i = 1; i <= row; i++) {
            WebElement removeButton = driverChrome.findElement(By.cssSelector("[name='remove_cart_item']"));
            removeButton.click();
            wait.until(stalenessOf(removeButton));
        }

        Assert.assertTrue(isElementPresent(driverChrome, tagName("em")));

    }
}
