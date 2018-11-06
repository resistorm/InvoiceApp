/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gui.invoice;




/**
 *
 * @author daan-
 */
public interface InvoiceObserver {

    public static enum InvoiceEvent {
        ARTICLE_REMOVED, ARTICLE_ADDED, FIELD_UPDATE
    };






    public void modelChanged(InvoiceEvent ie, InvoiceModel model);

}

