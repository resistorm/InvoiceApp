/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.views;

import app.main.ApplicationManager;
import com.project.InvoiceProject;
import com.project.ProjectPrinter;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;




/**
 *
 * @author daan-
 */
public class ProjectInterfaceView extends JPanel {

    private JButton printButton;
    private JButton clearButton;






    public ProjectInterfaceView() {
        super(new FlowLayout(FlowLayout.RIGHT));
        printButton = new JButton("Afdrukken");
        printButton.addActionListener((ActionEvent ae) -> {
            InvoiceProject ip = ApplicationManager.getInstance().getProjectManager().getInvoiceProject();
            ApplicationManager.getInstance().incrementInvoiceCounter();
            ProjectPrinter.printProject(ip);

        });
        clearButton = new JButton("Nieuw Project");
        clearButton.addActionListener((ActionEvent ae) -> {
            ApplicationManager.getInstance().createNewProject();
        });
        add(printButton);
        add(clearButton);
        setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20), BorderFactory.createEtchedBorder()));
        validate();

    }

}

