package com.andro.prolapso.shapi.models;

/**
 * Created by Maxime on 07/02/2018.
 */

public class User {

    private String name;
    private String firstName;
    private String height;
    private String weight;

    public User(String n, String fn, String h, String w){
        name = n;
        firstName = fn;
        height = h;
        weight = w;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }


}
