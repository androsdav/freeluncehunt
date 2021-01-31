package com.bankcard.repository;

import com.bankcard.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Interface UserRepository used for access to data base.
 * @author Didyk Andrey (androsdav@gmail.com).
 * @since 03.02.2020.
 * @version 1.0.
 */
@Repository
public interface  UserRepository extends JpaRepository<User, Integer> {

    /**
     * findByLogin - find user by login.
     * @param login - user login.
     * @return - return true or false.
     */
    User findByLogin(String login);

    @Query(value = "select u from User as u inner join fetch u.cards")
    User findByLoginAndLoadAllAndCards(@Param("login")String login);



}