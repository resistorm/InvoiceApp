/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gui.debtor;

import com.gui.all.Params;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableRowSorter;




/**
 *
 * @author daan-
 */
public class DebtorView extends JPanel implements DebtorObserver {

    private JLabel idLabel;
    private JLabel nameLabel;
    private JTextField idSearchField;
    private JTextField nameSearchField;
    private JLabel selectionLabel;
    private JLabel selectedLabel;
    private JButton commitDebtorButton;
    private JTable searchTable;
    private DebtorTableModel tableModel;
    private TableRowSorter<DebtorTableModel> rowSorter;
    private DebtorController controller;






    public DebtorView(DebtorController controller) {
        super(new BorderLayout());
        this.controller = controller;
        createSearchTable();
        createIdSearch();
        createNameSearch();
        createSelectionComponents();
        createCommitComponents();
        createAndAddTable();
        createAndAddInterface();
    }






    @Override
    public void modelChanged(DebtorEvent de, DebtorModel model) {
        if (model.getSelectedDebtor() != null) {
            selectedLabel.setText(model.getSelectedDebtor().getName());
        }
        Runnable handler;
        switch (de) {
            case DEBTOR_COMMITED:
                handler = (() -> {
                    idSearchField.setText("");
                    nameSearchField.setText("");
                });
                break;
            case ROW_FILTER_UPDATE:
                handler = (() -> {
                    rowSorter.setRowFilter(model.getRowFilter());
                    updateSelection();

                });
                break;
            case SELECTION_UPDARE:
                handler = (() -> {
                    if (model.getSelectedDebtor() != null) {
                        selectedLabel.setText(model.getSelectedDebtor().getName());
                    } else {
                        selectedLabel.setText("");
                    }
                });
            default:
                return;

        }
        SwingUtilities.invokeLater(handler);

    }






    private void createSearchTable() {
        tableModel = new DebtorTableModel();
        rowSorter = new TableRowSorter<>(tableModel);
        searchTable = new JTable(tableModel);
        searchTable.setRowSorter(rowSorter);
        searchTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                if (me.getClickCount() >= 2) {
                    controller.onDoubleClick();
                }
            }
        });
    }






    private void createIdSearch() {
        idLabel = new JLabel("Klantnr.");
        idLabel.setMinimumSize(Params.SEARCH_LABEL);
        idLabel.setPreferredSize(Params.SEARCH_LABEL);
        idSearchField = new JTextField(10);
        idSearchField.setMinimumSize(Params.SEARCH_FIELD);
        idSearchField.setPreferredSize(Params.SEARCH_FIELD);
        idSearchField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                controller.setIdQuery(idSearchField.getText());
            }






            @Override
            public void removeUpdate(DocumentEvent e) {
                controller.setIdQuery(idSearchField.getText());
            }






            @Override
            public void changedUpdate(DocumentEvent e) {
                controller.setIdQuery(idSearchField.getText());
            }
        });
        idSearchField.addActionListener((ActionEvent ae) -> {
            controller.commitIdSearch(idSearchField.getText());
        });

    }






    private void createNameSearch() {
        nameLabel = new JLabel("Klant");
        nameLabel.setMinimumSize(Params.SEARCH_LABEL);
        nameLabel.setPreferredSize(Params.SEARCH_LABEL);
        nameSearchField = new JTextField(10);
        nameSearchField.setMinimumSize(Params.SEARCH_FIELD);
        nameSearchField.setPreferredSize(Params.SEARCH_FIELD);
        nameSearchField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                controller.setNameQuery(nameSearchField.getText());
            }






            @Override
            public void removeUpdate(DocumentEvent e) {
                controller.setNameQuery(nameSearchField.getText());
            }






            @Override
            public void changedUpdate(DocumentEvent e) {
                controller.setNameQuery(nameSearchField.getText());
            }
        });
        nameSearchField.addActionListener((ActionEvent ae) -> {
            controller.commitNameSearch(nameSearchField.getText());
        });
    }






    private void createSelectionComponents() {
        selectionLabel = new JLabel("geselecteerd:");
        selectionLabel.setMinimumSize(Params.SELECTION_LABEL);
        selectionLabel.setPreferredSize(Params.SELECTION_LABEL);
        selectedLabel = new JLabel("");
        selectedLabel.setMinimumSize(Params.SELECTED_LABEL);
        selectedLabel.setPreferredSize(Params.SELECTED_LABEL);
    }






    private void createCommitComponents() {
        commitDebtorButton = new JButton("Toevoegen");
        commitDebtorButton.setMinimumSize(Params.COMMIT_PRODUCT_BUTTON);
        commitDebtorButton.addActionListener((ActionEvent ae) -> {
            controller.commitSelectedDebtor();
        });
    }






    private void createAndAddTable() {
        JScrollPane scroller = new JScrollPane(searchTable);
        searchTable.setFillsViewportHeight(true);
        searchTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        searchTable.getSelectionModel().addListSelectionListener((ListSelectionEvent lse) -> {
            controller.setSelectedDebtor(searchTable.getSelectedRow());
            changeSelection();
        });
        scroller.setPreferredSize(Params.SEARCH_TABLE);
        this.add(scroller, BorderLayout.CENTER);
    }






    private void createAndAddInterface() {
        JPanel headContainer = new JPanel(new GridLayout(2, 2, 5, 5));
        JPanel idContainer = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel nameContainer = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel selectContainer = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JPanel commitContainer = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        idContainer.add(idLabel);
        idContainer.add(idSearchField);
        nameContainer.add(nameLabel);
        nameContainer.add(nameSearchField);
        selectContainer.add(selectionLabel);
        selectContainer.add(selectedLabel);
        commitContainer.add(commitDebtorButton);
        headContainer.add(idContainer);
        headContainer.add(selectContainer);
        headContainer.add(nameContainer);
        headContainer.add(commitContainer);
        this.add(headContainer, BorderLayout.NORTH);
    }






    private void changeSelection() {
        int row = searchTable.getSelectedRow();
        if (row >= 0) {
            controller.setSelectedDebtor(rowSorter.convertRowIndexToModel(row));
        }

    }






    private void updateSelection() {
        int visibleRows = rowSorter.getViewRowCount();
        if (visibleRows > 0) {
            int index = rowSorter.convertRowIndexToModel(0);
            controller.setSelectedDebtor(index);
            searchTable.setRowSelectionInterval(0, 0);
        }
    }




    public class DebtorTableModel extends AbstractTableModel {

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            return String.class;
        }






        @Override
        public String getColumnName(int column) {
            switch (column) {
                case 0:
                    return "Klantnr.";
                case 1:
                    return "Naam";
                case 2:
                    return "Straat";
                case 3:
                    return "Postcode";
                case 4:
                    return "Woonplaats";
                default:
                    return "";

            }
        }






        @Override
        public int getRowCount() {
            return controller.getRowCount();
        }






        @Override
        public int getColumnCount() {
            return 5;
        }






        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return controller.getValueAt(rowIndex, columnIndex);
        }

    }


}

