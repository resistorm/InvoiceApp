/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gui.all;

import java.util.ArrayList;
import javax.swing.SwingUtilities;




/**
 *
 * @author daan-
 * @param <O>
 */
public abstract class Observable<O extends Observer> {

    private final ArrayList<O> observerList;






    public Observable() {
        observerList = new ArrayList<>();
    }






    public void subscribe(O observer) {
        observerList.add(observer);
    }






    public void unsubscribe(O observer) {
        observerList.remove(observer);
    }






    public void fireChangeEvent(int eventId) {
        if (!observerList.isEmpty()) {
            SwingUtilities.invokeLater(() -> {
                for (int i = 0; i < observerList.size(); i++) {
                    observerList.get(i).modelUpdated(eventId, this);
                }
            });
        }
    }






    public void notifyObservers(ObserverHandler<O> handler) {
        SwingUtilities.invokeLater(() -> {
            if (!observerList.isEmpty()) {
                for (int i = 0; i < observerList.size(); i++) {
                    O observer = observerList.get(i);
                    handler.handleObserver(observer);
                }
            }
        });
    }






    public ArrayList<O> getObserverList() {
        return observerList;
    }

}

