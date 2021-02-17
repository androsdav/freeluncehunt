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
    String currentBid;
    String buyItNow;
    String startingBid;

    /**
     *
     * @param lot - lot.
     * @param year - year.
     * @param make - make.
     * @param model - model.
     * @param item - item.
     * @param location - location.
     * @param lineRow - line row.
     * @param saleDate - sale date.
     * @param odometer - odometer.
     * @param docType - doc type.
     * @param damage - damage.
     * @param estRetailValue - set retail value.
     * @param currentBid - current bid.
     * @param buyItNow - buy it now.
     * @param startingBid - starting bid.
     */
    public Transport(String lot, String year, String make, String model, String item, String location, String lineRow,
                     String saleDate, String odometer, String docType, String damage, String estRetailValue,
                     String currentBid, String buyItNow, String startingBid) {
        this.lot = lot;
        this.year = year;
        this.make = make;
        this.model = model;
        this.item = item;
        this.location = location;
        this.lineRow = lineRow;
        this.saleDate = saleDate;
        this.odometer = odometer;
        this.docType = docType;
        this.damage = damage;
        this.estRetailValue = estRetailValue;
        this.currentBid = currentBid;
        this.buyItNow = buyItNow;
        this.startingBid = startingBid;



    }
}
