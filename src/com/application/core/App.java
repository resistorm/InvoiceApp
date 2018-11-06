/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.application.core;

import com.application.common.BaseProxy;
import com.application.common.DataObject;
import com.application.common.FileLoader;
import com.application.data.Configuration;
import com.application.data.Debtor;
import com.application.data.Product;
import java.io.File;




/**
 *
 * @author daan-
 */
public class App {

    public final DebtorBase debtorBase;
    public final ProductBase productBase;
    public final ConfigBase configBase;






    /**
     * Builds a new App instance and initializes the core Base objects -
     * DebtorBase - ProductBase - ConfigBase
     */
    public App() {
        this.debtorBase = DebtorBase.getInstance();
        this.productBase = ProductBase.getInstance();
        this.configBase = ConfigBase.getInstance();
    }






    /**
     * Bundles the passed Base Object to instantiate a new App instance
     *
     * @param debtorBase  the DebtorBase object
     * @param productBase the ProductBase object
     * @param configBase  the ConfigBase object
     */
    public App(DebtorBase debtorBase, ProductBase productBase, ConfigBase configBase) {
        this.debtorBase = debtorBase;
        this.productBase = productBase;
        this.configBase = configBase;
    }






    public void setInvoiceCounter(int counter) {
        configBase.setInvoiceCounter(counter);
        storeToFile(Base.CONFIG);
    }






    /**
     * Evaluates the Base to return the size of the corresponding Base instance
     *
     * @param base Base.DEBTOR, Base.PRODUCT or Base.CONFIG
     *
     * @return the size of the corresponding base object as amount of objects
     */
    public int size(Base base) {
        switch (base) {
            case DEBTOR:
                return debtorBase.size();
            case PRODUCT:
                return productBase.size();
            case CONFIG:
                return configBase.size();
            default:
                return 0;
        }
    }






    /**
     * Evaluates the Base to return the Object on the specified index of the
     * corresponding Base instance
     *
     * @param index the index at which the item is stored
     * @param base  the Base which holds the requested catalog
     *
     * @return the Object at the specified index in the specified Base
     */
    public DataObject get(int index, Base base) {
        switch (base) {
            case DEBTOR:
                return debtorBase.get(index);
            case PRODUCT:
                return productBase.get(index);
            case CONFIG:
                return configBase.get(index);
            default:
                return null;
        }
    }






    /**
     * Adds the Object<? extends DataObject> to the specified Base object if,
     * and only if the passed DataObject instance returns true on reflecting the
     * (DataObject instanceof BaseType).
     * <p>
     * The addition of an object will not ensure the newly added object to be
     * written to the base file. In order to persist these mutations, invoke
     * <code>App.storeToFile(Base)</code>
     *
     * @param data the DataObject to be added to the Base
     * @param base
     */
    public void add(DataObject data, Base base) {
        switch (base) {
            case DEBTOR:
                if (data instanceof Debtor) {
                    debtorBase.add((Debtor) data);
                }
                break;
            case PRODUCT:
                if (data instanceof Product) {
                    productBase.add((Product) data);
                }
                break;
            case CONFIG:
                if (data instanceof Configuration) {
                    configBase.add((Configuration) data);
                }
                break;
        }
    }






    /**
     * Removes the Object<? extends DataObject> from the specified Base object
     * if, and only if the passed DataObject instance returns true on reflecting
     * the (DataObject instanceof BaseType).
     * <p>
     * The deletion of an object will not ensure the object to be deleted from
     * the base file. In order to persist these mutations, invoke
     * <code>App.storeToFile(Base)</code>
     *
     * @param data
     * @param base
     */
    public void remove(DataObject data, Base base) {
        switch (base) {
            case DEBTOR:
                if (data instanceof Debtor) {
                    debtorBase.remove((Debtor) data);
                }
                break;
            case PRODUCT:
                if (data instanceof Product) {
                    productBase.remove((Product) data);
                }
                break;
            case CONFIG:
                if (data instanceof Configuration) {
                    configBase.remove((Configuration) data);
                }
                break;
        }

    }






    /**
     * Creates a proxy object to access the data in the specified Base object
     *
     * @param base the base for which the proxy must be created
     *
     * @return the Proxy object for accessing the items in the Base
     */
    public BaseProxy getBaseProxy(Base base) {
        switch (base) {
            case DEBTOR:
                return new DebtorProxy(debtorBase);
            case PRODUCT:
                return new ProductProxy(productBase);
            case CONFIG:
                return new ConfigProxy(configBase);
            default:
                return null;
        }
    }






    /**
     * Creates a new Runnable object that collects the resources that are
     * required for writing the Base contents to a file, and invokes
     * storeChanges(DataWriter) on the specified Base instance. If the base
     * instance has its 'modified' flag equal to true, the entire base is
     * written to the base file
     *
     * @param base the base instance that must be stored
     */
    public void storeToFile(Base base) {
        Runnable runner;
        switch (base) {
            case DEBTOR:
                runner = (() -> {
                    File file = FileLoader.getDebtorDataFile();
                    DataWriter writer = new DataWriter(file);
                    debtorBase.storeChanges(writer);
                });
                break;
            case PRODUCT:
                runner = (() -> {
                    File file = FileLoader.getProductDataFile();
                    DataWriter writer = new DataWriter(file);
                    productBase.storeChanges(writer);
                });
                break;
            case CONFIG:
                runner = (() -> {
                    File file = FileLoader.getConfigDataFile();
                    DataWriter writer = new DataWriter(file);
                    configBase.storeChanges(writer);
                });
                break;
            default:
                return;
        }
        Thread t = new Thread(runner);
        t.start();
    }

}

