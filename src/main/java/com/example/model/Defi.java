package com.example.model;

import java.sql.Date;
import java.util.List;

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
@Table(name = "defi")
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class)
public class Defi{

    @Id
    @Column(name = "id", nullable = false,updatable = true)
    private String id;

    @Column(name = "description",nullable = true, updatable = true,insertable = true)
    private String description;

    @Column(name = "titre",nullable = false, updatable = true, insertable = true)
    private String titre;

    @Column(name = "datedecreation",nullable = true, updatable = true,insertable = true)
    private Date dateDeCreation;


    @Column(name = "avgevaluation",nullable = true, updatable = true,insertable = true)
    private Double avgEvaluation ;


    @ManyToOne()
    private Arret arret;

    @OneToMany(mappedBy = "defi")
    private List<Etape> EtapeDunDefi;
    
    @ManyToOne()
    private Chami auteur;

    @OneToMany(mappedBy = "defiVisite")
    private List<Visite> visite;

    public List<Etape> getEtapeDunDefi() {
        return EtapeDunDefi;
    }


    public void setEtapeDunDefi(List<Etape> etapeDunDefi) {
        EtapeDunDefi = etapeDunDefi;
    }


    public Arret getArret() {
        return arret;
    }


    public void setArret(Arret arret) {
        this.arret = arret;
    }

   


    public Defi(){}


    public Double getAvgEvaluation() {
        return avgEvaluation;
    }

    public void setAvgEvaluation(Double avgEvaluation) {
        this.avgEvaluation = avgEvaluation;
    }

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

    public Chami getAuteur() {
        return auteur;
    }

    public void setAuteur(Chami auteur) {
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


    public List<Visite> getVisite() {
        return visite;
    }


    public void setVisite(List<Visite> visite) {
        this.visite = visite;
    }


   

   
    

}