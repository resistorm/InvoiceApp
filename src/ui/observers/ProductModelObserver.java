/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.observers;

import ui.models.ProductModel;




/**
 *
 * @author daan-
 */
public interface ProductModelObserver {

    public void selectionChanged(ProductModel model);






    public void quantityChanged(ProductModel model);

}

