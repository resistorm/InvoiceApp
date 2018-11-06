/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gui.model;

import com.application.data.Address;
import com.application.data.Article;
import com.application.data.Debtor;
import com.application.data.Price;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;




/**
 *
 * @author daan-
 */
public class Invoice {

    public static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private int invoiceNumber;
    private String invoiceDate;
    private Debtor debtor;
    private Bill bill;






    public Invoice() {
        invoiceNumber = 0;
        LocalDate localDate = LocalDate.now();
        invoiceDate = DTF.format(localDate);
        debtor = new Debtor("", "", new Address("", "", "", ""));
        bill = new Bill();
    }






    public Invoice(int invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
        LocalDate localDate = LocalDate.now();
        invoiceDate = DTF.format(localDate);
        debtor = new Debtor("", "", new Address("", "", "", ""));
        bill = new Bill();
    }






    public void setInvoiceNumber(int invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }






    public int getInvoiceNumber() {
        return invoiceNumber;
    }






    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }






    public String getInvoiceDate() {
        return invoiceDate;
    }






    public void setDebtor(Debtor debtor) {
        this.debtor = debtor;
    }






    public Debtor getDebtor() {
        return debtor;
    }






    public Article getArticleAt(int index) {
        return bill.getArticle(index);
    }






    public void addArticle(Article article) {
        bill.addToList(article);
    }






    public int articleCount() {
        return bill.size();
    }






    public void removeArticle(int index) {
        bill.removeFromList(index);
    }




    public class Bill {

        private ArrayList<Article> articleList;

        private Price totalExBtw;
        private Price btw;
        private Price total;






        public Bill() {
            articleList = new ArrayList<>();
            totalExBtw = new Price();
            btw = new Price();
            total = new Price();
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






        public int size() {
            return articleList.size();
        }






        public boolean isEmpty() {
            return articleList.isEmpty();
        }






        public Article getArticle(int index) {
            return articleList.get(index);
        }






        public void removeFromList(int index) {
            articleList.remove(index);
            recalculate();
        }






        public void addToList(Article article) {
            articleList.add(article);
            recalculate();

        }






        private void recalculate() {
            if (articleList.isEmpty()) {
                totalExBtw = new Price();
                btw = new Price();
                total = new Price();
            } else {
                Price[] prices = new Price[articleList.size()];
                for (int i = 0; i < articleList.size(); i++) {
                    prices[i] = articleList.get(i).getTotal();
                }
                totalExBtw = Price.sum(prices);
                btw = Price.multiply(0.21d, totalExBtw);
                total = Price.sum(totalExBtw, btw);
            }
        }

    }


}

