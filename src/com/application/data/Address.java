/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.application.data;




/**
 *
 * @author daan-
 */
public class Address {

    private String street;
    private String zipcode;
    private String city;
    private String country;






    public Address(String street, String zipcode, String city, String country) {
        this.street = street;
        this.zipcode = zipcode;
        this.city = city;
        this.country = country;
    }






    public String getStreet() {
        return street;
    }






    public void setStreet(String street) {
        this.street = street;
    }






    public String getZipcode() {
        return zipcode;
    }






    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }






    public String getCity() {
        return city;
    }






    public void setCity(String city) {
        this.city = city;
    }






    public String getCountry() {
        return country;
    }






    public void setCountry(String country) {
        this.country = country;
    }

}

