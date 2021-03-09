package com.githubrestapi.controller;

import com.githubrestapi.model.User;
import com.githubrestapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.validation.Valid;

/**
 * Class UserRegistrationController used for controller with front end.
 * @author Didyk Andrey (androsdav@gmail.com).
 * @since 01.02.2021.
 * @version 1.0.
 */
@Controller
public class UserRegistrationController {

    /**
     * @param userService - user service..
     */
    private UserService userService;

    /**
     * UserService - constructor.
     * @param userService - user service.
     */
    @Autowired
    private UserRegistrationController(UserService userService) {
        this.userService = userService;
    }

    /**
     * registration - returns: link to templates registration.html, model object user.
     * @param model - model.
     * @param user - user.
     * @return - return: link to templates registration.html, model object user.
     */
    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model, User user) {
        model.addAttribute("user", user);
        return "registration";
    }

    /**
     * registration - gets user from registration.html, adds user to data base and returns start page index.html
     * if registration don`t have 'error' or returns page registration.html if registration have 'error'.
     * @param user - user.
     * @param result - result.
     * @param model - model.
     * @return - returns start page index.html if registration don`t have 'error' or returns page registration.html
     * if registration have 'error'.
     */
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("user") @Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "registration";
        }
        if (!user.getPassword().equals(user.getPasswordConfirm())) {
            model.addAttribute("passwordError", "passwords do not match");
            return "registration";
        }
        if (!this.userService.saveUser(user)) {
            model.addAttribute("userLoginError", "user with same name already exists");
            return "registration";
        }
        return "index";
    }

}