/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.observers;

import ui.models.DebtorModel;
import ui.models.ProductModel;




/**
 *
 * @author daan-
 */
public interface DebtorProductModelObserver {

    public void addSelectedDebtor(DebtorModel model);






    public void addSelectedProduct(ProductModel model);

}

