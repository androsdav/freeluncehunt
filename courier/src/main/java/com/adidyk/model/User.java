package com.adidyk.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.*;

/**
 * Class User used for creates new object user with params: id, login, password, passwordConfirm,
 * name, surname, money.
 * @author Didyk Andrey (androsdav@gmail.com).
 * @since 01.02.2021.
 * @version 1.0.
 */
@Entity
@Table(name = "users")
public class User implements UserDetails {

    /**
     * @param id - user id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * @param login - user login.
     */
    @Column(name = "login")
    @Size(min = 3, message = "login must contain at least 3 characters")
    private String login;

    /**
     * @param password - user password.
     */
    @Column(name = "password")
    @Size(min = 3, message = "password must contain at least 3 characters")
    private String password;

    /**
     * @param passwordConfirm - user password confirm.
     */
    @Transient
    private String passwordConfirm;

    /**
     * @param name - user name.
     */
    @Column(name = "name", columnDefinition = "default 'name'")
    private String name;

    /**
     * @param surname - user surname.
     */
    @Column(name = "surname", columnDefinition = "default 'surname")
    private String surname;

    /**
     * @param money - user money.
     */
    @Column(name = "money", columnDefinition = "default 0")
    private Float money;

    /**
     * @param roles - user roles.
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name="users_roles",
            joinColumns={@JoinColumn(name="user_id", referencedColumnName="id")},
            inverseJoinColumns={@JoinColumn(name="role_id", referencedColumnName="id")})
    private List<Role> roles = new ArrayList<>();

    /**
     * User - constructor.
     */
    public User() {
    }

    /**
     * User - constructor.
     * @param id - user id.
     * @param login - user login.
     * @param password - user password.
     */
    public User(int id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
    }

    /**
     * User - constructor.
     * @param id - user id.
     */
    public User(int id) {
        this.id = id;
    }

    /**
     * User - constructor.
     * @param login - user login.
     * @param password - user password.
     */
    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    /**
     * User - constructor.
     * @param login - user login.
     */
    public User(String login) {
        this.login = login;
    }

    /**
     * getId - returns user id.
     * @return - returns user id.
     */
    public int getId() {
        return id;
    }

    /**
     * setId - sets user id.
     * @param id - user id.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * getLogin - returns user login.
     * @return - returns user login.
     */
    public String getLogin() {
        return login;
    }

    /**
     * setLogin - sets user login.
     * @param login - user login.
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * getPassword - returns user password.
     * @return - returns user password.
     */
    @Override
    public String getPassword() {
        return this.password;
    }

    /**
     * setPassword - sets user password.
     * @param password - user password.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * getPasswordConfirm - gets password confirm.
     * @return - returns password confirm.
     */
    public String getPasswordConfirm() {
        return this.passwordConfirm;
    }

    /**
     *  setPasswordConfirm - sets password confirm.
     * @param passwordConfirm - user password confirm.
     */
    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    /**
     * getName - returns user name.
     * @return - returns user name.
     */
    public String getName() {
        return name;
    }

    /**
     * setName - sets user name.
     * @param name - user name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * getSurName - returns user surname.
     * @return - returns user surname.
     */
    public String getSurname() {
        return surname;
    }

    /**
     * setSurname - sets user surname.
     * @param surname - user surname.
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * getMoney - returns user money.
     * @return - returns user money.
     */
    public Float getMoney() {
        return money;
    }

    /**
     * setMoney - sets user money.
     * @param money - user money.
     */
    public void setMoney(Float money) {
        this.money = money;
    }

    /**
     * getRoles - gets set roles.
     * @return - returns set roles.
     */
    public List<Role> getRoles() {
        return this.roles;
    }

    /**
     * setRole - sets role.
     * @param roles - user roles.
     */
    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    /**
     * getAuthorities - returns all roles.
     * @return  - returns all roles.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.getRoles();
    }

    /**
     * getUserName - returns username (login user).
     * @return - returns username (login user).
     */
    @Override
    public String getUsername() {
        return this.getLogin();
    }

    /**
     * isAccountNonExpired - is account non expired.
     * @return - returns true.
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * isAccountNonLocked - is account non locked.
     * @return - returns true.
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * isCredentialsNonExpired - is credential non expired.
     * @return - returns true.
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * isEnabled - is enabled.
     * @return - true.
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

    /**
     * equals - returns boolean result.
     * @param o - object of class Object.
     * @return - returns "true" if id, login, password, list role, cards of user is same, and returns "false" - isn`t same.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return id == user.id &&
                Objects.equals(login, user.login) &&
                Objects.equals(password, user.password) &&
                Objects.equals(passwordConfirm, user.passwordConfirm) &&
                Objects.equals(name, user.name) &&
                Objects.equals(surname, user.surname) &&
                Objects.equals(money, user.money) &&
                Objects.equals(roles, user.roles);
    }

    /**
     * hashCode - returns hash code for user.
     * @return - returns hash code for user.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, passwordConfirm, name, surname, money, roles);
    }

    /**
     * toString - returns string format.
     * @return - returns all information for user.
     */
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", passwordConfirm='" + passwordConfirm + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", money='" + money + '\'' +
                ", roles=" + roles +
                '}';
    }

}