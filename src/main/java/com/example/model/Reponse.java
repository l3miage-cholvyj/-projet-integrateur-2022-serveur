package com.example.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;

@Entity
@IdClass(CompositeKey.class)
public class Reponse implements Serializable{
    
    @Id
    @Column(name = "id_etape" ,nullable=false)
    private int idEtape;

    @Id 
    @Column(name="id_visite",nullable = false)
    private int idVisite;

    @Column(name="reponse_joueur", nullable = false)
    private String ReponseJoueur;
     
    @ManyToOne
    private Etape etape;

    @ManyToOne
    private Visite visite;

    public int getIdEtape() {
        return idEtape;
    }

    public void setIdEtape(int idEtape) {
        this.idEtape = idEtape;
    }

    public int getIdVisite() {
        return idVisite;
    }

    public void setIdVisite(int idVisite) {
        this.idVisite = idVisite;
    }

    public String getReponseJoueur() {
        return ReponseJoueur;
    }

    public void setReponseJoueur(String reponseJoueur) {
        ReponseJoueur = reponseJoueur;
    }

    public Etape getEtape() {
        return etape;
    }

    public void setEtape(Etape etape) {
        this.etape = etape;
    }

    public Visite getVisite() {
        return visite;
    }

    public void setVisite(Visite visite) {
        this.visite = visite;
    }


    


}
