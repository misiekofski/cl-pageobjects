package com.pom.junit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class LoginTest {
    private static WebDriver driver;

    @Before
    public void setUp(){
        System.setProperty("webdriver.gecko.driver",
                "src/test/resources/geckodriver.exe");

        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://prod-kurs.coderslab.pl/index.php?controller=authentication");
    }

    @Test
    public void testLoginWithProperCredentials() {
        LoginPage loginPage = new LoginPage(driver);
        ProductPage productPage = new ProductPage(driver);
        loginPage.loginAs("michal.dobrzycki@coderslab.pl", "CodersLab");

        Assert.assertEquals("Automated Tester", loginPage.getLoggedUsername());

        driver.findElement(By.xpath("//*[@data-id-product='1']")).click();

        double regularPrice = productPage.getRegularPrice();
        double currentPrice = productPage.getCurrentPrice();
        double discount = productPage.getDiscountValue();

        // oblicz cenę po rabacie
        double afterDiscount = regularPrice - regularPrice*discount;

        Assert.assertEquals(19.12, currentPrice, 0.2);
        Assert.assertEquals(23.90, regularPrice, 0.2);

        System.out.println("Regular price: " + regularPrice);
        System.out.println("Current price: " + currentPrice);

        System.out.println("Should be after discount: " + afterDiscount);

        Assert.assertEquals(afterDiscount, currentPrice, 0.2);
    }

    @After
    public void tearDown() {
        //driver.quit();
    }

}