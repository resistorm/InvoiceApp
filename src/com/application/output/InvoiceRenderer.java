/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.application.output;

import com.application.common.ImageLoader;
import com.application.common.RobotoFont;
import com.application.data.Article;
import com.application.data.Debtor;
import com.application.data.InvoiceDetails;
import com.application.data.InvoiceTotal;
import com.application.data.Label;
import com.gui.invoice.InvoiceModel;
import com.gui.invoice.InvoiceObserver;
import com.project.InvoiceProject;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.ArrayList;




/**
 *
 * @author daan-
 */
public class InvoiceRenderer implements InvoiceObserver {

    public static final Dimension SIZE = new Dimension(596, 842);
    public static final Font DIGIT = new Font("Monospaced", Font.PLAIN, 10);
    public static final Font THIN = RobotoFont.robotoThin(11.0f);
    public static final Font REGULAR = RobotoFont.robotoRegular(10.0f);
    public static final Font MEDIUM = RobotoFont.robotoMedium(11.0f);
    public static final Font BOLD = RobotoFont.robotoBold(11.0f);

    public static Label[] LABELS = new Label[]{
        new Label(BOLD.deriveFont(22f), "FACTUUR", 284, 227),
        new Label(MEDIUM, "Factuurnummer", 284, 261),
        new Label(MEDIUM, "Debiteurennummer", 284, 275),
        new Label(MEDIUM, "Factuurdatum", 284, 289),
        new Label(MEDIUM, "Artikelnr", 23, 329),
        new Label(MEDIUM, "Omschrijving", 95, 329),
        new Label(MEDIUM, "Aantal", 338, 329),
        new Label(MEDIUM, "BTW", 380, 329),
        new Label(MEDIUM, "Excl. BTW", 417, 329),
        new Label(MEDIUM, "Tot. excl. BTW", 488, 329),
        new Label(MEDIUM, "Totaal exclusief BTW", 284, 741),
        new Label(MEDIUM, "BTW 21%", 284, 756),
        new Label(MEDIUM, "TOTAAL", 284, 770),
        new Label(REGULAR, "Betalen binnen 14 dagen", 23, 756),
        new Label(REGULAR, "Foolaan 42", 500, 88),
        new Label(REGULAR, "1234 FO 's-Hertogenbosch", 446, 102),
        new Label(REGULAR, "+31 6 12 34 56 78", 491, 116),
        new Label(REGULAR, "NL00 BANK 0123 4567 89", 448, 130),
        new Label(REGULAR, "BTW-nr: 9876.54.321.B03", 453, 144),
        new Label(REGULAR, "K.v.k nr.: 15264859", 486, 158)
    };

    private BufferedImage bgImage = ImageLoader.loadImage(ImageLoader.PAGE_IMAGE);
    private Label nameLabel = new Label(MEDIUM, "", 79, 164);
    private Label streetLabel = new Label(MEDIUM, "", 79, 179);
    private Label zipcodeLabel = new Label(MEDIUM, "", 79, 193);
    private Label cityLabel = new Label(MEDIUM, "", 130, 193);

    private Label invoiceNumberLabel = new Label(MEDIUM, "", 400, 261);
    private Label debtorNumberLabel = new Label(MEDIUM, "", 400, 275);
    private Label invoiceDateLabel = new Label(MEDIUM, "", 400, 289);

    private Label totalExBtwLabel = new Label(DIGIT, "", 488, 741);
    private Label btwLabel = new Label(DIGIT, "", 488, 756);
    private Label totalLabel = new Label(DIGIT, "", 488, 770);

