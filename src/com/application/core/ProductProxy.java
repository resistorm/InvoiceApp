/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.application.core;

import com.application.core.ProductBase;
import com.application.common.BaseProxy;
import com.application.data.Product;




/**
 *
 * @author daan-
 */
public class ProductProxy implements BaseProxy<Product> {

    private final ProductBase base;






    public ProductProxy(ProductBase base) {
        this.base = base;
    }






    @Override
    public int size() {
        return base.size();
    }






    @Override
    public Product get(int index) {
        return base.get(index);
    }






    @Override
    public void add(Product data) {
        base.add(data);
    }






    @Override
    public void remove(Product data) {
        base.remove(data);
    }

}

