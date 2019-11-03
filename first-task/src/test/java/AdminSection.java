import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class AdminSection {
    private WebDriver driverChrome;

    @Before
    public void initializeDriver() {
        driverChrome = new ChromeDriver();
        driverChrome.get("http://localhost/litecart/admin/login.php");

        //Enter login and password
        driverChrome.findElement(By.name("username")).sendKeys("admin");
        driverChrome.findElement(By.name("password")).sendKeys("admin");
        driverChrome.findElement(By.xpath("//button[@name='login']")).click();
    }

    @After
    public void stopDriver() {driverChrome.quit();}

    @Test
    public void moveOnAdminSections() throws InterruptedException {
        driverChrome.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        int sections = driverChrome.findElements(By.xpath("//ul[@id='box-apps-menu']/li")).size();
        System.out.println(sections);

        int elementSection = 1;
        while (elementSection <= sections) {
            Thread.sleep(1000);
            driverChrome.findElement(By.cssSelector("ul#box-apps-menu > li:nth-child("+ elementSection +")")).click();

            try {
                WebElement main = driverChrome.findElement(By.id("content"));
                main.findElement(By.tagName("h1"));
            } catch (NoSuchElementException ex) {
                System.out.println("Элемент <h1> не найден ");
            }

            int subMenu = driverChrome.findElements(By.cssSelector("ul#box-apps-menu > li:nth-child("+ elementSection +") li")).size();
            int element = 2;
            while (element <= subMenu) {
                Thread.sleep(1000);
                driverChrome.findElement(By.cssSelector("ul#box-apps-menu > li:nth-child("+ elementSection +") li:nth-child("+ element +")")).click();
                try {
                    WebElement main = driverChrome.findElement(By.id("content"));
                    main.findElement(By.tagName("h1"));
                } catch (NoSuchElementException ex) {
                    System.out.println("Элемент <h1> не найден ");
                }
                element++;
            }
            elementSection++;
        }
    }
}
