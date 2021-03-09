package com.githubrestapi.model;

import javax.persistence.*;
import java.util.Objects;

/**
 * Class Card card for creates new object card with params: id, name, money, user.
 * @author Didyk Andrey (androsdav@gmail.com).
 * @since 01.02.2021.
 * @version 1.0.
 */
@Entity
@Table(name = "cards")
public class Card {

    /**
     * @param id - card id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * @param login - card login.
     */
    @Column(name = "name")
    private String name;

    /**
     * @param money - card money.
     */
    @Column(name = "money", columnDefinition = "default 0")
    private Float money;

    /**
     * @param user - user.
     */
    @ManyToOne(optional = false)
    @JoinColumn(name="user_id")
    private User user;

    /**
     * Card - constructor.
     */
    public Card() {
    }

    /**
     * Card - constructor.
     * @param name - card name.
     * @param money - card money.
     * @param user - user.
     */
    public Card(String name, Float money, User user) {
        this.name = name;
        this.money = money;
        this.user = user;
    }

    /**
     * Card - constructor.
     * @param name - card name.
     */
    public Card(String name) {
        this.name = name;
    }

    /**
     * getId - returns card id.
     * @return - returns card id.
     */
    public int getId() {
        return id;
    }

    /**
     * setId - sets id.
     * @param id - card id.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * getName - returns card name.
     * @return - returns card name.
     */
    public String getName() {
        return name;
    }

    /**
     * setName - sets name.
     * @param name - card name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * getMoney - returns card money.
     * @return - returns card money.
     */
    public Float getMoney() {
        return money;
    }

    /**
     * setMoney - sets money.
     * @param money - card money.
     */
    public void setMoney(Float money) {
        this.money = money;
    }

    /**
     * getUser - returns user.
     * @return - returns user.
     */
    public User getUser() {
        return user;
    }

    /**
     * setUser - sets user.
     * @param user - user.
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * equals - returns boolean result.
     * @param o - object of class Object.
     * @return - returns "true" if id, name, money, user of card is same, and returns "false" - isn`t same.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Card)) return false;
        Card card = (Card) o;
        return id == card.id &&
                Objects.equals(name, card.name) &&
                Objects.equals(money, card.money)&&
                Objects.equals(user, card.user);
    }

    /**
     * hashCode - returns hash code for card.
     * @return - returns hash code for card.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, name, money, user);
    }

    /**
     * toString - returns string format.
     * @return - returns all information for card.
     */
    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", money=" + money +
                ", user=" + user +
                '}';
    }

}