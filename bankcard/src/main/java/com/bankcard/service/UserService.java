package com.bankcard.service;

import com.bankcard.model.Role;
import com.bankcard.model.User;
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
 * Class UserService used for service logic with data base and implements UserDetailsService.
 * @author Didyk Andrey (androsdav@gmail.com).
 * @since 01.02.2021.
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
     * @return - returns user details if data base have user with inputted login.
     * @throws UsernameNotFoundException - user not found exception.
     */
    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User userDB = this.userRepository.findByLogin(login);
        if (userDB == null) {
            throw new UsernameNotFoundException("user not found");
        }
        return userDB;
    }

    /**
     * saveUser - adds user to data base with role "ROLE_USER".
     * @param user - user.
     * @return - returns true if user saved in data base and return false if user don`t saved
     * data base, because data base have user with inputted login.
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
        return true;
    }

    /**
     * updateUser - updates user params.
     * @param user - user.
     * @return - returns true if user updated in data base and return false if user don`t
     * updated data base, because data base don`t have user with inputted login.
     */
    public boolean updateUser(User user) {
        User userDB = this.userRepository.findByLogin(user.getLogin());
        if (userDB == null) {
            return false;
        }
        userDB.setName(user.getName());
        userDB.setSurname(user.getSurname());
        userDB.setMoney(user.getMoney());
        this.userRepository.save(userDB);
        return true;
    }

    /**
     * findUserByLogin - find user in data base by login.
     * @param user - user.
     * @return - returns user if data base have user with inputted user login.
     */
    public User findUserByLogin(User user) {
        User userDB = this.userRepository.findByLogin(user.getLogin());
        if (userDB == null) {
            throw new UsernameNotFoundException("user not found");
        }
        return this.userRepository.findByLogin(user.getLogin());
    }

}