/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.models;

import app.main.ApplicationManager;
import com.application.common.DataContainer;
import com.application.common.DataContainerObserver;
import com.application.data.Price;
import com.application.data.Product;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;




/**
 *
 * @author daan-
 */
public class ProductTableModel extends AbstractTableModel implements DataContainerObserver<Product> {

    private ArrayList<Product> dataList;






    public ProductTableModel() {
        super();
        dataList = ApplicationManager.getInstance().createNewProductList();
        ApplicationManager.getInstance().registerProductDataContainerObserver(this);
    }






    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }






    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Productnummer";
            case 1:
                return "Product";
            case 2:
                return "Prijs";
            case 3:
                return "Omschrijving";
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
        return 4;
    }






    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return dataList.get(rowIndex).toArray()[columnIndex];
    }






    @Override
    public void dataContainerUpdated(DataContainer<Product> dataContainer) {
        dataList = ApplicationManager.getInstance().createNewProductList();
        super.fireTableDataChanged();
    }






    public Product getProductAt(int index) {
        if (index < 0) {
            return new Product("", "", new Price(), "");
        }
        return dataList.get(index);
    }

}