    private Label[][] invoiceTable = new Label[][]{
        {new Label(REGULAR, "", 23, 360), new Label(REGULAR, "", 95, 360), new Label(REGULAR, "", 338, 360), new Label(REGULAR, "", 380, 360), new Label(DIGIT, "", 417, 360), new Label(DIGIT, "", 488, 360)},
        {new Label(REGULAR, "", 23, 374), new Label(REGULAR, "", 95, 374), new Label(REGULAR, "", 338, 374), new Label(REGULAR, "", 380, 374), new Label(DIGIT, "", 417, 374), new Label(DIGIT, "", 488, 374)},
        {new Label(REGULAR, "", 23, 388), new Label(REGULAR, "", 95, 388), new Label(REGULAR, "", 338, 388), new Label(REGULAR, "", 380, 388), new Label(DIGIT, "", 417, 388), new Label(DIGIT, "", 488, 388)},
        {new Label(REGULAR, "", 23, 402), new Label(REGULAR, "", 95, 402), new Label(REGULAR, "", 338, 402), new Label(REGULAR, "", 380, 402), new Label(DIGIT, "", 417, 402), new Label(DIGIT, "", 488, 402)},
        {new Label(REGULAR, "", 23, 416), new Label(REGULAR, "", 95, 416), new Label(REGULAR, "", 338, 416), new Label(REGULAR, "", 380, 416), new Label(DIGIT, "", 417, 416), new Label(DIGIT, "", 488, 416)},
        {new Label(REGULAR, "", 23, 430), new Label(REGULAR, "", 95, 430), new Label(REGULAR, "", 338, 430), new Label(REGULAR, "", 380, 430), new Label(DIGIT, "", 417, 430), new Label(DIGIT, "", 488, 430)},
        {new Label(REGULAR, "", 23, 444), new Label(REGULAR, "", 95, 444), new Label(REGULAR, "", 338, 444), new Label(REGULAR, "", 380, 444), new Label(DIGIT, "", 417, 444), new Label(DIGIT, "", 488, 444)},
        {new Label(REGULAR, "", 23, 458), new Label(REGULAR, "", 95, 458), new Label(REGULAR, "", 338, 458), new Label(REGULAR, "", 380, 458), new Label(DIGIT, "", 417, 458), new Label(DIGIT, "", 488, 458)},
        {new Label(REGULAR, "", 23, 472), new Label(REGULAR, "", 95, 472), new Label(REGULAR, "", 338, 472), new Label(REGULAR, "", 380, 472), new Label(DIGIT, "", 417, 472), new Label(DIGIT, "", 488, 472)},
        {new Label(REGULAR, "", 23, 486), new Label(REGULAR, "", 95, 486), new Label(REGULAR, "", 338, 486), new Label(REGULAR, "", 380, 486), new Label(DIGIT, "", 417, 486), new Label(DIGIT, "", 488, 486)},
        {new Label(REGULAR, "", 23, 500), new Label(REGULAR, "", 95, 500), new Label(REGULAR, "", 338, 500), new Label(REGULAR, "", 380, 500), new Label(DIGIT, "", 417, 500), new Label(DIGIT, "", 488, 500)},
        {new Label(REGULAR, "", 23, 514), new Label(REGULAR, "", 95, 514), new Label(REGULAR, "", 338, 514), new Label(REGULAR, "", 380, 514), new Label(DIGIT, "", 417, 514), new Label(DIGIT, "", 488, 514)},
        {new Label(REGULAR, "", 23, 528), new Label(REGULAR, "", 95, 528), new Label(REGULAR, "", 338, 528), new Label(REGULAR, "", 380, 528), new Label(DIGIT, "", 417, 528), new Label(DIGIT, "", 488, 528)},
        {new Label(REGULAR, "", 23, 542), new Label(REGULAR, "", 95, 542), new Label(REGULAR, "", 338, 542), new Label(REGULAR, "", 380, 542), new Label(DIGIT, "", 417, 542), new Label(DIGIT, "", 488, 542)},
        {new Label(REGULAR, "", 23, 556), new Label(REGULAR, "", 95, 556), new Label(REGULAR, "", 338, 556), new Label(REGULAR, "", 380, 556), new Label(DIGIT, "", 417, 556), new Label(DIGIT, "", 488, 556)},
        {new Label(REGULAR, "", 23, 570), new Label(REGULAR, "", 95, 570), new Label(REGULAR, "", 338, 570), new Label(REGULAR, "", 380, 570), new Label(DIGIT, "", 417, 570), new Label(DIGIT, "", 488, 570)},
        {new Label(REGULAR, "", 23, 584), new Label(REGULAR, "", 95, 584), new Label(REGULAR, "", 338, 584), new Label(REGULAR, "", 380, 584), new Label(DIGIT, "", 417, 584), new Label(DIGIT, "", 488, 584)},
        {new Label(REGULAR, "", 23, 598), new Label(REGULAR, "", 95, 598), new Label(REGULAR, "", 338, 598), new Label(REGULAR, "", 380, 598), new Label(DIGIT, "", 417, 598), new Label(DIGIT, "", 488, 598)},
        {new Label(REGULAR, "", 23, 612), new Label(REGULAR, "", 95, 612), new Label(REGULAR, "", 338, 612), new Label(REGULAR, "", 380, 612), new Label(DIGIT, "", 417, 612), new Label(DIGIT, "", 488, 612)},
        {new Label(REGULAR, "", 23, 626), new Label(REGULAR, "", 95, 626), new Label(REGULAR, "", 338, 626), new Label(REGULAR, "", 380, 626), new Label(DIGIT, "", 417, 626), new Label(DIGIT, "", 488, 626)},
        {new Label(REGULAR, "", 23, 640), new Label(REGULAR, "", 95, 640), new Label(REGULAR, "", 338, 640), new Label(REGULAR, "", 380, 640), new Label(DIGIT, "", 417, 640), new Label(DIGIT, "", 488, 640)},
        {new Label(REGULAR, "", 23, 654), new Label(REGULAR, "", 95, 654), new Label(REGULAR, "", 338, 654), new Label(REGULAR, "", 380, 654), new Label(DIGIT, "", 417, 654), new Label(DIGIT, "", 488, 654)},
        {new Label(REGULAR, "", 23, 668), new Label(REGULAR, "", 95, 668), new Label(REGULAR, "", 338, 668), new Label(REGULAR, "", 380, 668), new Label(DIGIT, "", 417, 668), new Label(DIGIT, "", 488, 668)},
        {new Label(REGULAR, "", 23, 682), new Label(REGULAR, "", 95, 682), new Label(REGULAR, "", 338, 682), new Label(REGULAR, "", 380, 682), new Label(DIGIT, "", 417, 682), new Label(DIGIT, "", 488, 682)},
        {new Label(REGULAR, "", 23, 696), new Label(REGULAR, "", 95, 696), new Label(REGULAR, "", 338, 696), new Label(REGULAR, "", 380, 696), new Label(DIGIT, "", 417, 696), new Label(DIGIT, "", 488, 696)}
    };

