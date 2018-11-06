/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gui.debtor;

import com.application.common.BaseProxy;
import com.application.data.Debtor;
import com.gui.invoice.InvoiceController;




/**
 *
 * @author daan-
 */
public class DebtorController {

    private InvoiceController invoiceController;
    private BaseProxy<Debtor> proxy;
    private DebtorModel model;
    private DebtorView view;






    public DebtorController(BaseProxy<Debtor> proxy, InvoiceController invoiceController) {
        this.proxy = proxy;
        this.invoiceController = invoiceController;
        model = new DebtorModel();
        view = new DebtorView(this);
        model.register(view);
    }






    public DebtorView getView() {
        return view;
    }






    public void setIdQuery(String q) {
        model.setIdQuery(q);
    }






    public void setNameQuery(String q) {
        model.setNameQuery(q);
    }






    public void commitIdSearch(String q) {
        if (model.getSelectedDebtor() != null) {
            invoiceController.getModel().setDebtor(model.getSelectedDebtor());
        }
        model.fireUpdateEvent(DebtorObserver.DebtorEvent.DEBTOR_COMMITED);
    }






    public void commitNameSearch(String q) {
        if (model.getSelectedDebtor() != null) {
            invoiceController.getModel().setDebtor(model.getSelectedDebtor());
        }
        model.fireUpdateEvent(DebtorObserver.DebtorEvent.DEBTOR_COMMITED);
    }






    public void setSelectedDebtor(int index) {
        if (index >= 0) {
            model.setSelectedDebtor(proxy.get(index));
        } else {
            model.setSelectedDebtor(null);
        }
    }






    public void commitSelectedDebtor() {
        if (model.getSelectedDebtor() != null) {
            invoiceController.getModel().setDebtor(model.getSelectedDebtor());
        }
        model.fireUpdateEvent(DebtorObserver.DebtorEvent.DEBTOR_COMMITED);
    }






    void onDoubleClick() {
        if (model.getSelectedDebtor() != null) {
            invoiceController.getModel().setDebtor(model.getSelectedDebtor());
        }
        model.fireUpdateEvent(DebtorObserver.DebtorEvent.DEBTOR_COMMITED);
    }






    public int getRowCount() {
        return proxy.size();
    }






    public Object getValueAt(int row, int column) {
        return proxy.get(row).toArray()[column];
    }

}

