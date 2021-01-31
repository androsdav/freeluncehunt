package com.bankcard;

import com.bankcard.model.Card;
import com.bankcard.model.User;
import com.bankcard.repository.CardRepository;
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

    private UserRepository userRepository;

    private CardRepository cardRepository;

    @Autowired
    public StartUi(UserRepository userRepository, CardRepository cardRepository) {
        this.userRepository = userRepository;
        this.cardRepository = cardRepository;
    }

    /**
     * main - main.
     *
     * @param arg - arg.
     */
    public static void main(String[] arg) {
        SpringApplication.run(StartUi.class, arg);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void testJpaMethods() {
        System.out.println();
        //System.out.println("gets all users: " + this.userRepository.findByLoginAndLoadAllAndCards("user1"));
        //Optional<User> user = this.userRepository.findById(1);
        //List<Card> cards = user.get().getCards();
        //System.out.println("cards: " + cards);
        System.out.println("cards: " + this.cardRepository.findAll());

        System.out.println();
        System.out.println("gets all users: " + this.userRepository.findById(1));
        System.out.println();
        System.out.println("gets all roles: " + this.userRepository.findAll());
    }


}
