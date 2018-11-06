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
public class Article {

    private final Product product;
    private int amount;
    private String tax;
    private Price total;






    /**
     * Creates a new Article instance with the passed product and amount. The
     * tax value is set to 'H' by default. The total price is calculated by the
     * product price and the amount
     *
     * @param product the product that is represented. All price calculations
     *                are based on the product price
     * @param amount  the order quantity, important for the total calculations
     */
    public Article(Product product, int amount) {
        this.product = product;
        this.amount = amount;
        this.tax = "H";
        if (amount <= 0) {
            this.amount = 1;
        }
        this.total = Price.product(amount, product.getProductPrice());
    }






    public String[] toStringArray() {
        return new String[]{product.getProcuctNumber(), product.getDescription(), String.valueOf(amount), tax, product.getProductPrice().toString(), total.toString()};
    }






    @Override
    public String toString() {
        return product.getProductName();
    }






    public int getAmount() {
        return amount;
    }






    /**
     * Sets the quantity to the specified amount if the passed value is greater
     * than zero. The total value is automatically updated.
     *
     * @param amount
     */
    public void setAmount(int amount) {
        if (amount > 0) {
            this.amount = amount;
            total = Price.product(amount, product.getProductPrice());
            System.out.println("Article total updated [" + amount + " * " + product.getProductPrice().toString() + " = " + total.toString());
        } else {
            System.out.println("Price amount not updated to " + amount + " because the value is invalid");
        }
    }






    public String getTax() {
        return tax;
    }






    public void setTax(String tax) {
        this.tax = tax;
    }






    public Product getProduct() {
        return product;
    }






    public Price getTotal() {
        return total;
    }






    public void recalc() {
        total = Price.product(amount, product.getProductPrice());
    }

}

