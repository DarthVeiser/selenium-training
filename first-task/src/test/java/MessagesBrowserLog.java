import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class MessagesBrowserLog {
    private WebDriver driverChrome;
    private WebDriverWait wait;

    @Before
    public void initializeDriver() {
        driverChrome = new ChromeDriver();
        driverChrome.get("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");
        wait = new WebDriverWait(driverChrome, 10);

        //Enter login and password
        driverChrome.findElement(By.name("username")).sendKeys("admin");
        driverChrome.findElement(By.name("password")).sendKeys("admin");
        driverChrome.findElement(By.xpath("//button[@name='login']")).click();
    }

    @After
    public void stopDriver() {driverChrome.quit();}

    @Test
    public void messagesBrowserLog() {
        wait.until( ExpectedConditions.visibilityOf( driverChrome.findElement(By.xpath("//div[@id='notices']"))));

        List<WebElement> products = driverChrome.findElements(By.cssSelector("a[href*='product_id']"));
        List<String> productNames = new ArrayList<>();
        for (int i = 0; i < products.size(); i += 2) {
            productNames.add(products.get(i).getText());
        }

        for (String productName : productNames) {
            driverChrome.findElement(By.linkText(productName)).click();
            driverChrome.navigate().back();
        }

        driverChrome.manage().logs().get("browser").forEach(l -> {
            System.out.println(l);
            Assert.assertFalse(l.equals(""));
        });
    }
}
