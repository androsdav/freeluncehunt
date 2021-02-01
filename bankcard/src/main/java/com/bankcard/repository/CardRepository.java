package com.bankcard.repository;

import com.bankcard.model.Card;
import com.bankcard.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card, Integer> {

    List<Card> findCardByUser(User user);

    Card findCardByUserAndName(User user, String name);



}