    public int invoiceRows = 0;






    public InvoiceRenderer() {
    }






    public InvoiceRenderer(InvoiceProject project) {
        setInvoiceDetails(project.invoiceDetails);
        setInvoiceTotals(project.getInvoiceTotal());
        setArticles(project.articleList);
        setDebtor(project.debtor);
    }






    public static void renderInvoiceProject(InvoiceProject project, Graphics2D g, double width, double height) {
        InvoiceRenderer renderer = new InvoiceRenderer(project);
        renderer.renderPageBackground(g, width, height);
        renderer.renderPageLabels(g, width, height);
        renderer.renderValueLabels(g, width, height);
        renderer.renderArticles(g, project.articleList.size(), width, height);

    }






    public void renderInvoice(Graphics2D g, double width, double height) {
        renderPageBackground(g, width, height);
        renderPageLabels(g, width, height);
        renderValueLabels(g, width, height);
        renderArticles(g, invoiceRows, width, height);
    }






    @Override
    public void modelChanged(InvoiceEvent ie, InvoiceModel model) {
        switch (ie) {
            case ARTICLE_ADDED:

        }
    }






    public void setInvoiceDetails(InvoiceDetails id) {
        invoiceNumberLabel.setContent(String.valueOf(id.getInvoiceNumber()));
        invoiceDateLabel.setContent(id.getInvoiceDate());
    }






    public void setInvoiceTotals(InvoiceTotal it) {
        totalExBtwLabel.setContent(it.getTotalExBtw().toString());
        btwLabel.setContent(it.getBtw().toString());
        totalLabel.setContent(it.getTotal().toString());
    }






