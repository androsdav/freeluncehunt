package com.githubrestapi.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import java.util.List;

/**
 * Class Account used for creates new object account.
 * name, surname, money.
 * @author Didyk Andrey (androsdav@gmail.com).
 * @since 10.03.2021.
 * @version 1.0.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Account {

    /**
     * @param login - account login.
     */
    String login;

    /**
     * @param id - account id.
     */
    int id;

    /**
     * @param name - account name.
     */
    String name;

    /**
     * @param company - account company.
     */
    String company;

    /**
     * @param blog - account blog.
     */
    String blog;

    /**
     * @param location - account location.
     */
    String location;

    /**
     * @param email - account email.
     */
    String email;

    /**
     * @param bio - account bio.
     */
    String bio;

    /**
     * @param twitter_username - account twitter user name.
     */
    String twitter_username;

    /**
     * @param public_repos - public repos.
     */
    int public_repos;

    /**
     * @param repos_url - repos url.
     */
    String repos_url;

    /**
     * @param repos - account repos.
     */
    List<Repo> repos;

    /**
     * @param language - account repos.
     */
    List<Language> language;

}