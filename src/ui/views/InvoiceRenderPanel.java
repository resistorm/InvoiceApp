/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.views;

import app.main.ApplicationManager;
import com.application.output.InvoiceRenderer;
import com.project.InvoiceProjectAccessor;
import com.project.ProjectObserver;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;




/**
 *
 * @author daan-
 */
public class InvoiceRenderPanel extends JPanel implements ProjectObserver {

    private InvoiceRenderer invoiceRenderer;
    private RenderPanel renderPanel;






    public InvoiceRenderPanel() {
        super(new BorderLayout());
        setBackground(Color.GRAY);
        setOpaque(true);
        setIgnoreRepaint(true);
        invoiceRenderer = new InvoiceRenderer();
        ApplicationManager.getInstance().registerProjectObserver(this);
        renderPanel = new RenderPanel();
        add(renderPanel, BorderLayout.CENTER);
        add(new InvoiceInterfaceView(), BorderLayout.NORTH);
        add(new ProjectInterfaceView(), BorderLayout.SOUTH);
        invoiceRenderer.setInvoiceDetails(ApplicationManager.getInstance().getProjectManager().getInvoiceDetails());
    }






    @Override
    public void debtorChanged(InvoiceProjectAccessor accessor) {
        invoiceRenderer.setDebtor(accessor.getDebtor());
        repaint();
    }






    @Override
    public void detailsChanged(InvoiceProjectAccessor accessor) {
        invoiceRenderer.setInvoiceDetails(accessor.getInvoiceDetails());
        repaint();
    }






    @Override
    public void articlesChanged(InvoiceProjectAccessor accessor) {
        invoiceRenderer.setArticles(accessor.getArticleList());
        invoiceRenderer.setInvoiceTotals(accessor.getInvoiceTotal());
        repaint();
    }






    @Override
    public void invoiceChanged(InvoiceProjectAccessor accessor) {
        invoiceRenderer.setDebtor(accessor.getDebtor());
        invoiceRenderer.setInvoiceDetails(accessor.getInvoiceDetails());
        invoiceRenderer.setArticles(accessor.getArticleList());
        invoiceRenderer.setInvoiceTotals(accessor.getInvoiceTotal());
        repaint();

    }




    public class RenderPanel extends JPanel {

        public RenderPanel() {
            super();
            setPreferredSize(InvoiceRenderer.SIZE);
            setMinimumSize(InvoiceRenderer.SIZE);
            setMaximumSize(InvoiceRenderer.SIZE);
            setSize(InvoiceRenderer.SIZE);
        }






        @Override
        public void paint(Graphics graphics) {
            Graphics2D g = (Graphics2D) graphics.create();

            invoiceRenderer.renderInvoice(g, getWidth(), getHeight());
        }

    }


}

