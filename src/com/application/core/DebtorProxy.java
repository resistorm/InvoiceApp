/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.application.core;

import com.application.core.DebtorBase;
import com.application.common.BaseProxy;
import com.application.data.Debtor;




/**
 *
 * @author daan-
 */
public class DebtorProxy implements BaseProxy<Debtor> {

    private final DebtorBase base;






    public DebtorProxy(DebtorBase base) {
        this.base = base;
    }






    @Override
    public int size() {
        return base.size();
    }






    @Override
    public Debtor get(int index) {
        return base.get(index);
    }






    @Override
    public void add(Debtor data) {
        base.add(data);
    }






    @Override
    public void remove(Debtor data) {
        base.remove(data);
    }

}

