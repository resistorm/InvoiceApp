/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.views;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JSplitPane;




/**
 *
 * @author daan-
 */
public class DebtorProductSplitPanel extends JPanel {

    private JSplitPane splitPane;
    private DebtorTablePanel debtorTablePanel;
    private ProductTablePanel productTablePanel;






    public DebtorProductSplitPanel(DebtorTablePanel debtorTablePanel, ProductTablePanel productTablePanel) {
        super(new BorderLayout());
        this.debtorTablePanel = debtorTablePanel;
        this.productTablePanel = productTablePanel;
        createComponents();
    }






    private void createComponents() {
        splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, debtorTablePanel, productTablePanel);
        splitPane.setDividerLocation(0.5d);
        splitPane.setDividerSize(4);
        this.add(splitPane, BorderLayout.CENTER);
        validate();
    }

}

