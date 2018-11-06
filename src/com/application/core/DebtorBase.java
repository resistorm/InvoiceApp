/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.application.core;

import com.application.common.DataContainer;
import com.application.common.DataContainerObserver;
import com.application.common.FileLoader;
import com.application.data.Debtor;
import java.io.File;
import java.util.ArrayList;




/**
 *
 * @author daan-
 */
public class DebtorBase implements DataContainer<Debtor> {

    private static DebtorBase instance;
    private ArrayList<DataContainerObserver<Debtor>> observerList;
    private ArrayList<Debtor> dataList;
    private boolean modified;






    public static DebtorBase getInstance() {
        if (instance == null) {
            synchronized (DebtorBase.class) {
                if (instance == null) {
                    File dataFile = FileLoader.getDebtorDataFile();
                    DebtorParser parser = new DebtorParser(dataFile);
                    instance = new DebtorBase(parser);
                }
            }
        }
        return instance;
    }






    protected DebtorBase(DebtorParser parser) {
        dataList = parser.parseFile();
        if (instance == null) {
            System.out.println("New DebtorBase initialized");
            instance = this;
        } else {
            System.out.println("WARNING! multiple DebtorBase instances alive");
            System.out.println("New DebtorBase initialized, static instance variable already set");
        }
        modified = false;
        observerList = new ArrayList<>();
    }






    @Override
    public int size() {
        return dataList.size();
    }






    @Override
    public Debtor get(int index) {
        return dataList.get(index);
    }






    @Override
    public void add(Debtor debtor) {
        dataList.add(debtor);
        modified = true;
    }






    @Override
    public void remove(Debtor debtor) {
        dataList.remove(debtor);
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
    public ArrayList<DataContainerObserver<Debtor>> getObserverList() {
        return observerList;
    }
}

