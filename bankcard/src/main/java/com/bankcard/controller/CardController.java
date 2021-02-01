package com.bankcard.controller;

import com.bankcard.model.Card;
import com.bankcard.model.User;
import com.bankcard.service.CardService;
import com.bankcard.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
public class CardController {

    /**
     * @param userController - user controller.
     */
    private UserService userService;

    private CardService cardService;

    CardController(UserService userService, CardService cardService) {
        this.userService = userService;
        this.cardService = cardService;
    }

    @RequestMapping(value = "/saveCardForm/{login}", method = RequestMethod.GET)
    public String saveCardForm(@PathVariable("login") String login, Model model, Card card) {
        User userDB = this.userService.findUserByLogin(new User(login));
        model.addAttribute( "card", card);
        model.addAttribute("user", userDB);
        return "saveCard";
    }

    @RequestMapping(value = "/saveCard", method = RequestMethod.POST)
    public String saveCard(@RequestParam("userLogin") String userLogin, Card card) {
        card.setUser(this.userService.findUserByLogin(new User(userLogin)));
        this.cardService.save(card);
        return "index";
    }

    @RequestMapping(value = "/cardInfo/{login}", method = RequestMethod.GET)
    public String findAllCardsByUserByLogin(@PathVariable("login") String login, Model model) {
        User user = this.userService.findUserByLogin(new User(login));
        List<Card> cards = this.cardService.findCardByUser(user);
        model.addAttribute("cards", cards);
        return "cardInfo";
    }

    @RequestMapping(value = "/transferMoneyFromUserToCardForm/{login}", method = RequestMethod.GET)
    public String transferMoneyFromUserToCardForm(@PathVariable("login") String login, Model model) {
        User userDB = this.userService.findUserByLogin(new User(login));
        List<Card> cards = this.cardService.findCardByUser(userDB);
        if (cards.size() == 0) {
            model.addAttribute("zeroCardError", "you don't have any cards");
            return "zeroCardError";
        }
        model.addAttribute( "cards", cards);
        model.addAttribute("user", userDB);
        return "transferMoneyFromUserToCard";
    }

    @RequestMapping(value = "/transferMoneyFromUserToCard", method = RequestMethod.POST)
    public String transferMoneyFromUserToCard(@RequestParam("userLogin") String userLogin,
                                              @RequestParam("cardName") String cardName,
                                              @RequestParam("transferMoney") Float transferMoney, Model model) {
        User userDB = this.userService.findUserByLogin(new User(userLogin));
        if (userDB.getMoney() < transferMoney) {
            model.addAttribute("notEnoughMoneyError", "not enough money");
            return this.transferMoneyFromUserToCardForm(userLogin, model);
        }
        Card cardDB = this.cardService.findCardByUseAndName(userDB, new Card(cardName));
        userDB.setMoney(userDB.getMoney() - transferMoney);
        cardDB.setMoney(cardDB.getMoney() + transferMoney);
        this.cardService.save(cardDB);
        return "index";
    }

    @RequestMapping(value = "/transferMoneyFromCardToCardForm/{login}", method = RequestMethod.GET)
    public String transferMoneyFromCardToCard(@PathVariable("login") String login, Model model) {
        User userDB = this.userService.findUserByLogin(new User(login));
        List<Card> cards = this.cardService.findCardByUser(userDB);
        if (cards.size() == 0) {
            model.addAttribute("zeroCardError", "you don't have any cards");
            return "zeroCardError";
        }
        model.addAttribute( "cards", cards);
        model.addAttribute("user", userDB);
        return "transferMoneyFromCardToCard";
    }

    @RequestMapping(value = "/transferMoneyFromCardToCard", method = RequestMethod.POST)
    public String transferMoneyFromCardToCard(@RequestParam("userLogin") String userLogin,
                                              @RequestParam("fromCardName") String fromCardName,
                                              @RequestParam("toCardName") String toCardName,
                                              @RequestParam("transferMoney") Float transferMoney, Model model) {
        User userDB = this.userService.findUserByLogin(new User(userLogin));
        if (this.cardService.findCardByUser(userDB).size() == 1) {
            model.addAttribute("oneCardError", "you only have one card");
            return this.transferMoneyFromCardToCard(userLogin, model);
        }
        if (fromCardName.equals(toCardName)) {
            model.addAttribute("cardsSame", "cards are same");
            return this.transferMoneyFromCardToCard(userLogin, model);
        }
        Card fromCardDB = this.cardService.findCardByUseAndName(userDB, new Card(fromCardName));
        if (fromCardDB.getMoney() < transferMoney) {
            model.addAttribute("notEnoughMoneyError", "not enough money");
            return this.transferMoneyFromCardToCard(userLogin, model);
        }
        Card toCardDB = this.cardService.findCardByUseAndName(userDB, new Card(toCardName));
        fromCardDB.setMoney(fromCardDB.getMoney() - transferMoney);
        toCardDB.setMoney(toCardDB.getMoney() + transferMoney);
        this.cardService.save(fromCardDB);
        this.cardService.save(toCardDB);
        return "index";
    }

}