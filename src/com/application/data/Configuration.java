/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.application.data;

import com.application.common.DataObject;




/**
 *
 * @author daan-
 */
public class Configuration implements DataObject {

    private final String name;
    private Object content;






    public Configuration(String name, Object content) {
        this.name = name;
        this.content = content;
    }






    public String getName() {
        return name;
    }






    public Object getContent() {
        return content;
    }






    public void setContent(Object content) {
        this.content = content;
    }






    @Override
    public Object[] toArray() {
        return new Object[]{name, content};
    }

}

