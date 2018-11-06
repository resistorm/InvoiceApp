/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project;

import com.application.output.InvoiceRenderer;
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
public class ProjectPrinter implements Printable {

    private InvoiceProject invoiceProject;






    public ProjectPrinter(InvoiceProject invoiceProject) {
        this.invoiceProject = invoiceProject;
    }






    public static void printProject(InvoiceProject project) {
        ProjectPrinter pp = new ProjectPrinter(project);
        pp.printProject();
    }






    public void printProject() {
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPrintable(this);
        if (job.printDialog()) {
            try {
                job.print();
            } catch (PrinterException pe) {
                System.out.println("PRINT EXCEPTION " + pe.getMessage());
            }

        }

    }






    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        if (pageIndex > 0) {
            return NO_SUCH_PAGE;
        }
        Graphics2D g = (Graphics2D) graphics.create();
        double width = pageFormat.getImageableWidth();
        double height = pageFormat.getImageableHeight();
        InvoiceRenderer.renderInvoiceProject(invoiceProject, g, width, height);
        return PAGE_EXISTS;
    }

}

