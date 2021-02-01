package com.bankcard.service;

import com.bankcard.model.Card;
import com.bankcard.model.Role;
import com.bankcard.model.User;
import com.bankcard.repository.CardRepository;
import com.bankcard.repository.RoleRepository;
import com.bankcard.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * Class UserService.
 * @author Didyk Andrey (androsdav@gmail.com).
 * @since 03.02.2020.
 * @version 1.0.
 */
@Service
public class UserService implements UserDetailsService {

    /**
     * @param userRepository - user repository.
     */
    private UserRepository userRepository;

    /**
     * @param roleRepository - repository.
     */
    private RoleRepository roleRepository;

    /**
     * UserService - constructor.
     * @param userRepository - user repository.
     * @param roleRepository - role repository.
     */
    @Autowired
    UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    /**
     * loadUserByUserName - loads user by user name (login user).
     * @param login - user login.
     * @return - returns user details
     * @throws UsernameNotFoundException - user not found exception.
     */
    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = this.userRepository.findByLogin(login);
        if (user == null) {
            throw new UsernameNotFoundException("user not found");
        }
        return user;
    }

    /**
     * saveUser - adds user with role "ROLE_USER".
     * @param user - user.
     */
    public boolean saveUser(User user) {
        User userDB = this.userRepository.findByLogin(user.getLogin());
        if (userDB != null) {
            return false;
        }
        List<Role> roles = new ArrayList<>();
        roles.add(this.roleRepository.findByName("ROLE_USER"));
        user.setRoles(roles);
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        this.userRepository.save(user);
        System.out.println("user save: " + user);
        return true;
    }

    public boolean updateUser(User user) {
        User userDB = this.userRepository.findByLogin(user.getLogin());
        userDB.setName(user.getName());
        userDB.setSurname(user.getSurname());
        userDB.setMoney(user.getMoney());
        this.userRepository.save(userDB);
        return true;
    }

    public User findUserByLogin(User user) {
        return this.userRepository.findByLogin(user.getLogin());
    }

}