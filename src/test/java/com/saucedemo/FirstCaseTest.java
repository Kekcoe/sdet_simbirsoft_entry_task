package com.saucedemo;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class FirstCaseTest {

    private static ConfigurationProperties ConfProperties;

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", ConfigurationProperties.getProperty("chromedriver"));
        //создание экземпляра драйвера
        WebDriver driver = new ChromeDriver();
        //окно разворачивается на полный экран
        driver.manage().window().maximize();
        //задержка на выполнение теста = 10 сек.
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //получение ссылки на страницу входа из файла настроек
        driver.get(ConfigurationProperties.getProperty("productpage")); }

    @Test
    public void successfullyBuyTest() {


    }

}
