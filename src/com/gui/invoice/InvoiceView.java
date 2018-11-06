/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gui.invoice;

import com.application.output.InvoiceRenderer;
import com.application.data.Article;
import com.gui.all.Params;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;




/**
 *
 * @author daan-
 */
public class InvoiceView extends JPanel implements InvoiceObserver {

    private InvoiceRenderer renderer;
    private InvoiceRenderPanel renderPanel;
    private JComboBox comboBox;
    private JButton deleteButton;
    private JButton printButton;
    private int rowCount;

    private InvoiceController controller;






    public InvoiceView(InvoiceController controller) {
        super(new BorderLayout());
        this.controller = controller;
        renderer = new InvoiceRenderer();
        renderPanel = new InvoiceRenderPanel();
        createComboBox();
        createDeleteButton();
        createPrintButton();
        installDeleteInterface();
        installRenderView();
        installPrintInterface();
    }






    private void createComboBox() {
        comboBox = new JComboBox(new DefaultComboBoxModel<Article>() {

            @Override
            public int getSize() {
                return controller.getComboBoxSize();
            }






            @Override
            public Article getElementAt(int index) {
                return controller.getComboElement(index);
            }

        });
        comboBox.setPreferredSize(Params.SEARCH_FIELD);
        comboBox.addActionListener((ActionEvent ae) -> {
            controller.setDeleteSelection(comboBox.getSelectedIndex());
        });

    }






    private void createDeleteButton() {
        deleteButton = new JButton("Verwijderen");
        deleteButton.setPreferredSize(Params.COMMIT_PRODUCT_BUTTON);
        deleteButton.addActionListener((ActionEvent ae) -> {
            controller.delete();
        });
    }






    private void createPrintButton() {
        printButton = new JButton("Printen");
        printButton.setPreferredSize(Params.COMMIT_PRODUCT_BUTTON);
        printButton.addActionListener((ActionEvent ae) -> {
            controller.print(renderer);
        });
    }






    private void installDeleteInterface() {
        JPanel container = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        container.add(printButton);
        this.add(container, BorderLayout.SOUTH);
    }






    private void installRenderView() {
        this.add(renderPanel, BorderLayout.CENTER);
    }






    private void installPrintInterface() {
        JPanel container = new JPanel(new FlowLayout(FlowLayout.LEFT));
        container.add(comboBox);
        container.add(deleteButton);
        this.add(container, BorderLayout.NORTH);
    }






    @Override
    public void modelChanged(InvoiceEvent ie, InvoiceModel model) {
        renderer.modelChanged(ie, model);
        rowCount = model.size();
        switch (ie) {
            case ARTICLE_ADDED:
                comboBox.addItem(model.getArticle(model.size() - 1));
                break;
            case ARTICLE_REMOVED:
                comboBox.validate();

                break;
            case FIELD_UPDATE:

                break;
        }

        repaint();
    }




    public class InvoiceRenderPanel extends JPanel {

        public InvoiceRenderPanel() {
            super();
            setLayout(null);
            Dimension size = InvoiceRenderer.SIZE;
            size.width += 20;
            size.height += 20;
            setSize(size);
            setPreferredSize(size);
            setMinimumSize(InvoiceRenderer.SIZE);

        }






        @Override
        public void paint(Graphics g) {
            g.setColor(Color.LIGHT_GRAY);
            g.fillRect(0, 0, getWidth(), getHeight());
            renderer.renderPageBackground((Graphics2D) g);
            renderer.renderPageLabels((Graphics2D) g);
            renderer.renderValueLabels((Graphics2D) g);
            renderer.renderArticles((Graphics2D) g, rowCount);

        }

    }


}

