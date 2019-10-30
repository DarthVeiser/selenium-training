import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


import java.util.ArrayList;
import java.util.List;
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
    public void stopDriver() {
        driverChrome.quit();
    }

    @Test
    public void moveOnAdminSections() throws InterruptedException {
        driverChrome.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        List<WebElement> sections = driverChrome.findElements(By.xpath("//ul[@id='box-apps-menu']/li"));
        List<String> liId = new ArrayList<>();

        for (WebElement element: sections) {
            liId.add(element.getAttribute("id"));
        }
        Thread.sleep(1000);
        for ( String id: liId) {
            WebElement section = driverChrome.findElement(By.id(id));
            System.out.println("a1");
            section.click();
            Thread.sleep(1000);

            System.out.println("a2");
            try {
                WebElement main = driverChrome.findElement(By.id("main"));
                System.out.println(main);

                WebElement h1 = main.findElement(By.tagName("h1"));
                System.out.println(h1);
            } catch (NoSuchElementException ex) {
                System.out.println("Элемент <h1> не найден ");
            }

            System.out.println(id);
            System.out.println(driverChrome.findElement(By.id(id)));

            List<WebElement> subSections = driverChrome.findElement(By.id(id)).findElements(By.xpath(".//li"));
            List<String> subLiId = new ArrayList<>();

            for (WebElement subElement: subSections) {
                subLiId.add(subElement.getAttribute("id"));
            }
            for (String idSub: subLiId) {
                WebElement subSection = driverChrome.findElement(By.id(id)).findElement(By.id(idSub));
                subSection.click();
                Thread.sleep(1000);
            }
        }
    }
}
