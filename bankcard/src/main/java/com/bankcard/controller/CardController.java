package com.bankcard.controller;

import com.bankcard.model.Card;
import com.bankcard.model.User;
import com.bankcard.service.CardService;
import com.bankcard.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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

    @RequestMapping(value = "/saveCard/{login}", method = RequestMethod.GET)
    public String saveCard(@PathVariable("login") String login, Model model, Card card) {
        User userDB = this.userService.findUserByLogin(new User(login));
        model.addAttribute( "card", card);
        model.addAttribute("user", userDB);
        return "saveCard";
    }

    @RequestMapping(value = "/saveCard/{login}", method = RequestMethod.POST)
    public String saveCard(@PathVariable("login") String login, Card card, BindingResult result) {
        if (result.hasErrors()) {
            return "saveCard";
        }
        card.setUser(this.userService.findUserByLogin(new User(login)));
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
    public String transferMoneyFromUserToCard(@PathVariable("login") String login, Model model, Card card) {
        User userDB = this.userService.findUserByLogin(new User(login));
        model.addAttribute( "card", card);
        model.addAttribute("user", userDB);
        return "transferMoneyFromUserToCard";
    }

    @RequestMapping(value = "/transferMoneyFromUserToCard/", method = RequestMethod.POST)
    public String transferMoneyFromUserToCard(@RequestParam("login") String login,
                                              @RequestParam("userLogin") Card card, BindingResult result) {
        User userDB = this.userService.findUserByLogin(new User(login));
        return "transferMoneyFromUserToCard";
    }

}
