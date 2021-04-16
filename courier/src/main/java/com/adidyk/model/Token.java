package com.adidyk.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

/**
 * Class Token used for creates new object token with params: access_token, token_type, scope.
 * @author Didyk Andrey (androsdav@gmail.com).
 * @since 18.03.2021.
 * @version 1.0.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Token {

    /**
     * @param access_token - access token.
     */
    private String access_token;

    /**
     * @param token_type - token type.
     */
    private String token_type;

    /**
     * @sparam scope - scope.
     */
    private String scope;

}