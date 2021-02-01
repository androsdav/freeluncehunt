package com.bankcard.controller;

import com.bankcard.model.Card;
import com.bankcard.model.User;
import com.bankcard.service.CardService;
import com.bankcard.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
    public String transferMoneyFromUserToCard111(@PathVariable("login") String login, Model model) {
        User userDB = this.userService.findUserByLogin(new User(login));
        List<Card> cards = this.cardService.findCardByUser(userDB);
        model.addAttribute( "cards", cards);
        model.addAttribute("user", userDB);
        return "transferMoneyFromUserToCard";
    }

    @RequestMapping(value = "/transferMoneyFromUserToCard", method = RequestMethod.POST)
    public String transferMoneyFromUserToCard(@RequestParam("userLogin") String userLogin,
                                              @RequestParam("cardName") String cardName,
                                              @RequestParam("transferMoney") Float transferMoney, Model model) {

        System.out.println();
        System.out.println("user login: " + userLogin);
        System.out.println();
        System.out.println("Card: " + cardName);
        System.out.println();
        System.out.println("Card: " + transferMoney);




        User userDB = this.userService.findUserByLogin(new User(userLogin));
        Card cardDB = this.cardService.findCardByUseAndName(userDB, new Card(cardName));
        if (userDB.getMoney() < transferMoney) {
            model.addAttribute("notEnoughMoney", "not enough money");
            return this.transferMoneyFromUserToCardForm(userLogin, );
        }
        userDB.setMoney(userDB.getMoney() - transferMoney);
        cardDB.setMoney(cardDB.getMoney() + transferMoney);
        cardDB.setUser(userDB);
        this.cardService.save(cardDB);


        return "index";
    }

}
