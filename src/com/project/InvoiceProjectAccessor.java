/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project;

import com.application.data.Article;
import com.application.data.Debtor;
import com.application.data.InvoiceDetails;
import com.application.data.InvoiceTotal;
import java.util.ArrayList;




/**
 *
 * @author daan-
 */
public interface InvoiceProjectAccessor {

    public Debtor getDebtor();






    public InvoiceDetails getInvoiceDetails();






    public ArrayList<Article> getArticleList();






    public InvoiceTotal getInvoiceTotal();






    public InvoiceProject getInvoiceProject();

}

