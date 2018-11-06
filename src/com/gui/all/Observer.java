/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gui.all;




/**
 *
 * @author daan-
 * @param <O>
 */
public interface Observer<O extends Observable> {

    public void modelUpdated(int changeId, O observable);

}

