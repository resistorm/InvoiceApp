/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.application.core;

import com.application.data.Address;
import com.application.data.Debtor;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;




/**
 *
 * @author daan-
 */
public class DebtorParser {

    private File file;






    public DebtorParser(File file) {
        this.file = file;
    }






    public ArrayList<Debtor> parseFile() {
        return parseFile(file);
    }






    public static ArrayList<Debtor> parseFile(File file) {
        ArrayList<Debtor> list = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            for (line = reader.readLine(); line != null; line = reader.readLine()) {
                line = line.toLowerCase();
                Debtor debtor = parseLine(line);
                list.add(debtor);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("An Error Occurred trying to parse a DebtorFile " + file.getAbsolutePath());
        }
        return list;
    }






    private static Debtor parseLine(String line) {
        String[] items = line.split(";");
        if (items.length == 1) {
            items = line.split("\t");
        }
        if (items.length == 1) {
            throw new IllegalArgumentException("Unparcable line " + line);
        }
        String debtorNumber = String.valueOf(parseDebtorId(items[0]));
        String debtorName = items[1];
        Address address = parseAddress(items);
        return new Debtor(debtorNumber, debtorName, address);
    }






    private static Address parseAddress(String[] items) {
        String street = items[2].toLowerCase();
        String zipcode = items[3].toLowerCase();
        String city = items[4].toLowerCase();
        String country = items[5].toLowerCase();
        Address address = new Address(street, zipcode, city, country);
        return address;
    }






    private static int parseDebtorId(String item) {
        String digits = item.replaceAll("\\D+", "");
        return Integer.parseInt(digits);
    }

}

