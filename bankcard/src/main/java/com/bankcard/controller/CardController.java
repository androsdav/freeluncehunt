package com.bankcard.controller;

import com.bankcard.model.Card;
import com.bankcard.model.User;
import com.bankcard.service.CardService;
import com.bankcard.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Class CardController used for controller with front end.
 * @author Didyk Andrey (androsdav@gmail.com).
 * @since 01.02.2021.
 * @version 1.0.
 */
@Controller
public class CardController {

    /**
     * @param userController - user controller.
     */
    private UserService userService;

    /**
     * @param cardService - card service.
     */
    private CardService cardService;

    /**
     * CardController - constructor.
     * @param userService - user service.
     * @param cardService - card service.
     */
    CardController(UserService userService, CardService cardService) {
        this.userService = userService;
        this.cardService = cardService;
    }

    /**
     * checkZeroCard - check list off cards is empty form user and returns false is list off cards is empty or
     * returns false if list off cards is not empty.
     * @param login - user login.
     * @param model - model.
     * @return - returns false is list off cards is empty or returns false if list off cards is not empty.
     */
    private boolean checkZeroCard(String login, Model model) {
        User userDB = this.userService.findUserByLogin(new User(login));
        List<Card> cards = this.cardService.findCardByUser(userDB);
        if (cards.size() == 0) {
            model.addAttribute("zeroCardError", "you don't have any cards");
            return true;
        }
        model.addAttribute( "cards", cards);
        model.addAttribute("user", userDB);
        return false;
    }

    /**
     * saveCardForm - searches user in data base by login, adds user and card to model and returns
     * page saveCard.
     * @param login - user login.
     * @param model - model.
     * @param card - card.
     * @return - returns model user, model card and returns page saveCard.html.
     */
    @RequestMapping(value = "/saveCardForm/{login}", method = RequestMethod.GET)
    public String saveCardForm(@PathVariable("login") String login, Model model, Card card) {
        User userDB = this.userService.findUserByLogin(new User(login));
        model.addAttribute( "card", card);
        model.addAttribute("user", userDB);
        return "saveCard";
    }

    /**
     * saveCard - gets card from saveCard.html and save card in data base if saveCard don`t have 'error' and
     * returns start page index.html.
     * @param userLogin - user login.
     * @param card - card.
     * @param model - model.
     * @param result - result.
     * @return - returns start page index.html.
     */
    @RequestMapping(value = "/saveCard", method = RequestMethod.POST)
    public String saveCard(@RequestParam("userLogin") String userLogin, Card card, Model model, BindingResult result) {
        if (result.hasErrors()) {
            return this.saveCardForm(userLogin, model, new Card());
        }
        if (this.cardService.findCardByUserAndName(this.userService.findUserByLogin(new User(userLogin)), card) != null) {
            model.addAttribute("cardLoginError", "card with same name already exists in user");
            return this.saveCardForm(userLogin, model, new Card());
        }
        card.setUser(this.userService.findUserByLogin(new User(userLogin)));
        this.cardService.saveCard(card);
        return "index";
    }

    /**
     * findAllCardByUserByLogin - searches all list cards, adds list of card to model and returns page cardInfo.html.
     * @param login - user login.
     * @param model - model.
     * @return - returns page cardInfo.html.
     */
    @RequestMapping(value = "/cardInfo/{login}", method = RequestMethod.GET)
    public String findAllCardsByUserLogin(@PathVariable("login") String login, Model model) {
        User user = this.userService.findUserByLogin(new User(login));
        List<Card> cards = this.cardService.findCardByUser(user);
        model.addAttribute("cards", cards);
        return "cardInfo";
    }

    /**
     * transferMoneyFromUserToCardForm - searches user in data base in login, list off cards by user,
     * adds list off cards and user to model and returns page transferMoneyFromUserToCard.html.
     * @param login - user login.
     * @param model - model.
     * @return - returns model user, model list off cards and page transferMoneyFromUserToCard.html.
     */
    @RequestMapping(value = "/transferMoneyFromUserToCardForm/{login}", method = RequestMethod.GET)
    public String transferMoneyFromUserToCardForm(@PathVariable("login") String login, Model model) {
        if (checkZeroCard(login, model)) return "zeroCardError";
        return "transferMoneyFromUserToCard";
    }

    /**
     * transferMoneyFromUserToCard - gets user login, card name and transfer money from transferMoneyFromUserToCard.html
     * and do transfer many from user to card if method don`t have 'error', returns start page index.html. If user
     * don`t have enough money returns transferMoneyFromUserToCard.html else returns index.html.
     * @param userLogin - user login.
     * @param cardName - card name.
     * @param transferMoney - transfer money.
     * @param model - model.
     * @return - if user don`t have enough money returns transferMoneyFromUserToCard.html else returns index.html.
     */
    @RequestMapping(value = "/transferMoneyFromUserToCard", method = RequestMethod.POST)
    public String transferMoneyFromUserToCard(@RequestParam("userLogin") String userLogin,
                                              @RequestParam("cardName") String cardName,
                                              @RequestParam("transferMoney") Float transferMoney, Model model) {
        User userDB = this.userService.findUserByLogin(new User(userLogin));
        if (userDB.getMoney() < transferMoney) {
            model.addAttribute("notEnoughMoneyError", "not enough money");
            return this.transferMoneyFromUserToCardForm(userLogin, model);
        }
        Card cardDB = this.cardService.findCardByUserAndName(userDB, new Card(cardName));
        userDB.setMoney(userDB.getMoney() - transferMoney);
        cardDB.setMoney(cardDB.getMoney() + transferMoney);
        this.cardService.saveCard(cardDB);
        return "index";
    }

    /**
     * transferMoneyFromCardToCard - searches user in data base in login, list off cards by user,
     * adds list off cards and user to model and returns page transferMoneyFromUserToCard.html.
     * @param login - user login.
     * @param model - model.
     * @return - returns model user, model list off cards and page transferMoneyFromUserToCard.html.
     */
    @RequestMapping(value = "/transferMoneyFromCardToCardForm/{login}", method = RequestMethod.GET)
    public String transferMoneyFromCardToCard(@PathVariable("login") String login, Model model) {
        if (checkZeroCard(login, model)) return "zeroCardError";
        return "transferMoneyFromCardToCard";
    }

    /**
     * transferMoneyFromCardToCard - gets user login, card name from, card name to and transfer money from
     * transferMoneyFromCardToCard.html and do transfer many from card to card if method don`t have 'error',
     * returns start page index.html. If fromCard don`t have enough money returns transferMoneyFromCardToCard.html
     * else returns index.html.
     * @param userLogin - user login.
     * @param fromCardName - from card name.
     * @param toCardName - to card name.
     * @param transferMoney - transfer money.
     * @param model - model.
     * @return - If fromCard don`t have enough money returns transferMoneyFromCardToCard.html else returns index.html.
     */
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
        Card fromCardDB = this.cardService.findCardByUserAndName(userDB, new Card(fromCardName));
        Card toCardDB = this.cardService.findCardByUserAndName(userDB, new Card(toCardName));
        if (fromCardDB.getMoney() < transferMoney) {
            model.addAttribute("notEnoughMoneyError", "not enough money");
            return this.transferMoneyFromCardToCard(userLogin, model);
        }
        fromCardDB.setMoney(fromCardDB.getMoney() - transferMoney);
        toCardDB.setMoney(toCardDB.getMoney() + transferMoney);
        this.cardService.saveCard(fromCardDB);
        this.cardService.saveCard(toCardDB);
        return "index";
    }

}