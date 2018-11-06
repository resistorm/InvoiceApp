/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.application.common;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;




/**
 *
 * @author daan-
 */
public class FileSaver {

    public static void saveToFile(ArrayList<DataObject> data, File file) {
        if (!file.exists() || data.isEmpty()) {
            throw new IllegalArgumentException();
        }
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(file));
            String line;
            for (int i = 0; i < data.size(); i++) {
                line = data.get(i).toCSV();
                writer.println(line);
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
        }

    }

}

