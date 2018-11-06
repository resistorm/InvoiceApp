/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gui.catalog;

import com.application.common.BaseProxy;
import com.application.data.Article;
import com.application.data.Product;
import com.gui.invoice.InvoiceController;




/**
 *
 * @author daan-
 */
public class CatalogController {

    private InvoiceController invoiceController;
    private BaseProxy<Product> proxy;
    private CatalogModel model;
    private CatalogView view;






    public CatalogController(BaseProxy<Product> proxy, InvoiceController invoiceController) {
        this.proxy = proxy;
        this.invoiceController = invoiceController;
        model = new CatalogModel();
        view = new CatalogView(this);
        model.register(view);
    }






    public CatalogView getView() {
        return view;
    }






    public CatalogModel getModel() {
        return model;
    }






    public BaseProxy<Product> getProxy() {
        return proxy;
    }






    public void setIdQuery(String query) {
        model.setIdQuery(query);
    }






    public void setNameQuery(String query) {
        model.setNameQuery(query);
    }






    public void setSelectedProduct(int index) {
        if (index >= 0) {
            model.setSelectedProduct(proxy.get(index));
        } else {
            model.setSelectedProduct(null);
        }
    }






    public void setAmount(int index) {
        if (index > 0) {
            model.setAmount(index);
        }
    }






    public void onCommitArticle() {
        if (model.getSelectedProduct() != null) {
            invoiceController.getModel().addArticle(new Article(model.getSelectedProduct(), model.getAmount()));
        }
    }






    public void onDoubleClick() {
        onCommitArticle();
    }






    public void onCommitIdSearch(String query) {
        if (model.getSelectedProduct() != null) {
            invoiceController.getModel().addArticle(new Article(model.getSelectedProduct(), model.getAmount()));
        }

    }






    public void onCommitNameSearch(String query) {
        if (model.getSelectedProduct() != null) {
            invoiceController.getModel().addArticle(new Article(model.getSelectedProduct(), model.getAmount()));
        }
    }






    public void onSelectionMoveUp() {
        System.out.println("Selection Move Up");

    }






    public void onSelectionMoveDown() {
        System.out.println("Selection Move Down");
    }






    public String getColumnName(int index) {
        switch (index) {
            case 0:
                return "Artikelnr";
            case 1:
                return "Artikel";
            case 2:
                return "Prijs ex btw";
            case 3:
                return "Omschrijving";
            default:
                return "";
        }

    }






    public int getRowCount() {
        return proxy.size();
    }






    public int getColumnCount() {
        return 4;
    }






    public Object getValueAt(int row, int column) {
        return proxy.get(row).toArray()[column].toString();
    }

}

