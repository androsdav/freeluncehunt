package com.githubrestapi.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

/**
 * Class Language used for creates new object language.
 * name, surname, money.
 * @author Didyk Andrey (androsdav@gmail.com).
 * @since 10.03.2021.
 * @version 1.0.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Language {

    /**
     * @param name - language name.
     */
    String name;

    /**
     * @param value - value.
     */
    Double value;

}