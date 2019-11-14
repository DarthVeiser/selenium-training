import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;


import java.util.Arrays;

public class RightPageTestFirefox {
    private WebDriver driver;
    @Before
    public void initializeDriver(){
        driver = new FirefoxDriver();
        driver.get("http://localhost/litecart");
    }

    @After
    public void stopDriver() {
        driver.quit();}
    
    @Test
    public void rightRage(){
        driver.navigate().to("http://localhost/litecart/en/");
        System.out.println("Проверяем следующую страницу: " + driver.getCurrentUrl());

        WebElement firstItem = driver.findElement(By.xpath(".//*[@id='box-campaigns']//ul/li[1]"));

        WebElement itemNameMain = firstItem.findElement(By.cssSelector("div.name"));
        String itemName = itemNameMain.getAttribute("textContent");

        //Проверяем на главной странице
        WebElement itemOldPriceMain = firstItem.findElement(By.cssSelector("s"));
        String itemOldPrice = itemOldPriceMain.getAttribute("textContent");
        String itemOldPriceClass = itemOldPriceMain.getAttribute("class");
        String itemOldPriceRGB = itemOldPriceMain.getCssValue("color");
        String[] itemOldPriceColor = itemOldPriceRGB.replaceAll("[rgb(\\)]", "").split(",");
        System.out.println(Arrays.toString(itemOldPriceColor));

        int red = Integer.parseInt(itemOldPriceColor[0].trim());
        int green = Integer.parseInt(itemOldPriceColor[1].trim());
        int blue = Integer.parseInt(itemOldPriceColor[2].trim());

        if ((red == green) && (red == blue)) {
            System.out.println("Цвет обычной цены на главной странице ("+ itemOldPriceRGB +") серый" );
        } else {
            AssertionError assertError = new AssertionError();
            System.out.println("Ошибка: " + assertError.getMessage());
            System.out.println("Цвет обычной цены на главной странице ("+ itemOldPriceRGB +") НЕ серый");
            Assert.fail();
        }
        Dimension sizeItemOldPriceMain = itemOldPriceMain.getSize();
        System.out.println("Размер обычной цены: " + sizeItemOldPriceMain);

        WebElement itemNewPriceMain = firstItem.findElement(By.cssSelector("strong"));
        String itemNewPrice = itemNewPriceMain.getAttribute("textContent");
        String itemNewPriceClass = itemNewPriceMain.getAttribute("class");
        String itemNewPriceRGB = itemNewPriceMain.getCssValue("color");
        String[] itemNewPriceColor = itemNewPriceRGB.replaceAll("[rgb(\\)]", "").split(",");
        System.out.println(Arrays.toString(itemNewPriceColor));
        int greenNew = Integer.parseInt(itemNewPriceColor[1].trim());
        int blueNew = Integer.parseInt(itemNewPriceColor[2].trim());
        if ((greenNew == 0) && (blueNew == 0)) {
            System.out.println("Цвет акционной цены на главной странице ("+ itemNewPriceRGB +") красный" );
        } else {
            AssertionError assertError = new AssertionError();
            System.out.println("Ошибка: " + assertError.getMessage());
            System.out.println("Цвет акционной цены на главной странице ("+ itemNewPriceRGB +") НЕ красный");
            Assert.fail();
        }
        Dimension sizeItemNewPriceMain = itemNewPriceMain.getSize();
        System.out.println("Размер акционной цены: " + sizeItemNewPriceMain);
        //Сравниваем размеры элементов
        if ( (sizeItemNewPriceMain.getHeight() < sizeItemOldPriceMain.getHeight()) &&
                (sizeItemNewPriceMain.getWidth() < sizeItemOldPriceMain.getWidth())) {
            AssertionError assertError = new AssertionError();
            System.out.println("Ошибка: " + assertError.getMessage());
            System.out.println("Обычная цена крупнее, чем акционная");
            Assert.fail();
        } else {
            System.out.println("Акционная цена крупнее, чем обычная");
        }

        firstItem.click();
        System.out.println("Проверяем следующую страницу: " + driver.getCurrentUrl());
        System.out.println("Заголовок страницы: " + driver.getTitle());

        //Проверяем на странице продукта
        WebElement itemNameOpenedWindow = driver.findElement(By.cssSelector("div#box-product h1"));
        String itemNameOpened = itemNameOpenedWindow.getAttribute("textContent");

        WebElement contentOpenedW = driver.findElement(By.cssSelector("div#box-product"));

        WebElement itemOldPriceOpenedW = contentOpenedW.findElement(By.cssSelector("s"));
        String itemOldPriceOpened = itemOldPriceOpenedW.getAttribute("textContent");
        String itemOldPriceClassOpened = itemOldPriceOpenedW.getAttribute("class");
        String itemOldPriceOpenedRGB = itemOldPriceOpenedW.getCssValue("color");
        String[] itemOldPriceOpenedColor = itemOldPriceOpenedRGB.replaceAll("[rgb(\\)]", "").split(",");
        System.out.println(Arrays.toString(itemOldPriceOpenedColor));

        int redOpened = Integer.parseInt(itemOldPriceColor[0].trim());
        int greenOpened = Integer.parseInt(itemOldPriceColor[1].trim());
        int blueOpened = Integer.parseInt(itemOldPriceColor[2].trim());

        if ((redOpened == greenOpened) && (redOpened == blueOpened)) {
            System.out.println("Цвет обычной цены на странице продукта ("+ itemOldPriceOpenedRGB +") серый" );
        } else {
            AssertionError assertError = new AssertionError();
            System.out.println("Ошибка: " + assertError.getMessage());
            System.out.println("Цвет обычной цены на странице продукта ("+ itemOldPriceOpenedRGB +") НЕ серый");
            Assert.fail();
        }
        Dimension sizeItemOldPriceOpened = itemOldPriceOpenedW.getSize();
        System.out.println("Размер обычной цены на странице продукта: " + sizeItemOldPriceOpened);

        WebElement itemNewPriceOpenedW = contentOpenedW.findElement(By.cssSelector("strong"));
        String itemNewPriceOpened = itemNewPriceOpenedW.getAttribute("textContent");
        String itemNewPriceClassOpened = itemNewPriceOpenedW.getAttribute("class");
        String itemNewPriceOpenedRGB = itemNewPriceOpenedW.getCssValue("color");
        String[] itemNewPriceOpenedColor = itemNewPriceOpenedRGB.replaceAll("[rgb(\\)]", "").split(",");
        System.out.println(Arrays.toString(itemNewPriceOpenedColor));
        int greenOpenedNew = Integer.parseInt(itemNewPriceOpenedColor[1].trim());
        int blueOpenedNew = Integer.parseInt(itemNewPriceOpenedColor[2].trim());
        if ((greenOpenedNew == 0) && (blueOpenedNew == 0)) {
            System.out.println("Цвет акционной цены на странице продукта ("+ itemNewPriceRGB +") красный" );
        } else {
            AssertionError assertError = new AssertionError();
            System.out.println("Ошибка: " + assertError.getMessage());
            System.out.println("Цвет акционной цены на странице продукта ("+ itemNewPriceRGB +") НЕ красный");
            Assert.fail();
        }
        Dimension sizeItemNewPriceOpened = itemNewPriceOpenedW.getSize();
        System.out.println("Размер акционной цены на странице продукта: " + sizeItemNewPriceOpened);

        //Сравниваем размеры элементов
        if ( (sizeItemNewPriceOpened.getHeight() < sizeItemOldPriceOpened.getHeight()) &&
                (sizeItemNewPriceOpened.getWidth() < sizeItemOldPriceOpened.getWidth())) {
            AssertionError assertError = new AssertionError();
            System.out.println("Ошибка: " + assertError.getMessage());
            System.out.println("Обычная цена крупнее, чем акционная");
            Assert.fail();
        } else {
            System.out.println("Акционная цена крупнее, чем обычная");
        }

        if (!(itemName.equals(itemNameOpened)) ) {
            AssertionError assertError = new AssertionError();
            System.out.println("Ошибка: " + assertError.getMessage());
            System.out.println("Название продукта на главной странице ("+ itemName +") != ("+itemNameOpened+")" );
            Assert.fail();
        }
        System.out.println("Название продукта на главной странице ("+ itemName +") такой же как в открывшемся окне ("+itemNameOpened+")" );

        if (!(itemOldPrice.equals(itemOldPriceOpened)) ) {
            AssertionError assertError = new AssertionError();
            System.out.println("Ошибка: " + assertError.getMessage());
            System.out.println("Обычная цена товара на главной странице ("+ itemOldPrice +") != ("+itemOldPriceOpened+")" );
            Assert.fail();
        }
        System.out.println("Обычная цена товара на главной странице ("+ itemOldPrice +") такая же как в открывшемся окне ("+itemOldPriceOpened+")" );

        if (!(itemOldPriceClass.equals(itemOldPriceClassOpened)) ) {
            AssertionError assertError = new AssertionError();
            System.out.println("Ошибка: " + assertError.getMessage());
            System.out.println("Класс обычной цены на главной странице ("+ itemOldPriceClass +") != ("+itemOldPriceClassOpened+")" );
            Assert.fail();
        }
        System.out.println("Класс обычной цены на главной странице ("+ itemOldPriceClass +") такой же как в открывшемся окне ("+itemOldPriceClassOpened+")" );

        if (!(itemNewPrice.equals(itemNewPriceOpened)) ) {
            AssertionError assertError = new AssertionError();
            System.out.println("Ошибка: " + assertError.getMessage());
            System.out.println("Акционная цена товара на главной странице ("+ itemNewPrice +") != ("+itemNewPriceOpened+")" );
            Assert.fail();
        }
        System.out.println("Акционная цена товара на главной странице ("+ itemNewPrice +") такая же как в открывшемся окне ("+itemNewPriceOpened+")" );

        if (!(itemNewPriceClass.equals(itemNewPriceClassOpened)) ) {
            AssertionError assertError = new AssertionError();
            System.out.println("Ошибка: " + assertError.getMessage());
            System.out.println("Класс акционной цены на главной странице ("+ itemNewPriceClass +") != ("+itemNewPriceClassOpened+")" );
            Assert.fail();
        }
        System.out.println("Класс акционной цены на главной странице ("+ itemNewPriceClass +") такой же как в открывшемся окне ("+itemNewPriceClassOpened+")" );
    }
}
