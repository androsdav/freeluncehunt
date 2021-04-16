package com.adidyk.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

/**
 * Class OAuthEntity used for creates new object oAuthEntity with params: client_id, client_secret, code.
 * name, surname, money.
 * @author Didyk Andrey (androsdav@gmail.com).
 * @since 18.03.2021.
 * @version 1.0.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OAuthEntity {

    /**
     * @param client_id - client id.
     */
    String client_id;

    /**
     * @param client_secret - client secret.
     */
    String client_secret;

    /**
     * @param code - code.
     */
    String code;

}