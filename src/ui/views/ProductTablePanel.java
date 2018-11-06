/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.views;

import com.application.data.Product;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableRowSorter;
import ui.models.ProductModel;
import ui.models.ProductTableModel;
import ui.observers.ProductModelObserver;




/**
 *
 * @author daan-
 */
public class ProductTablePanel extends JPanel implements ProductModelObserver {

    private JTable table;
    private TableRowSorter<ProductTableModel> rowSorter;
    private JTextField searchField;
    private JLabel selectionDisplay;
    private JSpinner amountSpinner;
    private JButton addSelectionButton;






    public ProductTablePanel() {
        super(new BorderLayout());
        createComponents();
    }






    private void createComponents() {
        ProductTableModel ptm = new ProductTableModel();
        rowSorter = new TableRowSorter<>(ptm);
        table = new JTable(ptm);
        table.setRowSorter(rowSorter);
        table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setRowSelectionAllowed(true);
        table.setColumnSelectionAllowed(false);
        table.setRowSelectionInterval(0, 0);
        selectionDisplay = new JLabel(table.getValueAt(0, 1).toString());
        addSelectionButton = new JButton("Toevoegen");
        searchField = new JTextField(20);
        amountSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 1000, 1));
        JPanel header = new JPanel(new GridLayout(1, 4, 10, 10));
        JScrollPane scroller = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        this.add(scroller, BorderLayout.CENTER);
        header.add(searchField);
        header.add(amountSpinner);
        header.add(selectionDisplay);
        header.add(addSelectionButton);
        this.add(header, BorderLayout.NORTH);
        header.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20), BorderFactory.createEtchedBorder()));
        validate();
    }






    /**
     * Adds the change listener to the JSpinner to receive events on change
     *
     * @param listener
     */
    public void registerChangeListener(ChangeListener listener) {
        amountSpinner.addChangeListener(listener);
    }






    /**
     * Registers the document listener on the JTextField to receive Document
     * events in order to update the row filter
     *
     * @param listener
     */
    public void registerDocumentListener(DocumentListener listener) {
        searchField.getDocument().addDocumentListener(listener);
    }






    public void registerListSelectionListener(ListSelectionListener listener) {
        table.getSelectionModel().addListSelectionListener(listener);
    }






    public void registerActionListener(ActionListener listener) {
        addSelectionButton.addActionListener(listener);
    }






    public void setRowFilter(RowFilter filter) {
        SwingUtilities.invokeLater(() -> {
            rowSorter.setRowFilter(filter);
            if (rowSorter.getViewRowCount() > 0) {
                table.setRowSelectionInterval(0, 0);
            }
        });
    }






    public int getAmount() {
        return (int) amountSpinner.getValue();
    }






    public int getSelectedRow() {
        if (table.getSelectedRow() < 0) {
            return -1;
        }
        return rowSorter.convertRowIndexToModel(table.getSelectedRow());
    }






    public Product getProductAt(int index) {
        ProductTableModel model = (ProductTableModel) table.getModel();
        return model.getProductAt(index);
    }






    public String getSearchQuery() {
        return searchField.getText();
    }






    public void clearSearchQuery() {
        SwingUtilities.invokeLater(() -> {
            searchField.setText("");
        });
    }






    @Override
    public void selectionChanged(ProductModel model) {
        SwingUtilities.invokeLater(() -> {
            selectionDisplay.setText(model.getSelectedProduct().getProductName());
        });
    }






    @Override
    public void quantityChanged(ProductModel model) {
        System.out.println("Not implementing quantity change action @ productTablePanel");

    }

}

