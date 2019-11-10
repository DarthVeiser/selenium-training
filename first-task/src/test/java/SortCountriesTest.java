import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.List;

public class SortCountriesTest {
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
    public void sortCountries() {
        driverChrome.get("http://localhost/litecart/admin/?app=countries&doc=countries");

        String prevCountry = null;

        List<WebElement> rows = driverChrome.findElements(By.cssSelector("table.dataTable tr.row"));
        for (int i = 0; i < rows.size(); i++) {
            String country = rows.get(i).findElements(By.cssSelector("td")).get(4).findElement(By.cssSelector("a")).getText();
            if (prevCountry != null && !(country.compareToIgnoreCase(prevCountry) > 0)) {
                AssertionError assertError = new AssertionError("Страны не отсортированы по алфавиту!");
                System.out.println("Ошибка: " + prevCountry + " " + country + " "+assertError.getMessage());
            }
            prevCountry = country;
            System.out.println(prevCountry);

            String countryZone = rows.get(i).findElements(By.cssSelector("td")).get(5).getText();
            if (!countryZone.equals("0")) {
                rows.get(i).findElements(By.cssSelector("td")).get(4).findElement(By.cssSelector("a")).click();

                String prevZone = null;
                List<WebElement> zones = driverChrome.findElements(By.cssSelector("table#table-zones tr"));
                for (int j = 1; j < zones.size(); j++ ) {
                    String zone = zones.get(j).findElements(By.cssSelector("td")).get(2).getText();
                    if (prevZone != null && !(zone.compareToIgnoreCase(prevZone) > 0)) {
                        AssertionError assertError = new AssertionError("Зоны не отсортированы по алфавиту!");
                        System.out.println("Ошибка:" + prevZone+ " "+ zone+" "+assertError.getMessage());
                    }
                    prevZone = zone;
                    System.out.println(prevZone);
                }

                driverChrome.get("http://localhost/litecart/admin/?app=countries&doc=countries");
                rows = driverChrome.findElements(By.cssSelector("table.dataTable tr.row"));
            }
        }
    }
}
