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
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class Etape implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_etape", nullable = false, updatable = true)
    private int idEtape;

    @Column(name = "gps", updatable = true)
    private String gps;

    @Column(name = "numetape", nullable = false, updatable = true, insertable = true)
    private String numEtape;

    @Column(name = " nbpoints", updatable = true, insertable = true)
    private int nbPoints;

    @Column(name = " media", nullable = true)
    private String media;

    @Column(name = "messagedebut", updatable = true, insertable = true)
    private String messageDebut;
    @Column(name = "messageFin", updatable = true, insertable = true)
    private String messageFin;

    @Column(name = "question", updatable = true, insertable = true)
    private String question;

    @Column(name = "reponse", updatable = true, insertable = true)
    private String reponse;

    @Column(name = "qcmchoixun", updatable = true, insertable = true)
    private String qcmChoixUn;

    @Column(name = "qcmchoixdeux", updatable = true, insertable = true)
    private String qcmChoixDeux;

    @Column(name = "qcmChoixtrois", updatable = true, insertable = true)
    private String qcmChoixTrois;

    @Column(name = "qcmChoixquatre", updatable = true, insertable = true)
    private String qcmChoixQuatre;

    @Column(name = "indice", updatable = true, insertable = true)
    private String indice;

    @ManyToOne
    private Defi defi;

    @OneToMany(mappedBy = "etape")
    private List<Reponse> reponseEtape;

    @OneToMany(mappedBy = "visite")
    private List<Reponse> reponseVisite;

    @JsonSerialize
    private transient int nbrEtapePourDefi;

    public int getId() {
        return idEtape;
    }

    public void setId(int id) {
        this.idEtape = id;
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

    public int getNbPoints() {
        return nbPoints;
    }

    public void setNbPoints(int nbPoints) {
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
        return qcmChoixDeux;
    }

    public void setQcmchoixDeux(String qcmChoixDeux) {
        this.qcmChoixDeux = qcmChoixDeux;
    }

    public String getQcmChoiTrois() {
        return qcmChoixTrois;
    }

    public void setQcmChoiTrois(String qcmChoixTrois) {
        this.qcmChoixTrois = qcmChoixTrois;
    }

    public String getQcmchoixQuatre() {
        return qcmChoixQuatre;
    }

    public void setQcmchoixQuatre(String qcmChoixQuatre) {
        this.qcmChoixQuatre = qcmChoixQuatre;
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