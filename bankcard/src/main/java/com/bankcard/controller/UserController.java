package com.bankcard.controller;

import com.bankcard.model.User;
import com.bankcard.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Class UserController used for controller with front end.
 * @author Didyk Andrey (androsdav@gmail.com).
 * @since 01.02.2021.
 * @version 1.0.
 */
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

    /**
     * updateUser - searches user in data base by login, adds object user to model and returns page updateUser.html.
     * @param login - user login.
     * @param model - model.
     * @return - returns model user and returns page updateUser.html.
     */
    @RequestMapping(value = "/updateUserForm/{login}", method = RequestMethod.GET)
    public String updateUser(@PathVariable("login") String login, Model model) {
        User userDB = this.userService.findUserByLogin(new User(login));
        model.addAttribute( "user", userDB);
        return "updateUser";
    }

    /**
     * updateUser - gets user from updateUser.html, updated user in data base and returns start page index.html
     * if updateUser don`t have 'error' or returns page updateUser.html if update user have 'error'.
     * @param user - user.
     * @param result - result.
     * @return - returns start page index.html if updateUser don`t have 'error' or returns page updateUser.html
     * if update user have 'error'.
     */
    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public String updateUser(User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "updateUser";
        }
        if (!this.userService.updateUser(user)) {
            model.addAttribute("userLoginError", "user by login don`t found");
            return "updateUser";
        }
        return "index";
    }

    /**
     * getUserByLogin - searches user in data base by login, adds user to model and returns page userInfo.html.
     * @param login - user login.
     * @param model - model.
     * @return - returns page userInfo.html.
     */
    @RequestMapping(value = "/userInfo/{login}", method = RequestMethod.GET)
    public String getUserByLogin(@PathVariable("login") String login, Model model) {
        User userDB = this.userService.findUserByLogin(new User(login));
        model.addAttribute("user", userDB);
        return "userInfo";
    }

}