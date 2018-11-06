/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gui.debtor;




/**
 *
 * @author daan-
 */
public interface DebtorObserver {

    public static enum DebtorEvent {
        ROW_FILTER_UPDATE,
        SELECTION_UPDARE,
        DEBTOR_COMMITED
    };






    public void modelChanged(DebtorEvent de, DebtorModel model);

}

