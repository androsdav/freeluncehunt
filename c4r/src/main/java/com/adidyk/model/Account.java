package com.adidyk.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

/**
 * Class Account used for creates new object account.
 * name, surname, money.
 * @author Didyk Andrey (androsdav@gmail.com).
 * @since 06.04.2021.
 * @version 1.0.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Account {

    /**
     * @param name - account name.
     */
    String name;

    /**
     * @param email - account email.
     */
    String email;

    String description;
}
