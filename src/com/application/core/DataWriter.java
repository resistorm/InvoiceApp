/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.application.core;

import com.application.common.DataObject;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;




/**
 *
 * @author daan-
 */
public class DataWriter {

    private File file;






    public DataWriter(File file) {
        this.file = file;
    }






    public void storeToFile(ArrayList<? extends DataObject> list) {
        storeToFile(list, file);
    }






    public static void storeToFile(ArrayList<? extends DataObject> list, File file) {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(file));
            for (int i = 0; i < list.size(); i++) {
                String line = list.get(i).toCSV();
                writer.println(line);
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
        }
    }

}

