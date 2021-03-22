package com.githubrestapi.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

/**
 * Class Account repo for creates new object repo with params: id, name, html_url, description, language.
 * name, surname, money.
 * @author Didyk Andrey (androsdav@gmail.com).
 * @since 10.03.2021.
 * @version 1.0.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Repo {

    /**
     * @param id - repo id.
     */
    String id;

    /**
     * @param repo - repo name.
     */
    String name;

    /**
     * @param html_url - repo html url.
     */
    String html_url;

    /**
     * @param description - repo description.
     */
    String description;

    /**
     * @param language - repo language.
     */
    String language;


}
