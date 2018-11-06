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
public interface DataContainerObserver<D> {

    public void dataContainerUpdated(DataContainer<D> dataContainer);

}

