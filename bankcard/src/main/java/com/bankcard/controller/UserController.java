package com.bankcard.controller;

import com.bankcard.model.User;
import com.bankcard.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {

    /**
     * @param userController - user controller.
     */
    private UserService userService;

    /**
     * UserService - constructor.
     * @param userService - user service.
     */
    @Autowired
    UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/updateUser/{login}", method = RequestMethod.GET)
    public String updateUser(@PathVariable("login") String login, Model model) {
        UserDetails user = this.userService.loadUserByUsername(login);
        System.out.println();
        System.out.println(user);
        System.out.println();
        model.addAttribute( "user", user);
        return "updateUser";
    }

    @RequestMapping(value = "/updateUser/{login}", method = RequestMethod.POST)
    public String updateUser(@PathVariable("login") String login, User user, BindingResult result) {
        System.out.println("result:" + result.toString());
        System.out.println("user update: " + user);
        if (result.hasErrors()) {
            return "updateUser";
        }
        this.userService.updateUser(user);
        return "index";
    }

    @RequestMapping(value = "/userInfo/{login}", method = RequestMethod.GET)
    public String getUserByLogin(@PathVariable("login") String login, Model model) {
        UserDetails user = this.userService.loadUserByUsername(login);
        model.addAttribute("user", user);
        return "userInfo";
    }

}
