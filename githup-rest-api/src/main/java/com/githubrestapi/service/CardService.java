package com.githubrestapi.service;

import com.githubrestapi.model.Card;
import com.githubrestapi.model.User;
import com.githubrestapi.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Class CardService used for service logic with data base.
 * @author Didyk Andrey (androsdav@gmail.com).
 * @since 01.02.2021.
 * @version 1.0.
 */
@Service
public class CardService {

    /**
     * @param cardRepository - card repository.
     */
    private CardRepository cardRepository;

    /**
     * CardService - constructor.
     * @param cardRepository - card service.
     */
    @Autowired
    CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    /**
     * saveCard - save card in data base.
     * @param card - card.
     */
    public void saveCard(Card card) {
        this.cardRepository.save(card);
    }

    /**
     * findCardByUser - finds all cards by user and returns list of cards.
     * @param user - user.
     * @return - returns list of cards.
     */
    public List<Card> findCardByUser(User user) {
        return this.cardRepository.findCardByUser(user);
    }

    /**
     * findCardByUserName - finds card by user and card name.
     * @param user - user.
     * @param card - card.
     * @return - returns card by user and card name.
     */
    public Card findCardByUserAndName(User user, Card card) {
       return  this.cardRepository.findCardByUserAndName(user, card.getName());

    }

}