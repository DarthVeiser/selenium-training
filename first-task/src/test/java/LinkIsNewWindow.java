import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Set;

public class LinkIsNewWindow {
    private WebDriver driverChrome;
    private  WebDriverWait wait;
    @Before
    public void initializeDriver() {
        driverChrome = new ChromeDriver();
        wait = new WebDriverWait(driverChrome, 10);
    }

    @After
    public void stopDriver() {driverChrome.quit();}

    @Test
    public void linkInNewWindow(){

        driverChrome.navigate().to( "http://localhost/litecart/admin/?app=countries&doc=countries" );
        driverChrome.findElement( By.name( "username" ) ).sendKeys( "admin" );
        driverChrome.findElement( By.name( "password" ) ).sendKeys( "admin" );
        driverChrome.findElement( By.name( "login" ) ).click();
        wait.until( ExpectedConditions.visibilityOf( driverChrome.findElement( By.xpath( "//div[@id='notices']" ) ) ) );


        driverChrome.findElement(By.cssSelector("[title='Edit']")).click();
        wait.until(ExpectedConditions.visibilityOf(driverChrome.findElement(By.xpath("//h1[contains(text(), " +
                "'Edit Country')]"))));

        String sourceWindow = driverChrome.getWindowHandle();
        System.out.println(sourceWindow);

        List<WebElement> externalLinks = driverChrome.findElements(By.className("fa-external-link"));
        int numberOflinks = externalLinks.size();
        for(int i = 0; i < numberOflinks; i++){
            externalLinks.get(i).click();
            wait.until(ExpectedConditions.numberOfWindowsToBe(2));
            Set<String> externaWindows = driverChrome.getWindowHandles();
            externaWindows.remove(sourceWindow);
            String newWindow = externaWindows.iterator().next();
            driverChrome.switchTo().window(newWindow);
            driverChrome.close();
            driverChrome.switchTo().window(sourceWindow);
        }
    }
}
