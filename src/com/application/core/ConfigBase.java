/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.application.core;

import com.application.common.DataContainer;
import com.application.common.DataContainerObserver;
import com.application.common.FileLoader;
import com.application.data.Configuration;
import java.io.File;
import java.util.ArrayList;




/**
 *
 * @author daan-
 */
public class ConfigBase implements DataContainer<Configuration> {

    private static ConfigBase instance;
    private ArrayList<DataContainerObserver<Configuration>> observerList;
    private ArrayList<Configuration> dataList;
    private boolean modified;






    public static ConfigBase getInstance() {
        if (instance == null) {
            synchronized (ConfigBase.class) {
                if (instance == null) {
                    File dataFile = FileLoader.getConfigDataFile();
                    ConfigParser parser = new ConfigParser(dataFile);
                    instance = new ConfigBase(parser);
                }
            }
        }
        return instance;
    }






    protected ConfigBase(ConfigParser parser) {
        dataList = parser.parseFile();
        if (instance == null) {
            System.out.println("New ConfigBase initialized");
            instance = this;
        } else {
            System.out.println("WARNING! multiple ConfigBase instances alive");
            System.out.println("New ConfigBase initialized, static instance variable already set");
        }
        modified = false;
        observerList = new ArrayList<>();
    }






    @Override
    public int size() {
        return dataList.size();
    }






    @Override
    public Configuration get(int index) {
        return dataList.get(index);
    }






    public int getInvoiceCounter() {
        String counter = (String) dataList.get(0).getContent();
        counter = counter.replaceAll("\\D+", "");
        if (counter.length() > 0) {
            return Integer.parseInt(counter);
        } else {
            return 0;
        }
    }






    public void incrementInvoiceCounter() {
        int counter = getInvoiceCounter();
        counter++;
        dataList.get(0).setContent(counter);
        modified = true;

    }






    public void setInvoiceCounter(int counter) {
        dataList.get(0).setContent(String.valueOf(counter));
    }






    @Override
    public void add(Configuration config) {
        dataList.add(config);
        modified = true;
    }






    @Override
    public void remove(Configuration config) {
        dataList.remove(config);
        modified = true;
    }






    @Override
    public void storeChanges(DataWriter writer) {
        if (modified) {
            writer.storeToFile(dataList);
            modified = false;
        }
    }






    @Override
    public ArrayList<DataContainerObserver<Configuration>> getObserverList() {
        return observerList;
    }

}

