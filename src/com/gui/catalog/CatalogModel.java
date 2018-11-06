/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gui.catalog;

import com.application.data.Product;
import com.gui.catalog.CatalogObserver.CatalogEvent;
import java.util.ArrayList;
import javax.swing.RowFilter;




/**
 *
 * @author daan-
 */
public class CatalogModel {

    private ArrayList<CatalogObserver> oList;
    private int amount;
    private Product selectedProduct;
    private String idQuery;
    private String nameQuery;
    private RowFilter rowFilter;






    public CatalogModel() {
        oList = new ArrayList<>();
        amount = 1;
        selectedProduct = null;
        idQuery = "";
        nameQuery = "";
        rowFilter = null;
    }






    public void register(CatalogObserver observer) {
        oList.add(observer);
    }






    public void fireUpdateEvent(CatalogEvent ce) {
        if (!oList.isEmpty()) {
            for (int i = 0; i < oList.size(); i++) {
                oList.get(i).catalogChanged(ce, this);
            }
        }
    }






    public int getAmount() {
        return amount;
    }






    public Product getSelectedProduct() {
        return selectedProduct;
    }






    public RowFilter getRowFilter() {
        return rowFilter;
    }






    public void setAmount(int amount) {
        this.amount = amount;
        fireUpdateEvent(CatalogEvent.AMOUNT_UPDATE);
    }






    public void setSelectedProduct(Product product) {
        selectedProduct = product;
        fireUpdateEvent(CatalogEvent.SELECTION_UPDATE);
    }






    public void setIdQuery(String q) {
        idQuery = q;
        updateRowFilter();
    }






    public void setNameQuery(String q) {
        nameQuery = q;
        updateRowFilter();
    }






    private void updateRowFilter() {
        if (idQuery.length() <= 0 && nameQuery.length() <= 0) {
            rowFilter = null;
        } else if (idQuery.length() > 0 && nameQuery.length() <= 0) {
            rowFilter = RowFilter.regexFilter(idQuery, 0);
        } else if (idQuery.length() <= 0 && nameQuery.length() > 0) {
            rowFilter = RowFilter.regexFilter(nameQuery, 1);
        } else {
            ArrayList<RowFilter<Object, Object>> filter = new ArrayList<>();
            filter.add(RowFilter.regexFilter(idQuery, 0));
            filter.add(RowFilter.regexFilter(nameQuery, 1));
            rowFilter = RowFilter.andFilter(filter);
        }
        fireUpdateEvent(CatalogEvent.ROW_FILTER_UPDATE);
    }

}

