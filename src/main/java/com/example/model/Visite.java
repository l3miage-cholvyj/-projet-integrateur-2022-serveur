package com.example.model;

import javax.persistence.*;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.*;
import com.example.model.*;
import java.sql.Date;

import java.beans.Transient;
import java.io.Serializable;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class Visite implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_visite", nullable = false, updatable = true)
    private int idVisite;

    @Column(name = "date", nullable = false, updatable = true)
    private Date date;

    @Column(name = "estfinie", nullable = false, updatable = true, insertable = true)
    private boolean estfinie;

    @Column(name = "evaluation", nullable = true, updatable = true, insertable = true)
    private int evaluation;

    @Column(name = "score", nullable = true, updatable = true, insertable = true)
    private int score;

    @Column(name = "temps", nullable = false, updatable = true, insertable = true)
    private int temps;

    @Column(name = "commentaire", nullable = true, updatable = true, insertable = true)
    private String commentaire;

    @ManyToOne()
    private Defi defiVisite;

    @ManyToOne()
    private Chami visiteur;



    public int getId() {
        return idVisite;
    }

    public void setId(int id) {
        this.idVisite = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isEstfinie() {
        return estfinie;
    }

    public void setEstfinie(boolean estfinie) {
        this.estfinie = estfinie;
    }

    public int getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(int evaluation) {
        this.evaluation = evaluation;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getTemps() {
        return temps;
    }

    public void setTemps(int temps) {
        this.temps = temps;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public Defi getDefiVisite() {
        return defiVisite;
    }

    public void setDefiVisite(Defi defiVisite) {
        this.defiVisite = defiVisite;
    }

    public Chami getVisiteur() {
        return visiteur;
    }

    public void setVisiteur(Chami visiteur) {
        this.visiteur = visiteur;
    }

  
    @Override
    public boolean equals(Object obj) {
        // TODO Auto-generated method stub
        return super.equals(obj);
    }
     @Override
     public int hashCode() {
         // TODO Auto-generated method stub
         return super.hashCode();
     }
}
