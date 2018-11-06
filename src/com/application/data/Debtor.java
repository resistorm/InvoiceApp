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
public class Debtor extends Person implements DataObject {

    private final String debtorNumber;






    public Debtor(String debtorNumber, String name, Address address) {
        super(name, address);
        this.debtorNumber = debtorNumber;
    }






    @Override
    public Object[] toArray() {
        return new Object[]{debtorNumber, getName(), getAddress().getStreet(), getAddress().getZipcode(), getAddress().getCity(), getAddress().getCountry()};
    }






    public String getDebtorNumber() {
        return debtorNumber;
    }






    public static final Debtor createEmptyDebtor() {
        return new Debtor("", "", new Address("", "", "", ""));

    }

}

