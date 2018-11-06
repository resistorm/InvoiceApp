/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.application.common;




/**
 *
 * @author daan-
 * @param <T>
 */
public interface BaseProxy<T> {

    public int size();






    public T get(int index);






    public void add(T data);






    public void remove(T data);

}

