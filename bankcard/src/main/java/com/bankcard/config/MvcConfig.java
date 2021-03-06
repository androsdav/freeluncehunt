package com.bankcard.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Class MvcConfig  used for adds view controllers.
 * @author Didyk Andrey (androsdav@gmail.com).
 * @since 02.02.2020.
 * @version 1.0.
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    /**
     * addViewControllers - adds view controllers.
     * @param registry - link variable to object of class ViewControllerRegistry.
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
    }

}