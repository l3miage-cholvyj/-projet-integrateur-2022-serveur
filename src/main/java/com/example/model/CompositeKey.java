package com.example.model;

import java.io.Serializable;

public class CompositeKey implements Serializable{

    private int idEtape;
    private int idVisite;


   
    @Override
    public int hashCode() {
    
        return super.hashCode();
    }
    
    @Override
    public boolean equals(Object obj) {

        return super.equals(obj);
    }
}
