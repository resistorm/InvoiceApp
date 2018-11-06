/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gui.invoice;

import com.application.data.Address;
import com.application.data.Article;
import com.application.data.Debtor;
import com.application.data.Price;
import com.gui.invoice.InvoiceObserver.InvoiceEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;




/**
 *
 * @author daan-
 */
public class InvoiceModel {

    public static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private ArrayList<InvoiceObserver> oList;
    private ArrayList<Article> aList;

    private Article selectedArticle;

    private int invoiceNumber;
    private String invoiceDate;
    private Debtor debtor;

    private Price totalExBtw;
    private Price btw;
    private Price total;






    public InvoiceModel() {
        oList = new ArrayList<>();
        aList = new ArrayList<>();
        totalExBtw = new Price();
        btw = new Price();
        total = new Price();
        invoiceNumber = 0;
        LocalDate localDate = LocalDate.now();
        invoiceDate = DTF.format(localDate);
        debtor = new Debtor("", "", new Address("", "", "", ""));
    }






    public void register(InvoiceObserver observer) {
        oList.add(observer);
    }






    public Article getRow(int index) {
        return aList.get(index);
    }






    public Article getSelectedArticle() {
        return selectedArticle;
    }






    public void setSelectedArticle(int index) {
        if (index >= 0 && index < size()) {
            selectedArticle = aList.get(index);
        }
    }






    public int size() {
        return aList.size();
    }






    public String[] getArticle(int index) {
        return aList.get(index).toStringArray();
    }






    public String getTotalEx() {
        return totalExBtw.toString();
    }






    public String getBtw() {
        return btw.toString();
    }






    public String getTotal() {
        return total.toString();
    }






    public Debtor getDebtor() {
        return debtor;
    }






    public int getInvoiceNumber() {
        return invoiceNumber;
    }






    public String getInvoiceDate() {
        return invoiceDate;
    }






    public void setDebtor(Debtor debtor) {
        this.debtor = debtor;
        if (!oList.isEmpty()) {
            for (int i = 0; i < oList.size(); i++) {
                oList.get(i).modelChanged(InvoiceObserver.InvoiceEvent.FIELD_UPDATE, this);
            }
        }
    }






    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
        if (!oList.isEmpty()) {
            for (int i = 0; i < oList.size(); i++) {
                oList.get(i).modelChanged(InvoiceObserver.InvoiceEvent.FIELD_UPDATE, this);
            }
        }
    }






    public void setInvoiceNumber(int invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
        if (!oList.isEmpty()) {
            for (int i = 0; i < oList.size(); i++) {
                oList.get(i).modelChanged(InvoiceObserver.InvoiceEvent.FIELD_UPDATE, this);
            }
        }
    }






    public void addArticle(Article a) {
        aList.add(a);
        updateInvoice(InvoiceEvent.ARTICLE_ADDED);
    }






    public void removeArticle(int index) {
        aList.remove(index);
        updateInvoice(InvoiceEvent.ARTICLE_REMOVED);
    }






    public void removeArticle(Article a) {
        aList.remove(a);
        updateInvoice(InvoiceEvent.ARTICLE_REMOVED);
    }






    public void updateInvoice(InvoiceObserver.InvoiceEvent ie) {
        if (aList.isEmpty()) {
            totalExBtw = new Price();
            btw = new Price();
            total = new Price();
        } else {
            Price[] prices = new Price[aList.size()];
            for (int i = 0; i < aList.size(); i++) {
                prices[i] = aList.get(i).getTotal();
            }
            totalExBtw = Price.sum(prices);
            btw = Price.multiply(0.21, totalExBtw);
            total = Price.sum(totalExBtw, btw);
        }
        if (!oList.isEmpty()) {
            for (int i = 0; i < oList.size(); i++) {
                oList.get(i).modelChanged(ie, this);
            }
        }

    }
}

