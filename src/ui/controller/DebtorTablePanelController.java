/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.controller;

import app.main.ApplicationManager;
import com.application.data.Debtor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import ui.models.DebtorModel;
import ui.views.DebtorTablePanel;




/**
 *
 * @author daan-
 */
public class DebtorTablePanelController implements ListSelectionListener, DocumentListener, ActionListener {

    private DebtorTablePanel debtorTablePanel;
    private DebtorModel debtorModel;






    public DebtorTablePanelController() {
        debtorTablePanel = new DebtorTablePanel();
        debtorModel = new DebtorModel();
        debtorTablePanel.registerDocumentListener(this);
        debtorTablePanel.registerSelectionListener(this);
        debtorTablePanel.registerActionListener(this);
        debtorModel.registerObserver(debtorTablePanel);
    }






    public DebtorTablePanel getDebtorTablePanel() {
        return debtorTablePanel;
    }






    public DebtorModel getDebtorModel() {
        return debtorModel;
    }






    @Override
    public void valueChanged(ListSelectionEvent e) {
        int selectedIndex = debtorTablePanel.getSelectedRow();
        Debtor selectedDebtor = debtorTablePanel.getDebtorAt(selectedIndex);
        debtorModel.setDebtor(selectedDebtor);
        debtorModel.notifyObservers();
    }






    @Override
    public void insertUpdate(DocumentEvent e) {
        String query = debtorTablePanel.getSearchQuery().trim().toLowerCase();
        if (query.isEmpty()) {
            debtorTablePanel.setRowFilter(null);
        } else {
            debtorTablePanel.setRowFilter(RowFilter.regexFilter(query, 1));
        }
    }






    @Override
    public void removeUpdate(DocumentEvent e) {
        String query = debtorTablePanel.getSearchQuery().trim().toLowerCase();
        if (query.isEmpty()) {
            debtorTablePanel.setRowFilter(null);
        } else {
            debtorTablePanel.setRowFilter(RowFilter.regexFilter(query, 1));
        }
    }






    @Override
    public void changedUpdate(DocumentEvent e) {
        System.out.println("Ignoring ChangeUpdate for DocumentEvent ");
    }






    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("\t$Debtor debtor = debtorModel.getDebtor()");
        Debtor debtor = debtorModel.getDebtor();
        System.out.println("\t$if (debtor != null)");
        if (debtor != null) {
            System.out.println("\t$debtor is not null");
            SwingUtilities.invokeLater(() -> {
                System.out.println("\t$ApplicationManager.getInstance.getProjectManager().updateDebtor(debtor)");
                ApplicationManager.getInstance().getProjectManager().updateDebtor(debtor);
                System.out.println("\t$debtorTablePanel.clearSearchQuery();");
                debtorTablePanel.clearSearchQuery();
            });
        }
    }

}

