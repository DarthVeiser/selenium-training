import net.bytebuddy.utility.RandomString;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;


public class RegistrationTest {
    private WebDriver driverChrome;
    private WebDriverWait wait;

    @Before
    public void initializeDriver(){
        driverChrome = new ChromeDriver();
        driverChrome.get("http://localhost/litecart");
    }

    @After
    public void stopDriver() {driverChrome.quit();}

    @Test
    public void registration() throws InterruptedException {
        RandomString randommail = new RandomString();
        String firstName = "Alex";
        String lastName = "Sandro";
        String address = "RF Novosibirsk Garanina 8";
        String postCode = "65852";
        String city = "New Orleans";
        String country = "United States";
        String email = "" + randommail + ".com";
        String phone = "9612224534";
        String userPass = "12345";

        driverChrome.findElement(By.cssSelector("table a[href*='create_account']")).click();

        Thread.sleep(2000);
        WebElement firstNameInput = driverChrome.findElement(By.name("firstname"));
        firstNameInput.click();
        firstNameInput.clear();
        firstNameInput.sendKeys(firstName);

        WebElement lastNameInput = driverChrome.findElement(By.name("lastname"));
        lastNameInput.click();
        lastNameInput.clear();
        lastNameInput.sendKeys(lastName);

        WebElement adress1Input = driverChrome.findElement( By.name( "address1" ) );
        adress1Input.click();
        adress1Input.clear();
        adress1Input.sendKeys( address );

        WebElement postcodeInput = driverChrome.findElement(By.name("postcode"));
        postcodeInput.click();
        postcodeInput.clear();
        postcodeInput.sendKeys(postCode);

        WebElement cityInput = driverChrome.findElement(By.name("city"));
        cityInput.click();
        cityInput.clear();
        cityInput.sendKeys(city);

        WebElement countrySelect = driverChrome.findElement(By.name("country_code"));
        new Select(countrySelect).selectByVisibleText(country);

        WebElement emailInput = driverChrome.findElement(By.name("email"));
        emailInput.click();
        emailInput.clear();
        emailInput.sendKeys(email);

        WebElement phoneInput = driverChrome.findElement(By.name("phone"));
        phoneInput.click();
        phoneInput.clear();
        phoneInput.sendKeys(phone);

        WebElement desiredPassInput = driverChrome.findElement(By.name("password"));
        desiredPassInput.click();
        desiredPassInput.clear();
        desiredPassInput.sendKeys(userPass);

        WebElement confirmPassInput = driverChrome.findElement(By.name("confirmed_password"));
        confirmPassInput.click();
        confirmPassInput.clear();
        confirmPassInput.sendKeys(userPass);

        WebElement createAccountButton = driverChrome.findElement(By.name("create_account"));
        createAccountButton.click();

        Thread.sleep(5000);
        WebElement logout = driverChrome.findElement( By.cssSelector( "table a[href*='logout']" ) );
        logout.click();


        driverChrome.findElement( By.name( "email" ) ).sendKeys(email);
        driverChrome.findElement( By.name( "password" ) ).sendKeys(userPass);
        driverChrome.findElement( By.name( "login" ) ).click();
        driverChrome.findElement( By.cssSelector( "table a[href*='logout']" ) ).click();
    }
}
