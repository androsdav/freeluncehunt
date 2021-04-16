package com.adidyk.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import javax.persistence.*;

/**
 * Class Account used for creates new object account.
 * name, surname, money.
 * @author Didyk Andrey (androsdav@gmail.com).
 * @since 16.04.2021.
 * @version 1.0.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "customers")
public class Customer {

    /**
     * @param id - customer id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * @param login - user login.
     */
    @Column(name = "latitude")
    private String latitude;

    /**
     * @param password - user password.
     */
    @Column(name = "longitude")
    private String longitude;

}
