/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.application.common;




/**
 *
 * @author daan-
 */
public interface DataObject {

    public Object[] toArray();






    public default String toCSV() {
        Object[] array = toArray();
        String csv = array[0].toString();
        for (int i = 1; i < array.length; i++) {
            csv += ";" + array[i].toString();
        }
        return csv;
    }

}

