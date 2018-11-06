/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.models;

import java.util.ArrayList;
import ui.observers.DebtorProductModelObserver;




/**
 *
 * @author daan-
 */
public class DebtorProductModel {

    private ArrayList<DebtorProductModelObserver> observerList;
    private DebtorModel debtorModel;
    private ProductModel productModel;






    public DebtorProductModel(DebtorModel debtorModel, ProductModel productModel) {
        this.debtorModel = debtorModel;
        this.productModel = productModel;
        observerList = new ArrayList<>();
    }






    public DebtorModel getDebtorModel() {
        return debtorModel;
    }






    public ProductModel getProductModel() {
        return productModel;
    }






    public void registerObserver(DebtorProductModelObserver observer) {
        observerList.add(observer);
    }






    public void removeObserver(DebtorProductModelObserver observer) {
        observerList.remove(observer);
    }






    public void notifyAddSelectedDebtor() {
        if (!observerList.isEmpty()) {
            for (int i = 0; i < observerList.size(); i++) {
                observerList.get(i).addSelectedDebtor(debtorModel);
            }
        }
    }






    public void notifyAddSelectedProduct() {
        if (!observerList.isEmpty()) {
            for (int i = 0; i < observerList.size(); i++) {
                observerList.get(i).addSelectedProduct(productModel);
            }
        }
    }

}

