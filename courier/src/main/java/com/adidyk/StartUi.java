package com.adidyk;

import com.adidyk.model.Customer;
import com.adidyk.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.web.client.RestTemplate;
import java.util.List;

/**
 * Class StartUi spring boot application run.
 *  @author Didyk Andrey (androsdav@gmail.com).
 *  @since 01.02.2021.
 *  @version 1.0.
 */
@SpringBootApplication
public class StartUi {

    CustomerRepository customerRepository;

    @Autowired
    StartUi(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

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

    @EventListener(ApplicationReadyEvent.class)
    public void testJpaMethods()  {
        List<Customer> customers = this.customerRepository.findAll();
        for (Customer customer : customers) {
            System.out.println("customer: " + customer);

        }
    }

}