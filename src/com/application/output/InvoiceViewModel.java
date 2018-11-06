/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.application.output;

import com.application.common.ImageLoader;
import com.application.common.RobotoFont;
import com.application.data.Label;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.image.BufferedImage;




/**
 *
 * @author daan-
 */
public class InvoiceViewModel {

    public static final Dimension SIZE = new Dimension(599, 844);

    public static final Font THIN = RobotoFont.robotoThin(11.0f);
    public static final Font REGULAR = RobotoFont.robotoRegular(11.0f);
    public static final Font MEDIUM = RobotoFont.robotoMedium(11.0f);
    public static final Font BOLD = RobotoFont.robotoBold(11.0f);

    public static Label[] PAGE_LABELS = new Label[]{
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

    public static final BufferedImage BACKGROUND_IMAGE = ImageLoader.loadImage(ImageLoader.PAGE_IMAGE);
    public static final Label NAME_LABEL = new Label(MEDIUM, "", 79, 164);
    public static final Label STREET_LABEL = new Label(MEDIUM, "", 79, 179);
    public static final Label ZIPCODE_LABEL = new Label(MEDIUM, "", 79, 193);
    public static final Label CITY_LABEL = new Label(MEDIUM, "", 130, 193);

    public static final Label INVOICE_NUMBER_LABEL = new Label(MEDIUM, "", 400, 261);
    public static final Label DEBTOR_NUMBER_LABEL = new Label(MEDIUM, "", 400, 275);
    public static final Label INVOICE_DATE_LABEL = new Label(MEDIUM, "", 400, 289);

    public static final Label TOTAL_EX_BTW_LABEL = new Label(MEDIUM, "", 488, 741);
    public static final Label BTW_LABEL = new Label(MEDIUM, "", 488, 756);
    public static final Label TOTAL_LABEL = new Label(MEDIUM, "", 488, 770);

    public static final Label[][] ARTICLE_ROWS = new Label[][]{
        {new Label(REGULAR, "", 23, 360), new Label(REGULAR, "", 95, 360), new Label(REGULAR, "", 338, 360), new Label(REGULAR, "", 380, 360), new Label(REGULAR, "", 417, 360), new Label(REGULAR, "", 488, 360)},
        {new Label(REGULAR, "", 23, 374), new Label(REGULAR, "", 95, 374), new Label(REGULAR, "", 338, 374), new Label(REGULAR, "", 380, 374), new Label(REGULAR, "", 417, 374), new Label(REGULAR, "", 488, 374)},
        {new Label(REGULAR, "", 23, 388), new Label(REGULAR, "", 95, 388), new Label(REGULAR, "", 338, 388), new Label(REGULAR, "", 380, 388), new Label(REGULAR, "", 417, 388), new Label(REGULAR, "", 488, 388)},
        {new Label(REGULAR, "", 23, 402), new Label(REGULAR, "", 95, 402), new Label(REGULAR, "", 338, 402), new Label(REGULAR, "", 380, 402), new Label(REGULAR, "", 417, 402), new Label(REGULAR, "", 488, 402)},
        {new Label(REGULAR, "", 23, 416), new Label(REGULAR, "", 95, 416), new Label(REGULAR, "", 338, 416), new Label(REGULAR, "", 380, 416), new Label(REGULAR, "", 417, 416), new Label(REGULAR, "", 488, 416)},
        {new Label(REGULAR, "", 23, 430), new Label(REGULAR, "", 95, 430), new Label(REGULAR, "", 338, 430), new Label(REGULAR, "", 380, 430), new Label(REGULAR, "", 417, 430), new Label(REGULAR, "", 488, 430)},
        {new Label(REGULAR, "", 23, 444), new Label(REGULAR, "", 95, 444), new Label(REGULAR, "", 338, 444), new Label(REGULAR, "", 380, 444), new Label(REGULAR, "", 417, 444), new Label(REGULAR, "", 488, 444)},
        {new Label(REGULAR, "", 23, 458), new Label(REGULAR, "", 95, 458), new Label(REGULAR, "", 338, 458), new Label(REGULAR, "", 380, 458), new Label(REGULAR, "", 417, 458), new Label(REGULAR, "", 488, 458)},
        {new Label(REGULAR, "", 23, 472), new Label(REGULAR, "", 95, 472), new Label(REGULAR, "", 338, 472), new Label(REGULAR, "", 380, 472), new Label(REGULAR, "", 417, 472), new Label(REGULAR, "", 488, 472)},
        {new Label(REGULAR, "", 23, 486), new Label(REGULAR, "", 95, 486), new Label(REGULAR, "", 338, 486), new Label(REGULAR, "", 380, 486), new Label(REGULAR, "", 417, 486), new Label(REGULAR, "", 488, 486)},
        {new Label(REGULAR, "", 23, 500), new Label(REGULAR, "", 95, 500), new Label(REGULAR, "", 338, 500), new Label(REGULAR, "", 380, 500), new Label(REGULAR, "", 417, 500), new Label(REGULAR, "", 488, 500)},
        {new Label(REGULAR, "", 23, 514), new Label(REGULAR, "", 95, 514), new Label(REGULAR, "", 338, 514), new Label(REGULAR, "", 380, 514), new Label(REGULAR, "", 417, 514), new Label(REGULAR, "", 488, 514)},
        {new Label(REGULAR, "", 23, 528), new Label(REGULAR, "", 95, 528), new Label(REGULAR, "", 338, 528), new Label(REGULAR, "", 380, 528), new Label(REGULAR, "", 417, 528), new Label(REGULAR, "", 488, 528)},
        {new Label(REGULAR, "", 23, 542), new Label(REGULAR, "", 95, 542), new Label(REGULAR, "", 338, 542), new Label(REGULAR, "", 380, 542), new Label(REGULAR, "", 417, 542), new Label(REGULAR, "", 488, 542)},
        {new Label(REGULAR, "", 23, 556), new Label(REGULAR, "", 95, 556), new Label(REGULAR, "", 338, 556), new Label(REGULAR, "", 380, 556), new Label(REGULAR, "", 417, 556), new Label(REGULAR, "", 488, 556)},
        {new Label(REGULAR, "", 23, 570), new Label(REGULAR, "", 95, 570), new Label(REGULAR, "", 338, 570), new Label(REGULAR, "", 380, 570), new Label(REGULAR, "", 417, 570), new Label(REGULAR, "", 488, 570)},
        {new Label(REGULAR, "", 23, 584), new Label(REGULAR, "", 95, 584), new Label(REGULAR, "", 338, 584), new Label(REGULAR, "", 380, 584), new Label(REGULAR, "", 417, 584), new Label(REGULAR, "", 488, 584)},
        {new Label(REGULAR, "", 23, 598), new Label(REGULAR, "", 95, 598), new Label(REGULAR, "", 338, 598), new Label(REGULAR, "", 380, 598), new Label(REGULAR, "", 417, 598), new Label(REGULAR, "", 488, 598)},
        {new Label(REGULAR, "", 23, 612), new Label(REGULAR, "", 95, 612), new Label(REGULAR, "", 338, 612), new Label(REGULAR, "", 380, 612), new Label(REGULAR, "", 417, 612), new Label(REGULAR, "", 488, 612)},
        {new Label(REGULAR, "", 23, 626), new Label(REGULAR, "", 95, 626), new Label(REGULAR, "", 338, 626), new Label(REGULAR, "", 380, 626), new Label(REGULAR, "", 417, 626), new Label(REGULAR, "", 488, 626)},
        {new Label(REGULAR, "", 23, 640), new Label(REGULAR, "", 95, 640), new Label(REGULAR, "", 338, 640), new Label(REGULAR, "", 380, 640), new Label(REGULAR, "", 417, 640), new Label(REGULAR, "", 488, 640)},
        {new Label(REGULAR, "", 23, 654), new Label(REGULAR, "", 95, 654), new Label(REGULAR, "", 338, 654), new Label(REGULAR, "", 380, 654), new Label(REGULAR, "", 417, 654), new Label(REGULAR, "", 488, 654)},
        {new Label(REGULAR, "", 23, 668), new Label(REGULAR, "", 95, 668), new Label(REGULAR, "", 338, 668), new Label(REGULAR, "", 380, 668), new Label(REGULAR, "", 417, 668), new Label(REGULAR, "", 488, 668)},
        {new Label(REGULAR, "", 23, 682), new Label(REGULAR, "", 95, 682), new Label(REGULAR, "", 338, 682), new Label(REGULAR, "", 380, 682), new Label(REGULAR, "", 417, 682), new Label(REGULAR, "", 488, 682)},
        {new Label(REGULAR, "", 23, 696), new Label(REGULAR, "", 95, 696), new Label(REGULAR, "", 338, 696), new Label(REGULAR, "", 380, 696), new Label(REGULAR, "", 417, 696), new Label(REGULAR, "", 488, 696)}
    };

}

