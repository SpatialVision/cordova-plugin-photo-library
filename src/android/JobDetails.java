package com.terikon.cordova.photolibrary;

/**
 * Created by gota on 8/03/18.
 */

public class JobDetails {
    String id;
    String address;
    String name;
    String lat;
    String lng;
    String date;
    String time;

    public JobDetails(String id, String address, String name, String lat, String lng, String date, String time) {
        this.id = id;
        this.address = address;
        this.name = name;
        this.lat = lat;
        this.lng = lng;
        this.date = date;
        this.time = time;
    }

    @Override
    public String toString() {
        return "JobDetails{" +
                "id='" + id + '\'' +
                ", address='" + address + '\'' +
                ", name='" + name + '\'' +
                ", lat='" + lat + '\'' +
                ", lng='" + lng + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
