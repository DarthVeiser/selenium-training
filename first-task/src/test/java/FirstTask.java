import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class FirstTask {
    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();

        //open selenium2.ru
        driver.get("https://selenium2.ru/");

        driver.close();
    }
}