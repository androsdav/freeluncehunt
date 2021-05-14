package com.adidyk;

public class Haversine {

    public void haversin() {

        double latitude1 = Math.toRadians(50.4839843);
        double longitude1 = Math.toRadians(30.5350063);
        double latitude2 = Math.toRadians(50.4716034);
        double longitude2 = Math.toRadians(30.4831969);
        double radius = 6371;

        double latitudeSin = Math.sin((latitude1-latitude2)/2);
        double longitudeSin = Math.sin((longitude1-longitude2)/2);

        double distans;
        distans = 2 * radius * Math.asin(Math.sqrt(latitudeSin*latitudeSin + Math.cos(latitude1)*Math.cos(latitude2)*longitudeSin*longitudeSin));
        System.out.println("Distanse: " + distans);



    }
}