    public void setDebtor(Debtor debtor) {
        nameLabel.setContent(debtor.getName());
        debtorNumberLabel.setContent(debtor.getDebtorNumber());
        streetLabel.setContent(debtor.getAddress().getStreet());
        zipcodeLabel.setContent(debtor.getAddress().getZipcode());
        cityLabel.setContent(debtor.getAddress().getCity());
    }






    public void setArticles(ArrayList<Article> list) {
        clearRows();
        if (list == null || list.isEmpty()) {
            invoiceRows = 0;
            return;
        }
        invoiceRows = list.size();
        for (int i = 0; i < list.size(); i++) {
            setRow(list.get(i), i);
        }
    }






    public void setRow(Article article, int rowIndex) {
        Label[] row = invoiceTable[rowIndex];
        String[] contents = article.toStringArray();
        for (int i = 0; i < row.length; i++) {
            row[i].setContent(contents[i]);
        }

    }






    public void clearRows() {
        for (int i = 0; i < invoiceTable.length; i++) {
            Label[] row = invoiceTable[i];
            for (int j = 0; j < row.length; j++) {
                row[j].setContent("");
            }
        }
    }






    public void renderPageBackground(Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g.drawImage(bgImage, 0, 0, null);
    }






    public void renderPageBackground(Graphics2D g, double w, double h) {
        g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g.drawImage(bgImage, 0, 0, (int) w, (int) h, null);
    }






    public void renderPageLabels(Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        for (Label label : LABELS) {
            g.setFont(label.getFont());
            g.drawString(label.getContent(), label.getX(), label.getY());
        }

    }






    public void renderPageLabels(Graphics2D g, double w, double h) {
        g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        for (Label label : LABELS) {
            renderLabel(label, g, w, h);
        }

    }






    public void renderValueLabels(Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        renderLabel(nameLabel, g);
        renderLabel(streetLabel, g);
        renderLabel(zipcodeLabel, g);
        renderLabel(cityLabel, g);
        renderLabel(invoiceNumberLabel, g);
        renderLabel(debtorNumberLabel, g);
        renderLabel(invoiceDateLabel, g);
        renderLabel(totalExBtwLabel, g);
        renderLabel(btwLabel, g);
        renderLabel(totalLabel, g);
    }






    public void renderValueLabels(Graphics2D g, double w, double h) {
        g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        renderLabel(nameLabel, g, w, h);
        renderLabel(streetLabel, g, w, h);
        renderLabel(zipcodeLabel, g, w, h);
        renderLabel(cityLabel, g, w, h);
        renderLabel(invoiceNumberLabel, g, w, h);
        renderLabel(debtorNumberLabel, g, w, h);
        renderLabel(invoiceDateLabel, g, w, h);
        renderLabel(totalExBtwLabel, g, w, h);
        renderLabel(btwLabel, g, w, h);
        renderLabel(totalLabel, g, w, h);
    }






    public void renderArticles(Graphics2D g, int rows) {
        g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        for (int i = 0; i < rows; i++) {
            Label[] row = invoiceTable[i];
            for (int j = 0; j < row.length; j++) {
                renderLabel(row[j], g);
            }
        }
    }






    public void renderArticles(Graphics2D g, int rows, double w, double h) {
        g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        for (int i = 0; i < rows; i++) {
            Label[] row = invoiceTable[i];
            for (int j = 0; j < row.length; j++) {
                renderLabel(row[j], g, w, h);
            }
        }
    }






    public void renderLabel(Label label, Graphics2D g) {
        g.setFont(label.getFont());
        g.drawString(label.getContent(), label.getX(), label.getY());
    }






    public void renderLabel(Label label, Graphics2D g, double w, double h) {
        double x = (double) label.getX();
        double y = (double) label.getY();
        x /= SIZE.getWidth();
        y /= SIZE.getHeight();
        x *= w;
        y *= h;
        g.setFont(label.getFont());
        g.drawString(label.getContent(), (int) x, (int) y);
    }
}

