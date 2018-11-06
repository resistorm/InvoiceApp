/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.application.data;

import java.util.ArrayList;




/**
 *
 * @author daan-
 */
public class InvoiceTotal {

    private Price totalExBtw;
    private Price btw;
    private Price total;






    public InvoiceTotal() {
        totalExBtw = new Price();
        btw = new Price();
        total = new Price();
    }






    public InvoiceTotal(ArrayList<Article> articleList) {
        calculate(articleList);
    }






    public Price getTotalExBtw() {
        return totalExBtw;
    }






    public Price getBtw() {
        return btw;
    }






    public Price getTotal() {
        return total;
    }






    public void setTotalExBtw(Price value) {
        totalExBtw = value;
        btw = Price.multiply(0.21, value);
        total = Price.sum(totalExBtw, btw);
    }






    public void calculate(ArrayList<Article> list) {
        if (list == null || list.isEmpty()) {
            totalExBtw = new Price();
            btw = new Price();
            total = new Price();
        } else {
            Price[] prices = new Price[list.size()];
            for (int i = 0; i < list.size(); i++) {
                prices[i] = list.get(i).getTotal();
            }
            totalExBtw = Price.sum(prices);
            btw = Price.multiply(0.21, totalExBtw);
            total = Price.sum(totalExBtw, btw);
        }
    }

}

