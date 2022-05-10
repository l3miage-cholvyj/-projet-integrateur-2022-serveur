package com.example.Controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.*;
import com.example.*;
import javax.persistence.EntityManager;
import javax.persistence.*;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.example.model.Defi;
import com.example.model.Visite;

import java.util.List;
import com.example.repository.*;
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
@RequestMapping("/api/visites")

public class VisiteCRUD {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private VisiteRepository visiteReposit;
    // @Autowired
    // private DefiRepository defiReposit;
    // @Autowired
    // private ChamiRepository chamiReposit;

    @GetMapping("/")
    public List<Visite> allVisite(HttpServletResponse response) throws SQLException {
        List<Visite> visites = new ArrayList<>();
        try {
            visites = visiteReposit.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return visites;
    }

    @GetMapping("/{visitetId}")
    public Visite read(@PathVariable(value = "visiteId") int id, HttpServletResponse response) throws SQLException {
        Visite visite = new Visite();
        if (visiteReposit.findById(id).isPresent()) {
            visite = visiteReposit.getById(id);
        } else {
            System.err.println("cette visite n'existe pas dans la base");
            response.setStatus(404);
        }
        return visite;
    }

    // methode qui permet de creer un nouvel u utilsateur
    @PostMapping("/{visiteId}")
    public Visite create(@PathVariable(value = "visiteId") int id, @RequestBody Visite visit,
            HttpServletResponse response) throws SQLException {

        Visite visite = new Visite();
        boolean isDefi = false; // new DefiCRUD().isDefi(visite.getDefi(),response);
        if (visiteReposit.findById(id).isPresent()) {
            // si la visite existe deja
            response.setStatus(403);

        } else if (visit.getId() != id) {
            // les id ne correspondent pas
            response.setStatus(412);
        } else if (isDefi) {
            visite.setDate(visit.getDate());
            visite.setEstfinie(visit.isEstfinie());
            visite.setEvaluation(visit.getEvaluation());
            visite.setScore(visit.getScore());
            visite.setTemps(visit.getTemps());
            visite.setCommentaire(visit.getCommentaire());
            // visite.setDefi(visit.getDefi());
            visiteReposit.save(visite);
        }

        return visite;
    }

    @PutMapping("/{visiteId}")
    public Visite update(@PathVariable(value = "visiteId") int id, @RequestBody Visite visit,
            HttpServletResponse response) throws SQLException {
        Visite visite = new Visite();

        if (visiteReposit.findById(id).isPresent()) {
            visite.setId(visit.getId());
            visite.setDate(visit.getDate());
            visite.setEstfinie(visit.isEstfinie());
            visite.setEvaluation(visit.getEvaluation());
            visite.setScore(visit.getScore());
            visite.setTemps(visit.getTemps());
            visite.setCommentaire(visit.getCommentaire());
            // visite.setDefi(visit.getDefi());

        } else {
            response.setStatus(404);
        }
        return visite;
    }

    @DeleteMapping("/{visiteId}")
    public void delete(@PathVariable(value = "visiteId") int id, HttpServletResponse response) throws SQLException {
        if (visiteReposit.findById(id).isPresent()) {
            visiteReposit.deleteById(id);
        } else {
            response.setStatus(404);
        }
    }

    @GetMapping("chami/{login}")
    public Set<Visite> getDefiyByArret(@PathVariable(value = "login") String login, HttpServletResponse response)
            throws SQLException {
        List<Visite> visites = new ArrayList<>();
        Set<Visite> listVisite = new HashSet<>();
        visites = allVisite(response);
        for (Visite visite : visites) {
            if (visite.getVisiteur().getLogin().equalsIgnoreCase(login)) {
                listVisite.add(visite);
            }
        }
        if (listVisite.isEmpty()) {
            response.setStatus(404);
        }
        return listVisite;
    }
}