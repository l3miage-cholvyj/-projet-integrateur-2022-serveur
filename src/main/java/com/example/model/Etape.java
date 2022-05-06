package com.example.model;
import javax.persistence.*;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.*;
import com.example.model.Defi;

import java.beans.Transient;
import java.io.Serializable;

@Entity
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class)
public class Etape implements Serializable {


    @Id @GeneratedValue()
    @Column(name = "id",nullable = false, updatable = true )
    private int id;

    @Column(name = "gps", updatable = true )
    private String gps;

    @Column(name = "numetape",nullable = false, updatable = true,insertable = true)
    private String numEtape;

    @Column(name = " nbpoints",updatable = true,insertable = true)
    private String nbPoints;

   @Column(name = "messagedebut", updatable = true,insertable = true)
    private String messageDebut;
   @Column(name = "messageFin", updatable = true,insertable = true)
    private String messageFin;

    @Column(name = "question", updatable = true,insertable = true)
    private String question;

    @Column(name = "reponse", updatable = true,insertable = true)
    private String reponse;

    @Column(name = "qcmchoixun", updatable = true,insertable = true)
    private String qcmChoixUn;

    @Column(name = "qcmchoixdeux", updatable = true,insertable = true)
    private String qcmchoixDeux;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGps() {
        return gps;
    }

    public void setGps(String gps) {
        this.gps = gps;
    }

    public String getNumEtape() {
        return numEtape;
    }

    public void setNumEtape(String numEtape) {
        this.numEtape = numEtape;
    }

    public String getNbPoints() {
        return nbPoints;
    }

    public void setNbPoints(String nbPoints) {
        this.nbPoints = nbPoints;
    }

    public String getMessageDebut() {
        return messageDebut;
    }

    public void setMessageDebut(String messageDebut) {
        this.messageDebut = messageDebut;
    }

    public String getMessageFin() {
        return messageFin;
    }

    public void setMessageFin(String messageFin) {
        this.messageFin = messageFin;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getReponse() {
        return reponse;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }

    public String getQcmChoixUn() {
        return qcmChoixUn;
    }

    public void setQcmChoixUn(String qcmChoixUn) {
        this.qcmChoixUn = qcmChoixUn;
    }

    public String getQcmchoixDeux() {
        return qcmchoixDeux;
    }

    public void setQcmchoixDeux(String qcmchoixDeux) {
        this.qcmchoixDeux = qcmchoixDeux;
    }

    public String getQcmChoiTrois() {
        return qcmChoiTrois;
    }

    public void setQcmChoiTrois(String qcmChoiTrois) {
        this.qcmChoiTrois = qcmChoiTrois;
    }

    public String getQcmchoixQuatre() {
        return qcmchoixQuatre;
    }

    public void setQcmchoixQuatre(String qcmchoixQuatre) {
        this.qcmchoixQuatre = qcmchoixQuatre;
    }

    public String getIndice() {
        return indice;
    }

    public void setIndice(String indice) {
        this.indice = indice;
    }

    public Defi getDefi() {
        return defi;
    }

    public void setDefi(Defi defi) {
        this.defi = defi;
    }

    public int getNbrEtapePourDefi() {
        return nbrEtapePourDefi;
    }

    public void setNbrEtapePourDefi(int nbrEtapePourDefi) {
        this.nbrEtapePourDefi = nbrEtapePourDefi;
    }

    @Column(name = "qcmchoixtrois",updatable = true,insertable = true)
    private String qcmChoiTrois;

    @Column(name = "qcmchoixquatre", updatable = true,insertable = true)
    private String qcmchoixQuatre;

    @Column(name = "indice", updatable = true,insertable = true)
    private String indice;
    
    @ManyToOne
    private Defi defi;

    @JsonSerialize
    private transient int nbrEtapePourDefi;


   
}