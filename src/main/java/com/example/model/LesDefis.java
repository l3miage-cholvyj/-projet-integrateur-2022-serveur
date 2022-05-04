package com.example.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.core.sym.Name;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;



@Entity
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class)
public class LesDefis{

    @Id
    @Column(name = "id", nullable = false,updatable = true)
    private String id;

    @Column(name = "titre",nullable = false, updatable = true, insertable = true)
    private String titre;

    //@Column(name = "auteur",nullable = false, updatable = true,insertable = true)
   // private String auteur;

    @Column(name = "datedecreation",nullable = true, updatable = true,insertable = true)
    private Date dateDeCreation;

    @Column(name = "description",nullable = true, updatable = true,insertable = true)
    private String description;

     @ManyToOne()
   // @JoinColumn(name="id")
     private LesChamis auteur;

    public LesDefis(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public LesChamis getAuteur() {
        return auteur;
    }

    public void setAuteur(LesChamis auteur) {
        this.auteur = auteur;
    }

    public Date getDateDeCreation() {
        return dateDeCreation;
    }


    public void setDateDeCreation(Date dateDeCreation) {
        this.dateDeCreation = dateDeCreation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    

}