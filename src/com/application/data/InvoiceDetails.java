/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.application.data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;




/**
 *
 * @author daan-
 */
public class InvoiceDetails {

    public static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private int invoiceNumber;
    private LocalDate invoiceDate;






    public InvoiceDetails(int invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
        this.invoiceDate = LocalDate.now();
    }






    public int getInvoiceNumber() {
        return invoiceNumber;
    }






    public void setInvoiceNumber(int invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }






    public String getInvoiceDate() {
        return DTF.format(invoiceDate);
    }






    public LocalDate getLocalInvoiceDate() {
        return invoiceDate;
    }






    public void setInvoiceDate(LocalDate invoiceDate) {
        this.invoiceDate = invoiceDate;
    }






    public void addToDate(int days, int months, int years) {
        invoiceDate = invoiceDate.plusYears(years);
        invoiceDate = invoiceDate.plusMonths(months);
        invoiceDate = invoiceDate.plusDays(days);
    }






    public void subtractFromDate(int days, int months, int years) {
        invoiceDate = invoiceDate.minusYears(years);
        invoiceDate = invoiceDate.minusMonths(months);
        invoiceDate = invoiceDate.minusDays(days);
    }

}

