/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.application.core;

import com.application.core.ConfigBase;
import com.application.common.BaseProxy;
import com.application.data.Configuration;




/**
 *
 * @author daan-
 */
public class ConfigProxy implements BaseProxy<Configuration> {

    private final ConfigBase base;






    public ConfigProxy(ConfigBase base) {
        this.base = base;
    }






    @Override
    public int size() {
        return base.size();
    }






    @Override
    public Configuration get(int index) {
        return base.get(index);
    }






    @Override
    public void add(Configuration data) {
        base.add(data);
    }






    @Override
    public void remove(Configuration data) {
        base.remove(data);
    }

}

