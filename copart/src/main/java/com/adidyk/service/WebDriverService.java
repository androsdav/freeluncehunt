package com.adidyk.service;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.stereotype.Service;

@Service
/**
 * Class Transport used for creates new object transport with params: id, login, password, passwordConfirm,
 * name, surname, money.
 * @author Didyk Andrey (androsdav@gmail.com).
 * @since 10.02.2021.
 * @version 1.0.
 */
public class WebDriverService {

    /**
     * @param driver - web driver.
     */
    private WebDriver driver;

    /**
     * initWebDriver - init web driver.
     */
    public void initWebDriver() {
        System.setProperty("webdriver.gecko.driver", "/home/andrey/C/Program files/geckodriver/geckodriver");
        this.driver = new FirefoxDriver();
    }
}
