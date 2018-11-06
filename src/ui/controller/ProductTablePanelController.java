/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.controller;

import app.main.ApplicationManager;
import com.application.data.Article;
import com.application.data.Product;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import ui.models.ProductModel;
import ui.views.ProductTablePanel;




/**
 *
 * @author daan-
 */
public class ProductTablePanelController implements ListSelectionListener, DocumentListener, ChangeListener, ActionListener {

    private ProductModel productModel;
    private ProductTablePanel productTablePanel;






    public ProductTablePanelController() {
        productModel = new ProductModel();
        productTablePanel = new ProductTablePanel();
        productTablePanel.registerChangeListener(this);
        productTablePanel.registerDocumentListener(this);
        productTablePanel.registerListSelectionListener(this);
        productTablePanel.registerActionListener(this);
        productModel.registerObserver(productTablePanel);
    }






    public ProductModel getProductModel() {
        return productModel;
    }






    public ProductTablePanel getProductTablePanel() {
        return productTablePanel;
    }






    @Override
    public void valueChanged(ListSelectionEvent e) {
        int selectedIndex = productTablePanel.getSelectedRow();
        Product selectedProduct = productTablePanel.getProductAt(selectedIndex);
        productModel.setSelectedProduct(selectedProduct);
        productModel.notifySelectionChange();
    }






    @Override
    public void insertUpdate(DocumentEvent e) {
        String query = productTablePanel.getSearchQuery().trim().toLowerCase();
        if (query.isEmpty()) {
            productTablePanel.setRowFilter(null);
        } else {
            productTablePanel.setRowFilter(RowFilter.regexFilter(query, 1));
        }
    }






    @Override
    public void removeUpdate(DocumentEvent e) {
        String query = productTablePanel.getSearchQuery().trim().toLowerCase();
        if (query.isEmpty()) {
            productTablePanel.setRowFilter(null);
        } else {
            productTablePanel.setRowFilter(RowFilter.regexFilter(query, 1));
        }
    }






    @Override
    public void changedUpdate(DocumentEvent e) {
        System.out.println("Ignoring Change Update @ productTablePanelController");
    }






    @Override
    public void stateChanged(ChangeEvent e) {
        int amount = productTablePanel.getAmount();
        productModel.setAmount(amount);
        productModel.notifyAmountChange();
    }






    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("\t$Product product = productModel.getSelectedProduct(); ");
        Product product = productModel.getSelectedProduct();
        System.out.println("\t$int amount = productModel.getAmount(); ");
        int amount = productModel.getAmount();
        if (product != null) {
            System.out.println("\t\tProduct isnot null ");
            SwingUtilities.invokeLater(() -> {
                Article article = new Article(product, amount);
                ApplicationManager.getInstance().getProjectManager().addArticle(article);
                productTablePanel.clearSearchQuery();
            });
        } else {
            System.out.println("\t\tProduct is null ");
        }
    }

}

