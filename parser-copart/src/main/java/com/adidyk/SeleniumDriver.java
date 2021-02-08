package com.adidyk;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

public class SeleniumDriver {

    public void test() throws InterruptedException {
        System.setProperty("webdriver.gecko.driver", "/home/andrey/C/Program files/geckodriver/geckodriver");

        WebDriver driver = new FirefoxDriver();
        driver.get("https://www.copart.com/vehicleFinderSearch/?displayStr=%5B2010%20TO%202021%5D&from=%2FvehicleFinder%2F&searchStr=%7B%22MISC%22:%5B%22%23VehicleTypeCode:VEHTYPE_V%22,%22%23LotYear:%5B2010%20TO%202021%5D%22%5D,%22sortByZip%22:false,%22buyerEnteredZip%22:null,%22milesAway%22:null%7D&searchCriteria=%7B%22query%22:%5B%22*%22%5D,%22filter%22:%7B%22FETI%22:%5B%22buy_it_now_code:B1%22%5D,%22MISC%22:%5B%22%23VehicleTypeCode:VEHTYPE_V%22,%22%23LotYear:%5B2010%20TO%202021%5D%22%5D%7D,%22sort%22:%5B%22auction_date_type%20desc%22,%22auction_date_utc%20asc%22%5D,%22watchListOnly%22:false,%22searchName%22:%22%22,%22freeFormSearch%22:false%7D");

        //WebElement table = new WebDriverWait(driver, 60).until(ExpectedConditions.presenceOfElementLocated(By.id("serverSideDataTable")));
        WebElement tbody = new WebDriverWait(driver, 60).until(ExpectedConditions.presenceOfElementLocated(By.tagName("tbody")));
        Thread.sleep(2000);
        List<WebElement> rows = tbody.findElements(By.tagName("tr"));
        System.out.println();
        for (WebElement row : rows) {
            String lot = row.findElement(By.cssSelector("a.search-results")).getText();
            String year = row.findElement(By.cssSelector("span[data-uname='lotsearchLotcenturyyear']")).getText();
            String make = row.findElement(By.cssSelector("span[data-uname='lotsearchLotmake']")).getText();
            String model = row.findElement(By.cssSelector("span[data-uname='lotsearchLotmodel']")).getText();
            System.out.println("lot: " + lot + "; year: " + year + "; make: " + make + "; model: " + model);
            //String lot = row.findElement(By.cssSelector("a.search-results")).getText();
            //String year = row.findElement(By.name("data-uname")).getText();
            //System.out.println("lot: " + lot);
            //System.out.println("year: " + year);
        }

        System.out.println("sleep .......");
        Thread.sleep(3000);
        driver.quit();

    }
}
