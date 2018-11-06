/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.models;

import app.main.ApplicationManager;
import com.application.common.DataContainer;
import com.application.common.DataContainerObserver;
import com.application.data.Debtor;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;




/**
 *
 * @author daan-
 */
public class DebtorTableModel extends AbstractTableModel implements DataContainerObserver<Debtor> {

    private ArrayList<Debtor> dataList;






    public DebtorTableModel() {
        super();
        dataList = ApplicationManager.getInstance().createNewDebtorList();
        ApplicationManager.getInstance().registerDebtorDataContainerObserver(this);
    }






    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }






    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Debiteurennummer";
            case 1:
                return "Debiteur";
            case 2:
                return "Straat";
            case 3:
                return "Postcode";
            case 4:
                return "Woonplaats";
            case 5:
                return "Land";
            default:
                return "";

        }
    }






    @Override
    public int getRowCount() {
        return dataList.size();
    }






    @Override
    public int getColumnCount() {
        return 5;
    }






    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return dataList.get(rowIndex).toArray()[columnIndex];
    }






    @Override
    public void dataContainerUpdated(DataContainer<Debtor> dataContainer) {
        dataList = ApplicationManager.getInstance().createNewDebtorList();
        this.fireTableDataChanged();
    }






    public Debtor getDebtorAt(int index) {
        if (index < 0) {
            return Debtor.createEmptyDebtor();
        }
        return dataList.get(index);
    }
}

