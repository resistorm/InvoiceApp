/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project;




/**
 *
 * @author daan-
 */
public interface ProjectObserver {

    public void debtorChanged(InvoiceProjectAccessor accessor);






    public void detailsChanged(InvoiceProjectAccessor accessor);






    public void articlesChanged(InvoiceProjectAccessor accessor);






    public default void invoiceChanged(InvoiceProjectAccessor accessor) {
        this.articlesChanged(accessor);
        this.debtorChanged(accessor);
        this.detailsChanged(accessor);
    }
}

