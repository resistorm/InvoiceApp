/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gui.all;




/**
 *
 * @author daan-
 */
public interface CatalogObserver extends Observer {

    public static final int SELECTION_CHANGED = 0;
    public static final int TABLE_UPDATE = 1;
    public static final int AMOUNT_CHANGE = 2;
    public static final int ARITCLE_ADDED = 3;






    @Override
    public void modelUpdated(int changeId, Observable observable);

}

