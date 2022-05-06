package com.example.model;

import javax.persistence.*;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.ObjectMapper;




@Entity
@Table(name="chami")
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class)
public class Chami {


    @Id 
    @Column(name = "email",nullable = false, updatable = true )
    private String email;

    @Column(name = "login",nullable = false, updatable = true,insertable = true)
    private String login;
    
    @Column(name = "age",nullable = true, updatable = true,insertable = true)
    private int age;


    @Column(name = "description",nullable = true, updatable = true,insertable = true)
    private String description;

    @Column(name = "ville",nullable = true, updatable = true,insertable = true)
    private String ville;

    @OneToMany(mappedBy="auteur",cascade = CascadeType.ALL)
    public List<Defi> defiesCrees;


   
    @Transient
    private int nbreDefis;


    public Chami(){}

    public int getAge() {
        return age;
    }


    public int getNbreDefis() {
        return nbreDefis;
    }

    public void setNbreDefis(int nbreDefis) {
        this.nbreDefis = nbreDefis;
    }

    public void setAge(int age) {
        this.age = age;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public String getLogin() {
        return login;
    }


    public void setLogin(String login) {
        this.login = login;
    }


    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    public String getVille() {
        return ville;
    }


    public void setVille(String ville) {
        this.ville = ville;
    }


    public List<Defi> getDefiesCrees() {
        return defiesCrees;
    }


    public void setDefiesCrees(List<Defi> defiesCrees) {
        this.defiesCrees = defiesCrees;
    }
    
   


}