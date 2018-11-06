/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gui.invoice;

import com.application.output.InvoiceRenderer;
import com.application.data.Article;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;




/**
 *
 * @author daan-
 */
public class InvoiceController {

    private InvoiceModel model;
    private InvoiceView view;






    public InvoiceController() {
        model = new InvoiceModel();
        view = new InvoiceView(this);
        model.register(view);
    }






    public InvoiceView getView() {
        return view;
    }






    public InvoiceModel getModel() {
        return model;
    }






    public int getComboBoxSize() {
        return model.size();
    }






    public Article getComboElement(int index) {
        return model.getRow(index);
    }






    public void setDeleteSelection(int index) {
        model.setSelectedArticle(index);
    }






    public void delete() {
        if (model.getSelectedArticle() != null) {
            model.removeArticle(model.getSelectedArticle());
        }
    }






    public void print(InvoiceRenderer renderer) {
        PrinterJob pj = PrinterJob.getPrinterJob();
        pj.setPrintable(new Printable() {
            @Override
            public int print(Graphics g, PageFormat pageFormat, int pageIndex) throws PrinterException {
                if (pageIndex > 0) {
                    return NO_SUCH_PAGE;
                }
                renderer.renderPageBackground((Graphics2D) g, pageFormat.getImageableWidth(), pageFormat.getImageableHeight());
                renderer.renderPageLabels((Graphics2D) g, pageFormat.getImageableWidth(), pageFormat.getImageableHeight());
                renderer.renderValueLabels((Graphics2D) g, pageFormat.getImageableWidth(), pageFormat.getImageableHeight());
                renderer.renderArticles((Graphics2D) g, model.size(), pageFormat.getImageableWidth(), pageFormat.getImageableHeight());
                return PAGE_EXISTS;
            }

        });

        try {
            if (pj.printDialog()) {
                pj.print();
            }
        } catch (PrinterException e) {
        }
    }

}

