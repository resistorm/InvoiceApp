/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.models;

import com.application.data.Price;
import com.application.data.Product;
import java.util.ArrayList;
import ui.observers.ProductModelObserver;




/**
 *
 * @author daan-
 */
public class ProductModel {

    private ArrayList<ProductModelObserver> observerList;
    private Product selectedProduct;
    private int amount;






    public ProductModel() {
        observerList = new ArrayList<>();
        selectedProduct = new Product("", "", new Price(), "");
        amount = 1;
    }






    public Product getSelectedProduct() {
        return selectedProduct;
    }






    public int getAmount() {
        return amount;
    }






    public void setSelectedProduct(Product product) {
        selectedProduct = product;
    }






    public void setAmount(int amount) {
        this.amount = amount;
    }






    public void registerObserver(ProductModelObserver observer) {
        observerList.add(observer);
    }






    public void removeObserver(ProductModelObserver observer) {
        observerList.remove(observer);
    }






    public void notifySelectionChange() {
        if (!observerList.isEmpty()) {
            for (int i = 0; i < observerList.size(); i++) {
                observerList.get(i).selectionChanged(this);
            }
        }
    }






    public void notifyAmountChange() {
        if (!observerList.isEmpty()) {
            for (int i = 0; i < observerList.size(); i++) {
                observerList.get(i).quantityChanged(this);
            }
        }
    }

}

