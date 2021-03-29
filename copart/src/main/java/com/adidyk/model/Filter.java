package com.adidyk.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

/**
 * Class Transport used for creates new object transport with params: id, login, password, passwordConfirm,
 * name, surname, money.
 * @author Didyk Andrey (androsdav@gmail.com).
 * @since 29.03.2021.
 * @version 1.0.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Filter {

    String name;
}
