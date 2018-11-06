/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.application.core;

import com.application.data.Configuration;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;




/**
 *
 * @author daan-
 */
public class ConfigParser {

    private File file;






    public ConfigParser(File file) {
        this.file = file;
    }






    public ArrayList<Configuration> parseFile() {
        return parseFile(file);
    }






    public static ArrayList<Configuration> parseFile(File file) {
        ArrayList<Configuration> list = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            for (line = reader.readLine(); line != null; line = reader.readLine()) {
                Configuration configuration = parseLine(line);
                list.add(configuration);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("An Error Occurred trying to parse a ConfigFile " + file.getAbsolutePath());
        }
        return list;

    }






    private static Configuration parseLine(String line) {
        String[] items = line.split(";");
        String name = items[0];
        String content = items[1];
        if (items.length > 2) {
            for (int i = 2; i < items.length; i++) {
                content += ";" + items[i];
            }
        }
        return new Configuration(name, content);
    }

}

