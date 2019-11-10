import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class SortGeozoneTest {
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
    public void sortGeozone() {
        driverChrome.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");

        List<WebElement> rows = driverChrome.findElements(By.cssSelector("table.dataTable tr.row"));
        for (int i = 0; i < rows.size(); i++) {
            rows.get(i).findElements(By.cssSelector("td")).get(2).findElement(By.cssSelector("a")).click();

            String prevZone = null;
            List<WebElement> zones = driverChrome.findElements(By.cssSelector("table#table-zones tr"));
            for (int j = 1; j < zones.size() - 1; j++ ) {
                String zone = zones.get(j).findElements(By.cssSelector("td")).get(2).findElement(By.cssSelector("select option" +
                        "[selected='selected']")).getText();
                if (prevZone != null && !(zone.compareToIgnoreCase(prevZone) > 0)) {
                    AssertionError assertError = new AssertionError("Зоны не отсортированы по алфавиту!");
                    System.out.println("Ошибка:" + prevZone+ " "+ zone+" "+assertError.getMessage());
                }
                prevZone = zone;
                System.out.println(prevZone);
            }

            driverChrome.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
            rows = driverChrome.findElements(By.cssSelector("table.dataTable tr.row"));
            }
        }
    }
