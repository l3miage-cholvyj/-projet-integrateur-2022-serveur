package com.example.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.example.model.*;
import java.util.List;

import com.fasterxml.jackson.core.sym.Name;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)

public class Arret {

    @Id
    @Column(name = "nom", unique = true, nullable = false, updatable = true)
    private String nom;

    @Column(name = "numeroLigne", updatable = true, insertable = true)
    private String numeroLigne;

    @Column(name = "gps", updatable = true, insertable = true)
    private String gps;
    
    @OneToMany(mappedBy = "arret")
    private List<Defi> defi;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNumeroLigne() {
        return numeroLigne;
    }

    public void setNumeroLigne(String numeroLigne) {
        this.numeroLigne = numeroLigne;
    }

    public String getGps() {
        return gps;
    }

    public void setGps(String gps) {
        this.gps = gps;
    }

    public List<Defi> getDefi() {
        return defi;
    }

    public void setDefi(List<Defi> defi) {
        this.defi = defi;
    }

}
