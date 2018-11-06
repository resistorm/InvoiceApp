/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.application.common;

import com.application.core.DataWriter;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.SwingUtilities;




/**
 *
 * @author daan-
 */
public interface DataContainer<D> {

    public ArrayList<DataContainerObserver<D>> getObserverList();






    public int size();






    public D get(int index);






    public void add(D data);






    public void remove(D data);






    public void storeChanges(DataWriter writer);






    public default void registerDataContainerObserver(DataContainerObserver<D> observer) {
        getObserverList().add(observer);
    }






    public default void removeDataContainerObserver(DataContainerObserver<D> observer) {
        getObserverList().remove(observer);
    }






    public default void notifyDataContainerObservers() {
        if (getObserverList() != null && !getObserverList().isEmpty()) {
            Iterator<DataContainerObserver<D>> iterator = getObserverList().iterator();
            while (iterator.hasNext()) {
                SwingUtilities.invokeLater(() -> {
                    iterator.next().dataContainerUpdated(this);
                });
            }
        }
    }
}

