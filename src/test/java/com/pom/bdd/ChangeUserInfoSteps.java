package com.pom.bdd;
import com.pom.junit.LoginPage;
import cucumber.api.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;


public class ChangeUserInfoSteps {
    UserInfoPage userInfoPage;
    WebDriver driver;

    @Given("^User is logged in to CodersLab shop$")
    public void userIsLooggedInToCodersLabShop() {
        System.setProperty("webdriver.gecko.driver",
                "src/test/resources/geckodriver.exe");

        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        driver.get("https://prod-kurs.coderslab.pl/index.php?controller=authentication");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAs("michal.dobrzycki@coderslab.pl", "CodersLab");
    }

    @When("^He goes to UserInformationPage$")
    public void heGoesToUserInformationPage() {
        driver.get("https://prod-kurs.coderslab.pl/index.php?controller=identity");
        userInfoPage = new UserInfoPage(driver);
    }

    @And("^He changes his birthday to \"([^\"]*)\"$")
    public void heChangesHisBirthdayTo(String date) {
        userInfoPage.setBirthdate(date);
    }

    @And("^He signs up for our newsletter$")
    public void heSignsUpForOurNewsletter() {
        userInfoPage.signInForNewsletter();
    }

    @And("^He saves information$")
    public void heSavesInformation() {
        userInfoPage.submitUserInfo();
    }

    @Then("^He sees \"([^\"]*)\"$")
    public void heSees(String message) {
        Assert.assertEquals(message, userInfoPage.getUpdateInformation());
    }
}
