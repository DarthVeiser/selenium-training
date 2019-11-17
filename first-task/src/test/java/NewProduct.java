import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.Calendar;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class NewProduct {
    private WebDriver driverChrome;
    String enName, enProdName, validFrom, validTo, prefix;

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
    public void NewProduct() throws InterruptedException {
        driverChrome.navigate().to("http://localhost/litecart/admin/");

        Calendar calendar = Calendar.getInstance();
        int h = calendar.get(calendar.HOUR_OF_DAY);
        int m = calendar.get(calendar.MINUTE);
        int s = calendar.get(calendar.SECOND);

        enName = "more Symbiote";
        prefix = Integer.toString(h) + Integer.toString(m) + Integer.toString(s);
        enProdName = enName + " " + prefix;


        int y = calendar.get(calendar.YEAR);
        int month = calendar.get(calendar.MONTH);
        int d = calendar.get(calendar.DAY_OF_MONTH);

        validFrom = Integer.toString(y);
        validTo = Integer.toString(y+2);

        if(month < 10) {
            validFrom = validFrom+"-0"+Integer.toString(month);
            validTo = validTo+"-0"+Integer.toString(month);
        } else {
            validFrom = validFrom+"-"+Integer.toString(month);
            validTo = validTo+"-"+Integer.toString(month);
        }

        if(d < 10) {
            validFrom = validFrom+"-0"+Integer.toString(d);
            validTo = validTo+"-0"+Integer.toString(d);
        } else {
            validFrom = validFrom+"-"+Integer.toString(d);
            validTo = validTo+"-"+Integer.toString(d);
        }


        driverChrome.findElement(By.cssSelector("[href*=catalog]")).click();
        // открыть каталог

        driverChrome.findElement(By.linkText("Add New Product")).click();
        // открываем форму регистрации нового продукта


        // устанавливаем статус Enabled
        driverChrome.findElement(By.name("status")).click();
        Thread.sleep(2000);

        // вводим название товара en
        driverChrome.findElement(By.name("name[en]")).clear();
        driverChrome.findElement(By.name("name[en]")).sendKeys(enProdName);

        // вводим код товара
        driverChrome.findElement(By.name("code")).sendKeys(prefix + Keys.TAB);

        // устанавливаем категорию Rubber Ducks
        driverChrome.findElement(By.xpath("(//input[@name='categories[]'])[2]")).click();

        // Устанавливаем группу Unisex
        driverChrome.findElement(By.xpath("(//input[@name='product_groups[]'])[3]")).click();

        // устанавливаем количество 1
        driverChrome.findElement(By.name("quantity")).clear();
        driverChrome.findElement(By.name("quantity")).sendKeys("11");

        // устанавливаем дату начала годности
        driverChrome.findElement(By.name("date_valid_from")).sendKeys(validFrom);

        // устанавливаем дату конца годности
        driverChrome.findElement(By.name("date_valid_to")).sendKeys(validTo);

        // Прикладываем картинку
        File file = new File("src/test/java/other/venom.jpg");
        driverChrome.findElement(By.name("new_images[]")).sendKeys(file.getAbsolutePath());
        Thread.sleep(1000);

        // переходим на вкладку Information
        driverChrome.findElement(By.linkText("Information")).click();


        // выбираем корпорацию
        new Select(driverChrome.findElement(By.name("manufacturer_id"))).selectByVisibleText("ACME Corp.");

        // Ввводим ключевое слово
        driverChrome.findElement(By.name("keywords")).sendKeys(enProdName);

        // задаем краткое описание
        driverChrome.findElement(By.name("short_description[en]")).sendKeys(enProdName);

        // задаем описание
        driverChrome.findElement(By.name("description[en]")).sendKeys(enProdName);

        // задаем заголовок
        driverChrome.findElement(By.name("head_title[en]")).sendKeys(enProdName);

        // задаем метаописание
        driverChrome.findElement(By.name("meta_description[en]")).sendKeys(enProdName);


        // переходим на вкладку Prices
        driverChrome.findElement(By.linkText("Prices")).click();


        // задаем цену
        driverChrome.findElement(By.name("purchase_price")).clear();
        driverChrome.findElement(By.name("purchase_price")).sendKeys("5");

        // выбираем валюту
        new Select(driverChrome.findElement(By.name("purchase_price_currency_code"))).selectByVisibleText("Euros");

        // задаем цену в долларах
        driverChrome.findElement(By.name("gross_prices[USD]")).clear();
        driverChrome.findElement(By.name("gross_prices[USD]")).sendKeys("10");

        // задаем цену в EURO
        driverChrome.findElement(By.name("gross_prices[EUR]")).clear();
        driverChrome.findElement(By.name("gross_prices[EUR]")).sendKeys("5");

        // сохраняем продукт
        driverChrome.findElement(By.name("save")).click();


        Thread.sleep(5000);


        // Проверяем наличие такого элемента на странице
        driverChrome.findElement(By.linkText(enProdName));
    }
}
