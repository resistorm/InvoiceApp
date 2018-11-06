/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.controller;

import ui.models.DebtorProductModel;
import ui.views.DebtorProductSplitPanel;




/**
 *
 * @author daan-
 */
public class DebtorProductTablePanelController {

    private DebtorTablePanelController debtorTablePanelController;
    private ProductTablePanelController productTablePanelController;
    private DebtorProductSplitPanel debtorProductSplitPanel;
    private DebtorProductModel debtorProductModel;






    public DebtorProductTablePanelController(DebtorTablePanelController debtorTablePanelController, ProductTablePanelController productTablePanelController) {
        this.debtorTablePanelController = debtorTablePanelController;
        this.productTablePanelController = productTablePanelController;
        debtorProductModel = new DebtorProductModel(debtorTablePanelController.getDebtorModel(), productTablePanelController.getProductModel());
        debtorProductSplitPanel = new DebtorProductSplitPanel(debtorTablePanelController.getDebtorTablePanel(), productTablePanelController.getProductTablePanel());
    }






    public DebtorProductSplitPanel getDebtorProductSplitPanel() {
        return debtorProductSplitPanel;
    }






    public DebtorProductModel getDebtorProductModel() {
        return debtorProductModel;
    }






    public DebtorTablePanelController getDebtorTablePanelController() {
        return debtorTablePanelController;
    }






    public ProductTablePanelController getProductTablePanelController() {
        return productTablePanelController;
    }

}

