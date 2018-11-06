/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gui.catalog;

import com.gui.all.Params;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableRowSorter;




/**
 *
 * @author daan-
 */
public class CatalogView extends JPanel implements CatalogObserver {

    private JLabel idSearchLabel;
    private JLabel nameSearchLabel;
    private JTextField idSearchField;
    private JTextField nameSearchField;
    private JLabel selectionLabel;
    private JLabel selectedLabel;
    private JLabel amountLabel;
    private JSpinner amountSpinner;
    private JButton commitProductButton;
    private JTable searchTable;
    private CatalogTableModel tableModel;
    private TableRowSorter<CatalogTableModel> rowSorter;
    private CatalogController catalogController;






    public CatalogView(CatalogController catalogController) {
        super();
        super.setLayout(new BorderLayout());
        this.catalogController = catalogController;
        createSearchTable();
        createIdSearch();
        createNameSearch();
        createSelectComponents();
        createAmountComponents();
        createArrowKeyControll();
        createAndAddTableScroller();
        createAndAddInterfaceComponents();
        validate();
    }






    @Override
    public void catalogChanged(CatalogEvent ce, CatalogModel model) {
        Runnable handler;
        switch (ce) {
            case AMOUNT_UPDATE:
                return;
            case ARTICLE_COMMITED:
                handler = (() -> {
                    amountSpinner.setValue(0);
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
            case SEARCH_COMMITED:
                handler = (() -> {
                    amountSpinner.setValue(0);
                    idSearchField.setText("");
                    nameSearchField.setText("");
                });
                break;
            case SEARCH_TABLE_UPDATE:
                handler = (() -> {
                    updateSelection();
                });
                break;
            case SELECTION_UPDATE:
                handler = (() -> {
                    if (model.getSelectedProduct() != null) {
                        selectedLabel.setText(model.getSelectedProduct().getProductName());
                    } else {
                        selectedLabel.setText("");
                    }

                });
                break;
            default:
                return;
        }
        SwingUtilities.invokeLater(handler);
    }






    private void updateSelection() {
        int visibleRows = rowSorter.getViewRowCount();
        if (visibleRows > 0) {
            int index = rowSorter.convertRowIndexToModel(0);
            catalogController.setSelectedProduct(index);
            searchTable.setRowSelectionInterval(0, 0);
        }
    }






    private void changeSelection() {
        int row = searchTable.getSelectedRow();
        if (row >= 0) {
            catalogController.setSelectedProduct(rowSorter.convertRowIndexToModel(row));
        }
    }






    private void createAndAddTableScroller() {
        JScrollPane scroller = new JScrollPane(searchTable);
        searchTable.setFillsViewportHeight(true);
        searchTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scroller.setPreferredSize(Params.SEARCH_TABLE);
        scroller.setMinimumSize(Params.SEARCH_TABLE);
        this.add(scroller, BorderLayout.CENTER);
    }






    private void createAndAddInterfaceComponents() {
        JPanel headContainer = new JPanel(new GridLayout(2, 2, 5, 5));
        JPanel idSearchContainer = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel nameSearchContainer = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel selectContainer = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JPanel amountContainer = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        idSearchContainer.add(idSearchLabel);
        idSearchContainer.add(idSearchField);
        nameSearchContainer.add(nameSearchLabel);
        nameSearchContainer.add(nameSearchField);
        selectContainer.add(selectionLabel);
        selectContainer.add(selectedLabel);
        amountContainer.add(amountLabel);
        amountContainer.add(amountSpinner);
        amountContainer.add(commitProductButton);
        headContainer.add(idSearchContainer);
        headContainer.add(selectContainer);
        headContainer.add(nameSearchContainer);
        headContainer.add(amountContainer);
        this.add(headContainer, BorderLayout.NORTH);
    }






    private void createSearchTable() {
        tableModel = new CatalogTableModel();
        rowSorter = new TableRowSorter<>(tableModel);
        searchTable = new JTable(tableModel);
        searchTable.setRowSorter(rowSorter);
        searchTable.getSelectionModel().addListSelectionListener((ListSelectionEvent lse) -> {
            changeSelection();
        });
        searchTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                if (me.getClickCount() >= 2) {
                    catalogController.onDoubleClick();
                }
            }
        });

    }






    private void createIdSearch() {
        idSearchLabel = new JLabel("Productnr.");
        idSearchLabel.setMinimumSize(Params.SEARCH_LABEL);
        idSearchLabel.setPreferredSize(Params.SEARCH_LABEL);
        idSearchField = new JTextField(15);
        idSearchField.setMinimumSize(Params.SEARCH_FIELD);
        idSearchField.setPreferredSize(Params.SEARCH_FIELD);
        idSearchField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                catalogController.setIdQuery(idSearchField.getText());
            }






            @Override
            public void removeUpdate(DocumentEvent e) {
                catalogController.setIdQuery(idSearchField.getText());
            }






            @Override
            public void changedUpdate(DocumentEvent e) {
                catalogController.setIdQuery(idSearchField.getText());
            }
        });
        idSearchField.addActionListener((ActionEvent ae) -> {
            catalogController.onCommitIdSearch(idSearchField.getText());
        });

    }






    private void createNameSearch() {
        nameSearchLabel = new JLabel("Product.");
        nameSearchLabel.setMinimumSize(Params.SEARCH_LABEL);
        nameSearchLabel.setPreferredSize(Params.SEARCH_LABEL);
        nameSearchField = new JTextField(15);
        nameSearchField.setMinimumSize(Params.SEARCH_FIELD);
        nameSearchField.setPreferredSize(Params.SEARCH_FIELD);
        nameSearchField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                catalogController.setNameQuery(nameSearchField.getText());
            }






            @Override
            public void removeUpdate(DocumentEvent e) {
                catalogController.setNameQuery(nameSearchField.getText());
            }






            @Override
            public void changedUpdate(DocumentEvent e) {
                catalogController.setNameQuery(nameSearchField.getText());
            }
        });
        nameSearchField.addActionListener((ActionEvent ae) -> {
            catalogController.onCommitNameSearch(nameSearchField.getText());
        });
    }






    private void createSelectComponents() {
        selectionLabel = new JLabel("Geselecteerd:");
        selectionLabel.setMinimumSize(Params.SELECTION_LABEL);
        selectionLabel.setPreferredSize(Params.SELECTION_LABEL);
        selectedLabel = new JLabel("");
        selectedLabel.setMinimumSize(Params.SELECTED_LABEL);
        selectedLabel.setPreferredSize(Params.SELECTED_LABEL);
    }






    private void createAmountComponents() {
        amountLabel = new JLabel("Aantal");
        amountLabel.setMinimumSize(Params.AMOUNT_LABEL);
        amountLabel.setPreferredSize(Params.AMOUNT_LABEL);
        amountSpinner = new JSpinner();
        amountSpinner.setMinimumSize(Params.AMOUNT_SPINNER);
        amountSpinner.setPreferredSize(Params.AMOUNT_SPINNER);
        amountSpinner.addChangeListener((ChangeEvent ce) -> {
            catalogController.setAmount((int) amountSpinner.getValue());
        });
        commitProductButton = new JButton("Toevoegen");
        commitProductButton.setMinimumSize(Params.COMMIT_PRODUCT_BUTTON);
        commitProductButton.setPreferredSize(Params.COMMIT_PRODUCT_BUTTON);
        commitProductButton.addActionListener((ActionEvent ae) -> {
            catalogController.onCommitArticle();
        });
    }






    private void createArrowKeyControll() {
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    int row = searchTable.getSelectedRow();
                    if (row >= 1 && row < rowSorter.getViewRowCount()) {
                        searchTable.setRowSelectionInterval(row - 1, row - 1);
                        catalogController.onSelectionMoveDown();
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    int row = searchTable.getSelectedRow();
                    if (row >= 0 && row < rowSorter.getViewRowCount() - 1) {
                        searchTable.setRowSelectionInterval(row + 1, row + 1);
                        catalogController.onSelectionMoveUp();
                    }
                }
            }






            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    int row = searchTable.getSelectedRow();
                    if (row >= 1 && row < rowSorter.getViewRowCount()) {
                        searchTable.setRowSelectionInterval(row - 1, row - 1);
                        catalogController.onSelectionMoveDown();
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    int row = searchTable.getSelectedRow();
                    if (row >= 0 && row < rowSorter.getViewRowCount() - 1) {
                        searchTable.setRowSelectionInterval(row + 1, row + 1);
                        catalogController.onSelectionMoveUp();
                    }
                }
            }






            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }




    public class CatalogTableModel extends AbstractTableModel {

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            return String.class;
        }






        @Override
        public String getColumnName(int column) {
            return catalogController.getColumnName(column);
        }






        @Override
        public int getRowCount() {
            return catalogController.getRowCount();
        }






        @Override
        public int getColumnCount() {
            return catalogController.getColumnCount();
        }






        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return catalogController.getValueAt(rowIndex, columnIndex);
        }
    }


}

