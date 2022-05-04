package com.example.model;

import javax.persistence.*;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;



@Entity
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class)
public class LesChamis {


    @Id 
    @Column(name = "login",nullable = false, updatable = true )
    private String login;

    @Column(name = "age",nullable = false, updatable = true,insertable = true)
    private int age;

    @OneToMany(mappedBy="auteur")
    public List<LesDefis> defiesCrees;


    public LesChamis(){}
    
    public LesChamis(String login, int age) {
       setLogin(login);
       setAge(age);
    }
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
/*
    public void setDefiesCrees(LesDefis def){
        this.defiesCrees = def;
    }
    public LesDefis setDefLesDefis(){
        return defiesCrees;
    }
    */
    


}
