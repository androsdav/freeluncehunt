package com.githubrestapi.repository;

import com.githubrestapi.model.Card;
import com.githubrestapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Interface CardRepository used for access to data base.
 * @author Didyk Andrey (androsdav@gmail.com).
 * @since 01.02.2021.
 * @version 1.0.
 */
@Repository
public interface CardRepository extends JpaRepository<Card, Integer> {

    /**
     * findCardByUser - finds all cards fy user and returns list of cards.
     * @param user - user.
     * @return - returns list of cards.
     */
    List<Card> findCardByUser(User user);

    /**
     * findCardByUserAndName - find card by user and card name and returns card.
     * @param user - user.
     * @param name - card name.
     * @return - returns card.
     */
    Card findCardByUserAndName(User user, String name);



}
