/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project;

import com.application.data.Article;
import com.application.data.Debtor;
import com.application.data.InvoiceDetails;




/**
 *
 * @author daan-
 */
public interface InvoiceProjectController {

    public void registerObserver(ProjectObserver observer);






    public void removeObserver(ProjectObserver observer);






    public void updateInvoiceDetails(InvoiceDetails details);






    public void updateDebtor(Debtor debtor);






    public void addArticle(Article article);






    public void removeArticle(Article article);






    public InvoiceProject getInvoiceProject();






    public void printInvoice();

}

