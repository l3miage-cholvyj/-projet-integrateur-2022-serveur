package com.example.Controller;




import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.*;
import com.example.*;
import com.example.model.*;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.*;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

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
import com.example.repository.ArretRepository;

@RestController
@CrossOrigin
@RequestMapping("/api/arret")
public class ArretCRUD {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private ArretRepository arretReposit;


    @GetMapping("/")
    public List<Arret> allArret(HttpServletResponse response)throws SQLException {
        List<Arret> arrets = new ArrayList<>();
        try {
           arrets = arretReposit.findAll();
        } catch (Exception e) {
           e.printStackTrace();
        }
        return arrets;
    }

    // read
    // qui renvoi l'utilisateur dont le login est userID
    @GetMapping("/{arretId}")
    public Arret read(@PathVariable(value = "arretId") String id, HttpServletResponse response) throws SQLException {
        Arret arret = new Arret();
        if (arretReposit.findById(id).isPresent()) {
            arret = arretReposit.getById(id);
        } else {
            System.err.println("ce arret n'existe pas dans la base");
            response.setStatus(404);
        }
        return arret;
    }

    // methode qui permet de creer un nouvel u utilsateur
    @PostMapping("/{arretId}")
    public Arret create(@PathVariable(value = "arretId") String id, @RequestBody Arret u,
            HttpServletResponse response)throws SQLException {
        Arret arret = new Arret();

        System.out.println("voici le nom"+u.getNom());

        if (arretReposit.findById(id).isPresent()) {
            // si l'arret existe deja
            response.setStatus(403);

        } else if (!u.getNom().equals(id)) {
            // les id ne correspondent pas
            response.setStatus(412);
        } else {
            arret.setNom(u.getNom());
            arret.setNumeroLigne(u.getNumeroLigne());
            arret.setGps(u.getGps());
            arretReposit.save(arret);
        }
       
        return arret;
    }

    @PutMapping("/{arretId}")
    public Arret update(@PathVariable(value = "arretId") String id, @RequestBody Arret u,
            HttpServletResponse response)throws SQLException {
                Arret arret = new Arret();
                if (arretReposit.findById(id).isPresent()) {
                    arret.setNom(u.getNom());
                    arret.setNumeroLigne(u.getNumeroLigne());
                    arret.setGps(u.getGps());
                } else {
                    response.setStatus(404);
                }
      return arret;
    }

    @DeleteMapping("/{arretId}")
    public void delete(@PathVariable(value = "arretId") String id, HttpServletResponse response)throws SQLException {
        if (arretReposit.findById(id).isPresent()) {
            arretReposit.deleteById(id);
        } else {
            response.setStatus(404);
        }

    }

    public boolean findArret(Arret arret,HttpServletResponse response) throws SQLException{
        boolean isArret = false;
        if(read(arret.getNom(),response)!=null){
        isArret = true;
        }else{
            response.setStatus(404);
        }
        return isArret;
    }
    
}