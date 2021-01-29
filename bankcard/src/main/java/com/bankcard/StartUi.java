package com.bankcard;

import com.bankcard.model.Role;
import com.bankcard.model.User;
import com.bankcard.repository.RoleRepository;
import com.bankcard.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.util.List;
import java.util.Optional;

/**
 * Class StartUi.
 */
@SpringBootApplication
public class StartUi {

    /**
     * main - main.
     *
     * @param arg - arg.
     */
    public static void main(String[] arg) {
        SpringApplication.run(StartUi.class, arg);
    }

    /*
    @EventListener(ApplicationReadyEvent.class)
    public void testJpaMethods() {
        System.out.println();
        System.out.println("gets all users: " + this.userRepository.findAll());
        System.out.println();
        System.out.println("gets all roles: " + this.roleRepository.findAll());
    }*/

}
