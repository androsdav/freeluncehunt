package com.adidyk.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

/**
 * Class Transport used for creates new object transport with params: id, login, password, passwordConfirm,
 * name, surname, money.
 * @author Didyk Andrey (androsdav@gmail.com).
 * @since 10.02.2021.
 * @version 1.0.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Transport {

    int id;
    @NonNull
    String lot;
    @NonNull
    String year;
    @NonNull
    String make;
    @NonNull
    String model;
    @NonNull
    String item;
    @NonNull
    String location;
    @NonNull
    String lineRow;
    @NonNull
    String saleDate;
    @NonNull
    String odometer;
    @NonNull
    String docType;
    @NonNull
    String damage;
    @NonNull
    String estRetailValue;
    @NonNull
    String currentBid;
    @NonNull
    String buyItNow;
}
