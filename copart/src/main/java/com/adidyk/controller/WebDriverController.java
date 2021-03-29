package com.adidyk.controller;

import com.adidyk.model.Filter;
import com.adidyk.service.WebDriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Class Transport used for creates new object transport with params: id, login, password, passwordConfirm,
 * name, surname, money.
 * @author Didyk Andrey (androsdav@gmail.com).
 * @since 10.02.2021.
 * @version 1.0.
 */
@Controller
public class WebDriverController {

    /**
     * @param webDriverService - seb driver service.
      */
    private WebDriverService webDriverService;

    /**
     * WebDriverController - constructor.
     * @param webDriverService - web driver service.
     */
    @Autowired
    WebDriverController(WebDriverService webDriverService) {
        this.webDriverService = webDriverService;
    }

    /**
     * initWebDriver - init web driver.
     */
    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public String initWebDriver() {
        this.webDriverService.initFireFoxWebDriver();
        return "index";
    }

    @RequestMapping(value="/quit", method = RequestMethod.GET)
    public String quit() {
        this.webDriverService.quitWebDriver();
        return "index";
    }


    @RequestMapping(value = "/parse", method = RequestMethod.GET)
    public String getFormParse() {
        return "parse";

    }
    @RequestMapping(value = "/parse", method = RequestMethod.POST)
    public String parse(@RequestParam("url") String url) throws InterruptedException {
        System.out.println("Url :" + url);
        this.webDriverService.parse(url);
        return "index";
    }

    @RequestMapping(value = "/copart", method = RequestMethod.GET)
    public String copart(Model model) throws InterruptedException {
        List<Filter> filters = this.webDriverService.moveToVehicleFinderSearch();
        model.addAttribute("filters", filters);
        return "copart";
    }

}
