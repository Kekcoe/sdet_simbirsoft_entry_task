package com.saucedemo;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;


public class FirstCaseTest {

    private static WebDriver driver;
    private static final Logger logger = LoggerFactory.getLogger(FirstCaseTest.class);

    @BeforeAll
    static void setupAll() {
        logger.info("start setupAll");
        System.setProperty("webdriver.chrome.driver", ConfigurationProperties.getProperty("chromedriver"));
        ChromeOptions option = new ChromeOptions();
        option.addArguments("--remote-allow-origins=*");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(option);
    }


    @BeforeEach
    public void setup() {
        //окно разворачивается на полный экран
        driver.manage().window().maximize();
        //задержка на выполнение теста = 10 сек.
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void successfullyBuyTest() {
        driver.get(ConfigurationProperties.getProperty("productpage"));
    }

    @Test
    public void failedLoginTest(){
     logger.info("start failed login test");
     driver.get(ConfigurationProperties.getProperty("loginpage"));
    }

}
