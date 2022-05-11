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
import java.util.List;
import com.example.repository.EtapeRepository;

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

import com.example.model.Etape;

@RestController
@CrossOrigin
@RequestMapping("/api/etapes")

public class EtapeCRUD {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private EtapeRepository etapeReposit;

    @GetMapping("/")
    public List<Etape> allEtape(HttpServletResponse response) throws SQLException {
        List<Etape> etapes = new ArrayList<>();
        try {
            etapes = etapeReposit.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return etapes;
    }

    @GetMapping("/{etapeId}")
    public Etape read(@PathVariable(value = "etapeId") int id, HttpServletResponse response) throws SQLException {
        Etape etape = new Etape();
        if (etapeReposit.findById(id).isPresent()) {
            etape = etapeReposit.getById(id);
        } else {
            response.setStatus(404);
        }
        return etape;
    }
 


    @PostMapping("/{etapeId}")
    public Etape create(@PathVariable(value = "etapeId") int id, @RequestBody Etape etape,
            HttpServletResponse response) {

        Etape etap = new Etape();
        boolean isDefi = new DefiCRUD().isDefi(new Etape().getDefi(), response);
        // On verfie si l'identifiant existe
        if (etapeReposit.findById(id).isPresent()) {
            response.setStatus(403);

        } else if (!etapeReposit.findById(id).isPresent()) {
            response.setStatus(412);
        } else if (isDefi) {
            etap.setGps(etape.getGps());
            etap.setNumEtape(etape.getNumEtape());
            etap.setNbPoints(etape.getNbPoints());
            etap.setMessageDebut(etape.getMessageDebut());
            etap.setMessageFin(etape.getMessageFin());
            etap.setQuestion(etape.getQuestion());
            etap.setReponse(etape.getReponse());
            etap.setQcmChoixUn(etape.getQcmChoixUn());
            etap.setQcmchoixDeux(etape.getQcmchoixDeux());
            etap.setQcmChoiTrois(etape.getQcmChoiTrois());
            etap.setQcmchoixQuatre(etape.getQcmchoixQuatre());
            etap.setIndice(etape.getIndice());
            etapeReposit.save(etap);
        } else {
            System.out.println("L'etape n'a pas été trouvé");
            response.setStatus(404);
        }
        return etap;
    }

    @PutMapping("/{etapeId}")
    public Etape update(@PathVariable(value = "etapeId") int id, @RequestBody Etape etape, HttpServletResponse response)
            throws SQLException {

        Etape etap = new Etape();
        if (etapeReposit.findById(id).isPresent()) {
            etap.setGps(etape.getGps());
            etap.setNbPoints(etape.getNbPoints());
            etap.setMessageDebut(etape.getMessageDebut());
            etap.setMessageFin(etape.getMessageFin());
            etap.setQuestion(etape.getQuestion());
            etap.setReponse(etape.getReponse());
            etap.setQcmChoixUn(etape.getQcmChoixUn());
            etap.setQcmchoixDeux(etape.getQcmchoixDeux());
            etap.setQcmChoiTrois(etape.getQcmChoiTrois());
            etap.setQcmchoixQuatre(etape.getQcmchoixQuatre());
            etap.setIndice(etape.getIndice());
            etapeReposit.save(etap);
        } else {
            response.setStatus(404);
        }

        return etap;
    }

    @DeleteMapping("/{etapeId}")
    public void delete(@PathVariable(value = "etapeId") int id, HttpServletResponse response) throws SQLException {
        if (etapeReposit.findById(id).isPresent()) {
            etapeReposit.deleteById(id);
        } else {
            response.setStatus(404);
        }
        return;
    }

    @GetMapping("/deftitre/{deftitre}")
    public Set<Etape> findReponeByIdVisite(@PathVariable(value = "deftitre") String titre, HttpServletResponse response)
            throws SQLException {
        List<Etape> etapes = new ArrayList<>();
        Set<Etape> etapeList = new HashSet<>();
        etapes = allEtape(response);
        for (Etape etap : etapes) {
            if (etap.getDefi().getTitre().equalsIgnoreCase(titre)) {
                etapeList.add(etap);
            }
        }
        if (etapeList.isEmpty())
            response.setStatus(404);
        return etapeList;
    }

}