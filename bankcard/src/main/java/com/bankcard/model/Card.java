package com.bankcard.model;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Objects;

/**
 * Class User used for creates new object user with params: id, login, password.
 * @author Didyk Andrey (androsdav@gmail.com).
 * @since 27.01.2020.
 * @version 1.0.
 */
@Entity
@Table(name = "cards")
public class Card {

    /**
     * @param id - user id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * @param login - user login.
     */
    @Column(name = "name")
    //@NotBlank(message = "login is mandatory")
    @Size(min = 3, message = "login must contain at least 3 characters")
    private String name;

    /**
     * @param money - user money.
     */
    @Column(name = "money", columnDefinition = "default 0")
    private Float money;

    @ManyToOne(optional = false)
    @JoinColumn(name="user_id")
    private User user;

    public Card() {
    }

    public Card(String name, Float money, User user) {
        this.name = name;
        this.money = money;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getMoney() {
        return money;
    }

    public void setMoney(Float money) {
        this.money = money;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

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

    @Override
    public int hashCode() {
        return Objects.hash(id, name, money, user);
    }

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