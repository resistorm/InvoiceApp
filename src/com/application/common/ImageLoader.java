/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.application.common;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;




/**
 *
 * @author daan-
 */
public class ImageLoader {

    public static final String PAGE_IMAGE = "/invoice_page.png";






    public static BufferedImage loadImage(String path) {
        BufferedImage image = null;
        URL url = ImageLoader.class.getResource(path);
        try {
            image = ImageIO.read(url);
        } catch (IOException e) {
            System.out.println("Unable to load the BufferedImage specified by " + path);
        }
        return image;
    }

}

