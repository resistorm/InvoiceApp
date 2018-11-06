/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gui.catalog;




/**
 *
 * @author daan-
 */
public interface CatalogObserver {

    public static enum CatalogEvent {
        SEARCH_TABLE_UPDATE,
        ROW_FILTER_UPDATE,
        SELECTION_UPDATE,
        AMOUNT_UPDATE,
        ARTICLE_COMMITED,
        SEARCH_COMMITED,
    };






    public void catalogChanged(CatalogEvent ce, CatalogModel model);
}

