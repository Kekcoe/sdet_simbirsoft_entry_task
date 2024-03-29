package com.saucedemo;

import com.saucedemo.pages.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;



public class SauceDemoTest {
    private static WebDriver driver;
    static final Logger logger = LoggerFactory.getLogger(SauceDemoTest.class);
    public static LoginPage loginPage;
    public static ProductsPage productsPage;
    public static CartPage cartPage;
    public static CheckoutStepOnePage checkoutStepOnePage;
    public static CheckoutStepTwoPage checkoutStepTwoPage;
    public static CheckoutComplete checkoutComplete;

    @BeforeAll
    static void setupAll() {
        logger.info("start setupAll");
        System.setProperty("webdriver.chrome.driver", ConfigurationProperties.getProperty("chromedriver"));
        ChromeOptions option = new ChromeOptions();
        option.addArguments("--remote-allow-origins=*");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(option);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
        cartPage = new CartPage(driver);
        checkoutStepOnePage = new CheckoutStepOnePage(driver);
        checkoutStepTwoPage = new CheckoutStepTwoPage(driver);
        checkoutComplete = new CheckoutComplete(driver);
    }

    @Test
    public void successfullyBuyTest() {
        logger.info("start add to cart test");
        doLogin(ConfigurationProperties.getProperty("login"), ConfigurationProperties.getProperty("password"));
        driver.get(ConfigurationProperties.getProperty("productpage"));

        productsPage.clickButtonAddToCart();
        productsPage.clickBucketSymbol();

        cartPage.clickCheckoutBtn();

        checkoutStepOnePage.inputFirstName(ConfigurationProperties.getProperty("firstname"));
        checkoutStepOnePage.inputLastName(ConfigurationProperties.getProperty("lastname"));
        checkoutStepOnePage.inputPostalCode(ConfigurationProperties.getProperty("zipcode"));
        checkoutStepOnePage.clickContinueBtn();

        checkoutStepTwoPage.clickFinishBtn();

        String actualText = checkoutComplete.getCompleteText();
        String expectedText = "Thank you for your order!";
        reportAllure(actualText, expectedText);

        logger.info("finish add to cart test");
    }

    @Test
    public void failedLoginTest() {
        logger.info("start failed login test");
        doLogin(ConfigurationProperties.getProperty("wrong_login"), ConfigurationProperties.getProperty("wrong_password"));
        String actualErrorMessage = loginPage.getErrorMessage();
        String expectedErrrorMessage = "Epic sadface: Username and password do not match any user in this service";
        reportAllure(actualErrorMessage, expectedErrrorMessage);
    }

    private void doLogin(String login, String password) {
        driver.get(ConfigurationProperties.getProperty("loginpage"));
        loginPage.inputLogin(login);
        loginPage.inputPassword(password);
        loginPage.clickLoginBtn();
    }

    @Step
    public void reportAllure(String actualErrorMessage, String expectedErrrorMessage) {
        Assertions.assertEquals(expectedErrrorMessage, actualErrorMessage);
    }

    @AfterAll
    public static void cleanUp(){
        logger.info("cleanUp");
        driver.quit();
    }
}