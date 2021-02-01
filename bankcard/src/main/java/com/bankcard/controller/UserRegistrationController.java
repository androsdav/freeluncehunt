package com.bankcard.controller;

import com.bankcard.model.User;
import com.bankcard.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.validation.Valid;

@Controller
public class UserRegistrationController {

    /**
     * @param userController - user controller.
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
     * registration - registration.
     * @param model - model.
     * @param user - user.
     * @return - return page registration.
     */
    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model, User user) {
        model.addAttribute("user", user);
        return "registration";
    }

    /**
     * registration - registration.
     * @param user - user.
     * @param result - result.
     * @param model - model.
     * @return - returns index page.
     */
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("user") @Valid User user, BindingResult result, Model model) {
        //System.out.println("user registration:" + user);
        if (result.hasErrors()) {
           // System.out.println("result has error");
            return "registration";
        }
        if (!user.getPassword().equals(user.getPasswordConfirm())) {
            model.addAttribute("passwordError", "passwords do not match");
            //System.out.println("password do not match");
            return "registration";
        }
        if (!this.userService.saveUser(user)) {
            model.addAttribute("userLoginError", "user with same name already exists");
            //System.out.println("user with same name");
            return "registration";
        }
        model.addAttribute("userSave", "new user was added");
        return "index";
    }

}