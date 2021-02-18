package com.adidyk.service;

import com.adidyk.model.Transport;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Class Transport used for creates new object transport with params: id, login, password, passwordConfirm,
 * name, surname, money.
 * @author Didyk Andrey (androsdav@gmail.com).
 * @since 10.02.2021.
 * @version 1.0.
 */
@Service
public class WebDriverService {

    /**
     * @param driver - web driver.
     */
    private WebDriver webDriver;

    /**
     * @param transports - list transports.
     */
    private List<Transport> transports = new ArrayList<>();

    /**
     * initWebDriver - init web driver.
     */
    public void initFireFoxWebDriver() {
        System.setProperty("webdriver.gecko.driver", "/home/andrey/C/Program files/geckodriver/geckodriver");
        FirefoxOptions options = new FirefoxOptions();
        this.webDriver = new FirefoxDriver(options);
    }

    /**
     * quitWebDriver - quit web driver.
     */
    public void quitWebDriver() {
        this.webDriver.quit();
    }

    private static boolean isElementPresent(WebElement webElement, By by) {
        try {
            webElement.findElement(by);
            System.out.println("web element: " + webElement.findElement(by).getTagName());
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    /**
     * parse - parse (parse only first page results).
     * @param url - url parse.
     */
    public void parse(String url) throws InterruptedException {
        this.webDriver.get(url);
        WebElement tbody = new WebDriverWait(this.webDriver, 60).until(ExpectedConditions.presenceOfElementLocated(By.tagName("tbody")));
        Thread.sleep(2000);
        System.out.println("tbody found");
        List<WebElement> rows = tbody.findElements(By.tagName("tr"));
        for (WebElement row : rows) {

            /*
            System.out.println();
            System.out.println(row.getText());
            System.out.println();*/
            List<WebElement> elements = row.findElements(By.xpath(".//li[contains(text(), 'Buy It Now Price')]"));
            for (WebElement element : elements) {
                System.out.println(element.getText());
            }
            System.out.println();


            /*
            String lot = row.findElement(By.cssSelector("a.search-results")).getText();
            String year = row.findElement(By.cssSelector("span[data-uname='lotsearchLotcenturyyear']")).getText();
            String make = row.findElement(By.cssSelector("span[data-uname='lotsearchLotmake']")).getText();
            String model = row.findElement(By.cssSelector("span[data-uname='lotsearchLotmodel']")).getText();
            String item = row.findElement(By.cssSelector("span[data-uname='lotsearchItemnumber']")).getText();
            String location = row.findElement(By.cssSelector("span[data-uname='lotsearchLotyardname']")).getText();
            String lineRow = row.findElement(By.cssSelector("span[pref-code='searchPreference.searchFields']")).getText();
            String saleDate = row.findElement(By.cssSelector("span[data-uname='lotsearchLotauctiondate']")).getText();
            String odometer = row.findElement(By.cssSelector("span[data-uname='lotsearchLotodometerreading']")).getText();
            String docType = row.findElement(By.cssSelector("span[data-uname='lotsearchSaletitletype']")).getText();
            String damage = row.findElement(By.cssSelector("span[data-uname='lotsearchLotdamagedescription']")).getText();
            String estRetailValue = row.findElement(By.cssSelector("span[data-uname='lotsearchLotestimatedretailvalue']")).getText();
            String currentBid = null;
            String buyItNow = null;
            String startingBid = null;
            if (isElementPresent(row, By.cssSelector("span[ng-bind]"))) {
                System.out.println("currentBid is true ");
                currentBid = row.findElements(By.cssSelector("span[ng-bind]")).get(1).getText();
            }
            if (isElementPresent(row, By.xpath("//li[contains(text(), 'Buy It Now Price')]"))) {
                System.out.println("byItNow is true ");
                buyItNow = row.findElement(By.xpath("//li[contains(text(), 'Buy It Now Price')]")).getText().split(":")[1];
            }
            if (isElementPresent(row, By.xpath("//*[contains(text(), 'Starting Bid')]"))) {
                System.out.println("startingBid is true ");
                startingBid = row.findElement(By.xpath("//*[contains(text(), 'Starting Bid')]")).getText().split(":")[1];
            }
            /*
            if (row.findElement(By.className("list-unstyled")).findElements(By.tagName("li")).size() == 5) {
                currentBid = row.findElements(By.cssSelector("span[ng-bind]")).get(1).getText();
                if (isElementPresent(row, By.xpath("//*[contains(text(), 'Buy It Now Price')]"))) {
                    buyItNow = row.findElement(By.xpath("//*[contains(text(), 'Buy It Now Price')]")).getText().split(":")[1];
                }
            } else if (row.findElement(By.className("list-unstyled")).findElements(By.tagName("li")).size() == 4) {
                startingBid = row.findElement(By.xpath("//*[contains(text(), 'Starting Bid')]")).getText().split(":")[1];

            }*/
            //this.transports.add(new Transport(lot, year, make, model, item, location, lineRow, saleDate, odometer, docType, damage, estRetailValue, currentBid, buyItNow, startingBid));
        }
            System.out.println("print transport");
        int index = 0;
        for (Transport transport : transports) {
            System.out.println("[" + index + "]:  " + transport);
            index ++;

        }
        this.transports.clear();
    }

}
