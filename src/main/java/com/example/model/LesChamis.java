package com.example.model;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "les_chamis")
public class LesChamis {

    @Id 
    @Column(name = "login",nullable = false, updatable = true )
    private String login;

    @Column(name = "age",nullable = false, updatable = true,insertable = true)
    private int age;

   // @OneToMany(mappedBy="chami",cascade = CascadeType.ALL)
   // private List<LesDefis> defiesCrees;


    
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


  
    
    @Override
    public String toString() {
        return "LesChamis [age=" + age + ", login=" + login + "]";
    }
    
}
