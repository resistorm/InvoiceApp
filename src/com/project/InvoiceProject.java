/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project;

import com.application.data.Address;
import com.application.data.Article;
import com.application.data.Debtor;
import com.application.data.InvoiceDetails;
import com.application.data.InvoiceTotal;
import java.util.ArrayList;




/**
 *
 * @author daan-
 */
public class InvoiceProject {

    public InvoiceDetails invoiceDetails;
    public Debtor debtor;
    public ArrayList<Article> articleList;






    public InvoiceProject(InvoiceDetails invoiceDetails) {
        this.invoiceDetails = invoiceDetails;
        debtor = new Debtor("", "", new Address("", "", "", ""));
        articleList = new ArrayList<>();
    }






    public InvoiceProject(InvoiceDetails invoiceDetails, Debtor debtor) {
        this.invoiceDetails = invoiceDetails;
        this.debtor = debtor;
        this.articleList = new ArrayList<>();
    }






    public InvoiceTotal getInvoiceTotal() {
        return new InvoiceTotal(articleList);
    }

}

