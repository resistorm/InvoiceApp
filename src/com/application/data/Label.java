/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.application.data;

import java.awt.Font;




/**
 *
 * @author daan-
 */
public class Label {

    private Font font;
    private String content;
    private int x;
    private int y;






    public Label(Font font, String content, int x, int y) {
        this.font = font;
        this.content = content;
        this.x = x;
        this.y = y;
    }






    public Font getFont() {
        return font;
    }






    public String getContent() {
        return content;
    }






    public void setContent(String content) {
        this.content = content;
    }






    public int getX() {
        return x;
    }






    public int getY() {
        return y;
    }
}

