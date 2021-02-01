package com.bankcard.service;

import com.bankcard.model.Card;
import com.bankcard.model.User;
import com.bankcard.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CardService {

    private CardRepository cardRepository;

    @Autowired
    CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public void save(Card card) {
        this.cardRepository.save(card);
        System.out.println("save card" + card);
    }

    public List<Card> findCardByUser(User user) {
        return this.cardRepository.findCardByUser(user);
    }

    public Card findCardByUseAndName(User user, Card card) {
       return  this.cardRepository.findCardByUserAndName(user, card.getName());

    }

}
