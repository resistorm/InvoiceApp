/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.application.data;

import java.text.DecimalFormat;




/**
 *
 * @author daan-
 */
public class Price {

    public static final String EURO_SYMBOL = "€";
    public static final DecimalFormat PRICE_FORMAT = new DecimalFormat("###,##0.00");
    public static final int STRING_SIZE = 8;

    private double value;






    /**
     * Instantiates a new Price Object with value 00,-
     */
    public Price() {
        value = 0.0d;
    }






    /**
     * Instantiates a new Price Object with a value equal to the argument
     *
     * @param value the initial value of this instance
     */
    public Price(double value) {
        this.value = value;
    }






    /**
     * Tries to remove the euro sign from the String, if present, before
     * invoking Double.parseDouble(valueString); The statements are surrounded
     * by a try catch, and will throw an illegal argument exception when unable
     * to parse
     *
     * @param valueString the String that must be parsed into a Price object
     */
    public Price(String valueString) {
        try {
            valueString = valueString.replace(EURO_SYMBOL, "");
            this.value = Double.parseDouble(valueString);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
    }






    @Override
    public String toString() {
        return EURO_SYMBOL + String.format("%1$" + STRING_SIZE + "s", PRICE_FORMAT.format(value));
    }






    /**
     *
     * @return returns the value that this price object represents as a double
     */
    public double value() {
        return value;
    }






    /**
     * Creates a new Price object by passing the sum of the passed Price objects
     * to the constructor
     *
     * @param p1 the first price object
     * @param p2 the price object to be added to the first
     *
     * @return a new Price object representing the sum of the passed objects
     */
    public static Price sum(Price p1, Price p2) {
        double v = p1.value() + p2.value();
        Price p3 = new Price(v);
        return p3;
    }






    /**
     * Creates a new Price object by passing the sum of the passed Price objects
     * to the constructor
     *
     * @param prices the price object to be summed
     *
     * @return the total of all price objects in the array
     */
    public static Price sum(Price[] prices) {
        double v = 0.0d;
        for (int i = 0; i < prices.length; i++) {
            v += prices[i].value();
        }
        return new Price(v);
    }






    /**
     * Subtracts the second value from the first value, p1 - p2
     *
     * @param p1 the price object to subtract from
     * @param p2 the price object representing the value to subtract
     *
     * @return the subtraction of P1 - p2
     */
    public static Price subtract(Price p1, Price p2) {
        double v = p1.value() - p2.value();
        Price p3 = new Price(v);
        return p3;
    }






    public static Price multiply(double factor, Price p1) {
        double v = factor * p1.value();
        Price p2 = new Price(v);
        return p2;
    }






    public static Price product(int amount, Price price) {
        if (amount <= 1) {
            return price;
        } else {
            Price[] prices = new Price[amount];
            for (int i = 0; i < amount; i++) {
                prices[i] = price;
            }
            return sum(prices);
        }
    }

}

