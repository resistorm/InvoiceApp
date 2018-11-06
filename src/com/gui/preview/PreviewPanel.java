/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gui.preview;

import com.application.output.InvoiceRenderer;
import com.gui.all.Params;
import com.project.InvoiceProjectAccessor;
import com.project.InvoiceProjectController;
import com.project.ProjectObserver;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;




/**
 *
 * @author daan-
 */
public class PreviewPanel extends JPanel implements ProjectObserver {

    private InvoiceRenderer renderer;






    public PreviewPanel() {
        super();
        setPreferredSize(Params.PREVIEW_SIZE);
        setMinimumSize(Params.PREVIEW_SIZE);
        renderer = new InvoiceRenderer();
    }






    public PreviewPanel(InvoiceProjectController controller) {
        super();
        setPreferredSize(Params.PREVIEW_SIZE);
        setMinimumSize(Params.PREVIEW_SIZE);
        renderer = new InvoiceRenderer(controller.getInvoiceProject());
        controller.registerObserver(this);
    }






    public void setInvoiceProject(InvoiceProjectController controller) {
        renderer = new InvoiceRenderer(controller.getInvoiceProject());
        controller.registerObserver(this);
    }






    @Override
    public void paint(Graphics graphics) {
        Graphics2D g = (Graphics2D) graphics.create();
        double width = Params.PREVIEW_SIZE.getWidth();
        double height = Params.PREVIEW_SIZE.getHeight();
        renderer.renderPageBackground(g, width, height);
        renderer.renderPageLabels(g, width, height);
        renderer.renderValueLabels(g, width, height);
        renderer.renderArticles(g, renderer.invoiceRows, width, height);
        g.dispose();
    }






    @Override
    public void debtorChanged(InvoiceProjectAccessor accessor) {
        renderer.setDebtor(accessor.getDebtor());
    }






    @Override
    public void detailsChanged(InvoiceProjectAccessor accessor) {
        renderer.setInvoiceDetails(accessor.getInvoiceDetails());
    }






    @Override
    public void articlesChanged(InvoiceProjectAccessor accessor) {
        renderer.setArticles(accessor.getArticleList());
        renderer.setInvoiceTotals(accessor.getInvoiceTotal());
    }

}

