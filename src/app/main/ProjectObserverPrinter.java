/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.main;

import com.application.data.Article;
import com.application.data.InvoiceTotal;
import com.project.InvoiceProjectAccessor;
import com.project.ProjectObserver;




/**
 *
 * @author daan-
 */
public class ProjectObserverPrinter implements ProjectObserver {

    @Override
    public void debtorChanged(InvoiceProjectAccessor accessor) {
        System.out.println("Debtor Changed to " + accessor.getDebtor().getName());
    }






    @Override
    public void detailsChanged(InvoiceProjectAccessor accessor) {
        System.out.println("Details changed to " + accessor.getInvoiceDetails().getInvoiceDate() + " and invoicenumber " + accessor.getInvoiceDetails().getInvoiceNumber());
    }






    @Override
    public void articlesChanged(InvoiceProjectAccessor accessor) {
        if (accessor.getArticleList().size() <= 0) {
            System.out.println("Articles changed. The invioce is empty ");
        } else {
            System.out.println("Articles Changed. Amount of articles is " + accessor.getArticleList().size());
            for (int i = 0; i < accessor.getArticleList().size(); i++) {
                Article article = accessor.getArticleList().get(i);
                System.out.println("Article: " + article.getProduct().getProductName() + "\tAmount: " + article.getAmount() + "\tTotal: " + article.getTotal().toString());
            }
            InvoiceTotal it = new InvoiceTotal(accessor.getArticleList());
            System.out.println("\t\tTotal ex btw : " + it.getTotalExBtw().toString());
            System.out.println("\t\t         btw : " + it.getBtw().toString());
            System.out.println("\t\t       Total : " + it.getTotal().toString());
        }
    }

}

