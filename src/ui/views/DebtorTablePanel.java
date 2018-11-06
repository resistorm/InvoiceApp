/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.views;

import com.application.data.Debtor;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableRowSorter;
import ui.models.DebtorModel;
import ui.models.DebtorTableModel;
import ui.observers.DebtorModelObserver;




/**
 *
 * @author daan-
 */
public class DebtorTablePanel extends JPanel implements DebtorModelObserver {

    private JTable table;
    private TableRowSorter<DebtorTableModel> rowSorter;
    private JTextField searchField;
    private JLabel selectionDisplay;
    private JButton addSelectionButton;






    public DebtorTablePanel() {
        super(new BorderLayout());
        createComponents();
    }






    private void createComponents() {
        DebtorTableModel dtm = new DebtorTableModel();
        rowSorter = new TableRowSorter<>(dtm);
        table = new JTable(dtm);
        table.setRowSorter(rowSorter);
        table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setRowSelectionAllowed(true);
        table.setColumnSelectionAllowed(false);
        table.setRowSelectionInterval(0, 0);
        selectionDisplay = new JLabel(table.getValueAt(0, 1).toString());
        addSelectionButton = new JButton("Selecteren");
        searchField = new JTextField(20);
        JScrollPane scroller = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        this.add(scroller, BorderLayout.CENTER);
        JPanel header = new JPanel(new GridLayout(1, 3, 10, 10));
        header.add(searchField);
        header.add(selectionDisplay);
        header.add(addSelectionButton);
        this.add(header, BorderLayout.NORTH);
        header.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20), BorderFactory.createEtchedBorder()));
        validate();
    }






    public void registerSelectionListener(ListSelectionListener listener) {
        table.getSelectionModel().addListSelectionListener(listener);
    }






    public void registerDocumentListener(DocumentListener listener) {
        searchField.getDocument().addDocumentListener(listener);
    }






    public void registerActionListener(ActionListener listener) {
        addSelectionButton.addActionListener(listener);
    }






    public void setRowFilter(RowFilter rowFilter) {
        SwingUtilities.invokeLater(() -> {
            rowSorter.setRowFilter(rowFilter);
            if (rowSorter.getViewRowCount() > 0) {
                table.setRowSelectionInterval(0, 0);
            }
        });
    }






    public void setSelectionDisplay(String selection) {
        SwingUtilities.invokeLater(() -> {
            selectionDisplay.setText(selection);
        });
    }






    public int getSelectedRow() {
        if (table.getSelectedRow() < 0) {
            return -1;
        }
        return rowSorter.convertRowIndexToModel(table.getSelectedRow());
    }






    public Debtor getDebtorAt(int index) {
        DebtorTableModel model = (DebtorTableModel) table.getModel();
        return model.getDebtorAt(index);
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
    public void selectionChanged(DebtorModel model) {
        SwingUtilities.invokeLater(() -> {
            selectionDisplay.setText(model.getDebtor().getName());
        });
    }

}

