/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.application.common;

import java.awt.Font;




/**
 *
 * @author daan-
 */
public class RobotoFont {

    public static Font robotoBlack(float size) {
        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT, RobotoFont.class.getResourceAsStream("/Roboto/Roboto-Black.ttf"));
            return font.deriveFont(size);
        } catch (Exception e) {
        }
        return Font.getFont("Arial").deriveFont(size);
    }






    public static Font robotoBold(float size) {
        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT, RobotoFont.class.getResourceAsStream("/Roboto/Roboto-Bold.ttf"));
            return font.deriveFont(size);
        } catch (Exception e) {
        }
        return Font.getFont("sans-serif").deriveFont(size);
    }






    public static Font robotoLight(float size) {
        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT, RobotoFont.class.getResourceAsStream("/Roboto/Roboto-Light.ttf"));
            return font.deriveFont(size);
        } catch (Exception e) {
        }
        return Font.getFont("sans-serif").deriveFont(size);
    }






    public static Font robotoMedium(float size) {
        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT, RobotoFont.class.getResourceAsStream("/Roboto/Roboto-Medium.ttf"));
            return font.deriveFont(size);
        } catch (Exception e) {
        }
        return Font.getFont("sans-serif").deriveFont(size);
    }






    public static Font robotoRegular(float size) {
        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT, RobotoFont.class.getResourceAsStream("/Roboto/Roboto-Regular.ttf"));
            return font.deriveFont(size);
        } catch (Exception e) {
        }
        return Font.getFont("sans-serif").deriveFont(size);
    }






    public static Font robotoThin(float size) {
        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT, RobotoFont.class.getResourceAsStream("/Roboto/Roboto-Thin.ttf"));
            return font.deriveFont(size);
        } catch (Exception e) {
        }
        return Font.getFont("sans-serif").deriveFont(size);
    }

}

