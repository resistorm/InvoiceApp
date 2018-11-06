/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gui.debtor;

import com.application.data.Debtor;
import com.gui.debtor.DebtorObserver.DebtorEvent;
import java.util.ArrayList;
import javax.swing.RowFilter;




/**
 *
 * @author daan-
 */
public class DebtorModel {

    private ArrayList<DebtorObserver> oList;
    private String idQuery;
    private String nameQuery;
    private RowFilter rowFilter;
    private Debtor selectedDebtor;






    public DebtorModel() {
        oList = new ArrayList<>();
        idQuery = "";
        nameQuery = "";
        rowFilter = null;
        selectedDebtor = null;
    }






    public void register(DebtorObserver observer) {
        oList.add(observer);
    }






    public void fireUpdateEvent(DebtorEvent de) {
        if (!oList.isEmpty()) {
            for (int i = 0; i < oList.size(); i++) {
                oList.get(i).modelChanged(de, this);
            }
        }
    }






    public RowFilter getRowFilter() {
        return rowFilter;
    }






    public Debtor getSelectedDebtor() {
        return selectedDebtor;
    }






    public void setSelectedDebtor(Debtor debtor) {
        selectedDebtor = debtor;
        fireUpdateEvent(DebtorEvent.SELECTION_UPDARE);
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
            ArrayList<RowFilter<Object, Object>> filters = new ArrayList<>();
            filters.add(RowFilter.regexFilter(idQuery, 0));
            filters.add(RowFilter.regexFilter(nameQuery, 1));
            rowFilter = RowFilter.andFilter(filters);
        }
        fireUpdateEvent(DebtorEvent.ROW_FILTER_UPDATE);

    }

}

