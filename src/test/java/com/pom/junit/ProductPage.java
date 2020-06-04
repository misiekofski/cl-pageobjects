package com.pom.junit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage {
    private WebDriver driver;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@class='current-price']/span")
    private WebElement currentPriceElement;

    @FindBy(xpath = "//span[@class='regular-price']")
    private WebElement regularPriceElement;

    @FindBy(xpath = "//span[contains(@class, 'discount-percentage')]")
    private WebElement discountPercentageElement;

    public double getCurrentPrice() {
        String stringCurrentPrice = currentPriceElement.getAttribute("content");
        return Double.parseDouble(stringCurrentPrice);
    }

    public double getRegularPrice() {
        String stringRegularPrice = regularPriceElement.getText().substring(1);
        return Double.parseDouble(stringRegularPrice);
    }

    public double getDiscountValue() {
        String discountValue = discountPercentageElement.getText();
        // split dzieli nam napis "SAVE 20%" na tablicę ["SAVE", "20%"]
        String percentage = discountValue.split(" ")[1];
        // odetnij ostatni znak (%) ze stringa
        percentage = percentage.substring(0, percentage.length()-1);
        return Double.parseDouble(percentage)/100;
    }

}
