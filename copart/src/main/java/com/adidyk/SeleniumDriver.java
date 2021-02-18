package com.adidyk;

import com.adidyk.model.Transport;
import org.aspectj.lang.annotation.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * Class Transport used for creates new object transport with params: id, login, password, passwordConfirm,
 * name, surname, money.
 * @author Didyk Andrey (androsdav@gmail.com).
 * @since 10.02.2021.
 * @version 1.0.
 */
@Component
public class SeleniumDriver {

    private List<Transport> transports = new ArrayList<>();

    private WebDriver driver;

    private int page = 0;

    //@PostConstruct
    public void start() throws InterruptedException {
        System.out.println("init method:");
        System.setProperty("webdriver.gecko.driver", "/home/andrey/C/Program files/geckodriver/geckodriver");
        this.driver = new FirefoxDriver();
        driver.manage().deleteAllCookies();
        //driver.manage().cookies.deleteAllCookies(); //delete all cookies
        Thread.sleep(7000); //wait 7 seconds to clear cookies.
        this.driver.get("https://www.copart.com/vehicleFinderSearch/?displayStr=%5B2010%20TO%202021%5D&from=%2FvehicleFinder%2F&searchStr=%7B%22MISC%22:%5B%22%23VehicleTypeCode:VEHTYPE_V%22,%22%23LotYear:%5B2010%20TO%202021%5D%22%5D,%22sortByZip%22:false,%22buyerEnteredZip%22:null,%22milesAway%22:null%7D&searchCriteria=%7B%22query%22:%5B%22*%22%5D,%22filter%22:%7B%22FETI%22:%5B%22buy_it_now_code:B1%22%5D,%22MISC%22:%5B%22%23VehicleTypeCode:VEHTYPE_V%22,%22%23LotYear:%5B2010%20TO%202021%5D%22%5D%7D,%22sort%22:%5B%22auction_date_type%20desc%22,%22auction_date_utc%20asc%22%5D,%22watchListOnly%22:false,%22searchName%22:%22%22,%22freeFormSearch%22:false%7D");
    }

    SeleniumDriver() {

    }

    //@Scheduled(fixedRate = 120000)
    public void test() throws InterruptedException {
        this.driver.navigate().refresh();

        WebElement tbody = new WebDriverWait(driver, 60).until(ExpectedConditions.presenceOfElementLocated(By.tagName("tbody")));
        Thread.sleep(2000);
        List<WebElement> rows = tbody.findElements(By.tagName("tr"));
        System.out.println();
        for (WebElement row : rows) {
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
            String currentBid = row.findElements(By.cssSelector("span[ng-bind]")).get(1).getText();
            String buyItNow = row.findElement(By.xpath("//*[contains(text(), 'Buy It Now Price')]")).getText().split(":")[1];
            */
            //this.transports.add(new Transport(lot, year, make, model, item, location, lineRow, saleDate, odometer, docType, damage,estRetailValue, currentBid, buyItNow));
        }

        System.out.println();
        System.out.println("page : " + this.page++);
        System.out.println("sleep .......");
        this.printAllTransport();
    }

    private void printAllTransport() {
        /*
        int index = 0;
        for (Transport transport : transports) {
            System.out.println("[" + index + "]:  " + transport);
            index ++;

        }
        this.transports.clear();*/
    }

}
