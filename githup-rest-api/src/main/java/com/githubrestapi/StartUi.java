package com.githubrestapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * Class StartUi spring boot application run.
 *  @author Didyk Andrey (androsdav@gmail.com).
 *  @since 01.02.2021.
 *  @version 1.0.
 */
@SpringBootApplication
public class StartUi {

    /**
     * main - spring boot application run.
     * @param arg - arg.
     */
    public static void main(String[] arg) {
        SpringApplication.run(StartUi.class, arg);
    }

    /**
     * getRestTemplate - gets link to object class RestTemplate.
     * @return - returns link to object of class RestTemplate.
     */
    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

}