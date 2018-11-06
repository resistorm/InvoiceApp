/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.application.core;

import com.application.common.DataContainer;
import com.application.common.DataContainerObserver;
import com.application.common.FileLoader;
import com.application.data.Product;
import java.io.File;
import java.util.ArrayList;




/**
 *
 * @author daan-
 */
public class ProductBase implements DataContainer<Product> {

    private static ProductBase instance;
    private ArrayList<DataContainerObserver<Product>> observerList;
    private ArrayList<Product> dataList;
    private boolean modified;






    public static ProductBase getInstance() {
        if (instance == null) {
            synchronized (ProductBase.class) {
                if (instance == null) {
                    File dataFile = FileLoader.getProductDataFile();
                    ProductParser parser = new ProductParser(dataFile);
                    instance = new ProductBase(parser);
                }
            }
        }
        return instance;
    }






    protected ProductBase(ProductParser parser) {
        dataList = parser.parseFile();
        if (instance == null) {
            System.out.println("New ProductBase initialized");
            instance = this;
        } else {
            System.out.println("WARNING! multiple ProductBase instances alive");
            System.out.println("New ProductBase initialized, static instance variable already set");
        }
        modified = false;
        observerList = new ArrayList<>();
    }






    @Override
    public int size() {
        return dataList.size();
    }






    @Override
    public Product get(int index) {
        return dataList.get(index);
    }






    @Override
    public void add(Product product) {
        dataList.add(product);
        modified = true;
    }






    @Override
    public void remove(Product product) {
        dataList.remove(product);
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
    public ArrayList<DataContainerObserver<Product>> getObserverList() {
        return observerList;
    }
}

