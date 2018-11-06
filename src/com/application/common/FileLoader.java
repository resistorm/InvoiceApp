/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.application.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;




/**
 *
 * @author daan-
 */
public class FileLoader {

    public static final String EMBEDDED_DEBTOR_FILE_PATH = "/debtors.csv";
    public static final String EMBEDDED_PRODUCT_FILE_PATH = "/products.csv";
    public static final String EMBEDDED_CONFIG_FILE_PATH = "/config.csv";
    public static final String APPLICATION_DIRECTORY = System.getProperty("user.home") + "\\.invoice_app\\";
    public static final String DEBTOR_FILE_PATH = "debtors.csv";
    public static final String PRODUCT_FILE_PATH = "products.csv";
    public static final String CONFIG_FILE_PATH = "config.csv";






    public static File getApplicationDirectory() {
        File file = new File(APPLICATION_DIRECTORY);
        if (!file.exists()) {
            file.mkdir();
        }
        return file;
    }






    public static File getDebtorDataFile() {
        File file = new File(APPLICATION_DIRECTORY + DEBTOR_FILE_PATH);
        if (!file.exists()) {
            try {
                getApplicationDirectory();
                file.createNewFile();
                createCopy(FileLoader.class.getResourceAsStream(EMBEDDED_DEBTOR_FILE_PATH), file);
            } catch (IOException e) {
            }
        }
        return file;
    }






    public static File getProductDataFile() {
        File file = new File(APPLICATION_DIRECTORY + PRODUCT_FILE_PATH);
        if (!file.exists()) {
            try {
                getApplicationDirectory();
                file.createNewFile();
                createCopy(FileLoader.class.getResourceAsStream(EMBEDDED_PRODUCT_FILE_PATH), file);
            } catch (IOException e) {
            }
        }
        return file;
    }






    public static File getConfigDataFile() {
        File file = new File(APPLICATION_DIRECTORY + CONFIG_FILE_PATH);
        if (!file.exists()) {
            try {
                getApplicationDirectory();
                file.createNewFile();
                createCopy(FileLoader.class.getResourceAsStream(EMBEDDED_CONFIG_FILE_PATH), file);
            } catch (IOException e) {
            }
        }
        return file;
    }






    public static void createCopy(InputStream in, File out) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            PrintWriter writer = new PrintWriter(new FileWriter(out));
            String line;
            for (line = reader.readLine(); line != null; line = reader.readLine()) {
                writer.println(line);
            }
            reader.close();
            writer.close();

        } catch (IOException e) {
            System.out.println("Unable to create a copy");
        }
    }

}

