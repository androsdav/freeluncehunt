package com.adidyk.repository;

import com.adidyk.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface UserRepository used for access to data base.
 * @author Didyk Andrey (androsdav@gmail.com).
 * @since 01.02.2021.
 * @version 1.0.
 */
@Repository
public interface  UserRepository extends JpaRepository<User, Integer> {

    /**
     * findByLogin - find user by login.
     * @param login - user login.
     * @return - return user.
     */
    User findByLogin(String login);

}