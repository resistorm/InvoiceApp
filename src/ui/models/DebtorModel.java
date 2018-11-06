/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.models;

import com.application.data.Address;
import com.application.data.Debtor;
import java.util.ArrayList;
import ui.observers.DebtorModelObserver;




/**
 *
 * @author daan-
 */
public class DebtorModel {

    private ArrayList<DebtorModelObserver> observerList;
    private Debtor debtor;






    public DebtorModel() {
        debtor = new Debtor("", "", new Address("", "", "", ""));
        observerList = new ArrayList<>();
    }






    public void setDebtor(Debtor debtor) {
        if (debtor != null) {
            this.debtor = debtor;
        }
    }






    public Debtor getDebtor() {
        return debtor;
    }






    public void registerObserver(DebtorModelObserver observer) {
        observerList.add(observer);
    }






    public void removeObserver(DebtorModelObserver observer) {
        observerList.remove(observer);
    }






    public void notifyObservers() {
        if (!observerList.isEmpty()) {
            for (int i = 0; i < observerList.size(); i++) {
                observerList.get(i).selectionChanged(this);
            }
        }
    }
}

