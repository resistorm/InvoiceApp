/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.application.data;

import com.application.common.DataObject;




/**
 *
 * @author daan-
 */
public class Product implements DataObject {

    private String procuctNumber;
    private String productName;
    private Price productPrice;
    private String description;






    public Product(String productNumber, String productName, Price productPrice, String description) {
        this.procuctNumber = productNumber;
        this.productName = productName;
        this.productPrice = productPrice;
        this.description = description;
    }






    public String getProcuctNumber() {
        return procuctNumber;
    }






    public void setProcuctNumber(String procuctNumber) {
        this.procuctNumber = procuctNumber;
    }






    public String getProductName() {
        return productName;
    }






    public void setProductName(String productName) {
        this.productName = productName;
    }






    public Price getProductPrice() {
        return productPrice;
    }






    public void setProductPrice(Price productPrice) {
        this.productPrice = productPrice;
    }






    public String getDescription() {
        return description;
    }






    public void setDescription(String description) {
        this.description = description;
    }






    @Override
    public Object[] toArray() {
        return new Object[]{getProcuctNumber(), getProductName(), getProductPrice().toString(), getDescription()};
    }

}

