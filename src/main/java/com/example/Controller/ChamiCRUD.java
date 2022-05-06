
package com.example.Controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.*;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;


import com.example.model.Chami;
import com.example.repository.ChamisRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/users")
public class ChamiCRUD {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private ChamisRepository chamiReposit;

    // read All

    @GetMapping("/")
    public List<Chami> allUsers(HttpServletResponse response) {
       //System.out.println("==========="+ new LesChamis().getDefiesCrees().size());

        List<Chami> chamis = new ArrayList<>();
        try {
            chamis = chamiReposit.findAll();
        } catch (EntityNotFoundException e) {
        }
       
        for (Chami ch : chamis ){
             ch.setNbreDefis(ch.getDefiesCrees().size());
             ch.getNbreDefis();
            
        }
        return chamis;
    }

    // read
    // qui renvoi l'utilisateur dont le login est userID
    @GetMapping("/{userId}")
    public Chami read(@PathVariable(value = "userId") String email, HttpServletResponse response) throws SQLException {

        Chami chamis = new Chami();
        if (chamiReposit.findById(email).isPresent()) {
            chamis = chamiReposit.getById(email);
        } else {
            System.out.println("le truc recherché n'est pas trouvé");
            response.setStatus(404);
        }
        return chamis;
    }

    // methode qui permet de creer un nouvel u utilsateur
    @PostMapping("/{userId}")
    public Chami create(@PathVariable(value = "userId") String email, @RequestBody Chami u,
            HttpServletResponse response) {
        Chami chami = new Chami();

        System.out.println("voici le login"+u.getLogin());

        if (chamiReposit.findById(email).isPresent()) {
            // si l'identifiant existe deja
            response.setStatus(403);

        } else if (!u.getEmail().equals(email)) {
            // les id ne correspondent pas
            response.setStatus(412);
        } else {
            chami.setEmail(u.getEmail());
            chami.setLogin(u.getLogin());
            chami.setAge(u.getAge());
            chami.setDescription(u.getDescription());
            chami.setVille(u.getVille());
            chamiReposit.save(chami);
        }
        return chami;
    }

    @PutMapping("/{userId}")
    public Chami update(@PathVariable(value = "userId") String email, @RequestBody Chami u,
            HttpServletResponse response) {
        Chami chami = new Chami();
        if (chamiReposit.findById(email).isPresent()) {
            chami.setEmail(u.getEmail());
            chami.setLogin(u.getLogin());
            chami.setAge(u.getAge());
            chami.setDescription(u.getDescription());
            chami.setVille(u.getVille());
            chamiReposit.save(chami);
        } else {
            response.setStatus(404);
        }
        return chami;
    }

    @DeleteMapping("/{userId}")
    public void delete(@PathVariable(value = "userId") String id, HttpServletResponse response) {
        if (chamiReposit.findById(id).isPresent()) {
            chamiReposit.deleteById(id);
        } else {
            response.setStatus(404);
        }

    }
}