import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginScenario {
    private WebDriver driver;

    @Before
    public void initializeDriver() {
        driver = new ChromeDriver();
    }

    @After
    public void stopDriver() {
        driver.close();
    }

    @Test
    public void loginForAdmin() throws InterruptedException {
        driver.get("http://localhost/litecart/admin/login.php");

        //Enter login and password
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");

        driver.findElement(By.xpath("//button[@name='login']")).click();
        Thread.sleep(3000);
    }
}
