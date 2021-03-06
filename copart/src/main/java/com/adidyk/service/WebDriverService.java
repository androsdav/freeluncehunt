package com.adidyk.service;

import com.adidyk.model.Filter;
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

    private List<Filter> filters = new ArrayList<>();

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

    /**
     * isElementPresent - is element present.
     * @param webElement - web element.
     * @param by - by.
     * @return - return true or false.
     */
    private static boolean isElementPresent(WebElement webElement, By by) {
        try {
            webElement.findElement(by);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    /**
     * isElementPresent - is element present.
     * @param webDriver - web driver.
     * @param by - by.
     * @return - return true or false.
     */
    private static boolean isElementPresent(WebDriver webDriver, By by) {
        try {
            webDriver.findElement(by);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    /**
     * parseOneRow - parse one row from table.
     * @param row - row.
     * @return - return transport.
     */
    private Transport parseOneRow(WebElement row) {
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
            currentBid = row.findElements(By.cssSelector("span[ng-bind]")).get(1).getText();
        } else if (isElementPresent(row, By.xpath(".//li[contains(text(), 'Current Bid')]"))) {
            currentBid = row.findElement(By.xpath(".//li[contains(text(), 'Current Bid')]")).getText().split(":")[1];
        }
        if (isElementPresent(row, By.xpath(".//li[contains(text(), 'Buy It Now Price')]"))) {
            buyItNow = row.findElement(By.xpath(".//li[contains(text(), 'Buy It Now Price')]")).getText().split(":")[1];
        }
        if (isElementPresent(row, By.xpath(".//li[contains(text(), 'Starting Bid')]"))) {
            startingBid = row.findElement(By.xpath(".//li[contains(text(), 'Starting Bid')]")).getText().split(":")[1];
        }
        return new Transport(lot, year, make, model, item, location, lineRow, saleDate, odometer, docType, damage, estRetailValue, currentBid, buyItNow, startingBid);
    }

    /**
     * parse - parse (parse only first page results).
     * @param url - url parse.
     */
    public void parse(String url) throws InterruptedException {
        this.webDriver.get(url);
        new WebDriverWait(this.webDriver, 60).until(ExpectedConditions.presenceOfElementLocated(By.tagName("tbody")));
        Thread.sleep(2000);

        int page;
        if (isElementPresent(this.webDriver, By.xpath(".//li[@class='paginate_button last']/a"))) {
            this.webDriver.findElement(By.xpath(".//li[@class='paginate_button last']/a")).click();
            Thread.sleep(2000);
            page = Integer.parseInt(this.webDriver.findElement(By.xpath(".//li[@class='paginate_button active']/a")).getText());
            this.webDriver.findElement(By.xpath(".//li[@class='paginate_button first']/a")).click();
            Thread.sleep(2000);
        } else {
            page = 1;
        }

        for (int index = 0; index < page; index++) {
            WebElement body = new WebDriverWait(this.webDriver, 60).until(ExpectedConditions.presenceOfElementLocated(By.tagName("tbody")));
            Thread.sleep(2000);
            for (WebElement row : body.findElements(By.tagName("tr"))) {
                this.transports.add(this.parseOneRow(row));
            }
            if (isElementPresent(this.webDriver, By.xpath(".//li[@class='paginate_button next']"))) {
                this.webDriver.findElement(By.xpath(".//li[@class='paginate_button next']/a[text()='Next']")).click();
            }
        }
        System.out.println(page);
        System.out.println();
        int index = 0;
        for (Transport transport : transports) {
            System.out.println("[" + index + "]:  " + transport);
            index ++;

        }
        this.transports.clear();
    }

    public List<Filter> moveToVehicleFinderSearch() throws InterruptedException {
        String url = "https://www.copart.com";
        this.webDriver.get(url);
        Thread.sleep(8000);
        this.webDriver.findElement(By.xpath(".//a[normalize-space()='Find Vehicles']")).click();
        this.webDriver.findElement(By.xpath(".//a[normalize-space()='Vehicle Finder']")).click();
        Thread.sleep(8000);
        this.webDriver.findElement(By.xpath(".//button[@class='btn btn-green']")).click();
        Thread.sleep(8000);
        List<WebElement> filters = this.webDriver.findElements(By.xpath("//a[contains(@data-uname, 'Filter')]"));
        for (WebElement item : filters) {
            if (!item.getText().equals("")) {
                this.filters.add(new Filter(item.getText()));
            }
        }
        for (Filter filter1 : this.filters) {
            System.out.println("filter: " + filter1);
        }
        return this.filters;

    }

}
