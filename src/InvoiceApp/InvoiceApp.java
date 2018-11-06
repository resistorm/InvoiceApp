/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InvoiceApp;

import java.awt.FlowLayout;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import ui.controller.DebtorProductTablePanelController;
import ui.controller.DebtorTablePanelController;
import ui.controller.ProductTablePanelController;
import ui.views.DebtorProductSplitPanel;
import ui.views.InvoiceRenderPanel;




/**
 *
 * @author daan-
 */
public class InvoiceApp {

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        DebtorProductSplitPanel dpsp = buildDebtorProductTablePanelController().getDebtorProductSplitPanel();
        InvoiceRenderPanel irp = buildInvoiceRenderPanel();
        frame.getContentPane().setLayout(new FlowLayout());
        frame.getContentPane().add(dpsp);
        frame.getContentPane().add(irp);
        frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        frame.setLocationRelativeTo(null);
        SwingUtilities.invokeLater(() -> {
            frame.setVisible(true);
        });

    }






    public static DebtorProductTablePanelController buildDebtorProductTablePanelController() {
        DebtorTablePanelController debtorTablePanelController = new DebtorTablePanelController();
        ProductTablePanelController productTablePanelController = new ProductTablePanelController();
        DebtorProductTablePanelController debtorProductTablePanelController = new DebtorProductTablePanelController(debtorTablePanelController, productTablePanelController);
        return debtorProductTablePanelController;
    }






    public static InvoiceRenderPanel buildInvoiceRenderPanel() {
        return new InvoiceRenderPanel();
    }

}

