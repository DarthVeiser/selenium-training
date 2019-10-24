import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class LoginScenario {
    private WebDriver driver;
    private WebDriver driverChrome;

    @Before
    public void initializeDriver() {
        driverChrome = new ChromeDriver();
        driver = new FirefoxDriver();
   }

    @After
    public void stopDriver() {
        driverChrome.quit();
        driver.quit();
    }

    @Test
    public void loginForAdmin() throws InterruptedException {
        driverChrome.get("http://localhost/litecart/admin/login.php");
        driver.get("http://localhost/litecart/admin/login.php");

        //Enter login and password
        driverChrome.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("username")).sendKeys("admin");
        driverChrome.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");

        driverChrome.findElement(By.xpath("//button[@name='login']")).click();
        driver.findElement(By.xpath("//button[@name='login']")).click();
        Thread.sleep(3000);
    }
}
