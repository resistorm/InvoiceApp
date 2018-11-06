/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.application.core;

import com.application.data.Price;
import com.application.data.Product;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;




/**
 *
 * @author daan-
 */
public class ProductParser {

    private File file;






    public ProductParser(File file) {
        this.file = file;
    }






    public ArrayList<Product> parseFile() {
        return parseFile(file);
    }






    public static ArrayList<Product> parseFile(File file) {
        ArrayList<Product> list = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            for (line = reader.readLine(); line != null; line = reader.readLine()) {
                Product product = parseLine(line);
                list.add(product);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("An Error Occurred trying to parse a ProductFile " + file.getAbsolutePath());
        }
        return list;
    }






    private static Product parseLine(String line) {
        String[] items = line.split(";");
        String productNumber = items[0];
        String productName = items[1];
        Price productPrice = new Price(items[2]);
        String description = items[3];
        return new Product(productNumber, productName, productPrice, description);

    }

}

