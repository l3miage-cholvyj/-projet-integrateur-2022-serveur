
package com.example.Controller;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.example.model.LesChamis;
import com.example.model.LesDefis;
import com.google.cloud.storage.Acl.Entity;

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
@RequestMapping("/api/defis")
public class LesDefisCRUD {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private LesDefisRepository defiReposit;

    // read All

    @GetMapping("/")
    public List<LesDefis> allDefis(HttpServletResponse response) {
        System.out.println("i m here=========================================");
        return defiReposit.findAll();
    }

    @GetMapping("/{defi}")
    public LesDefis read(@PathVariable(value = "defi") String id, HttpServletResponse response) {

        LesDefis defi = new LesDefis();
        if (defiReposit.findById(id).isPresent()) {
            defi = defiReposit.getById(id);
        } else {
            System.err.println("ce defit n'existe pas");
            response.setStatus(404);
        }
        return defi;
    }
    /**
     * 
     * @param id l'idenfiant du defi
     * @param def defis à inserer 
     * @param response  la reponse du server
     * @return le defis 
     */
    @PostMapping("/{defiId}")
    public LesDefis create(@PathVariable(value = "defiId") String id, @RequestBody LesDefis def,HttpServletResponse response) {
        System.out.println("=================methode post==================");
        LesDefis defi = new LesDefis();
        if (defiReposit.findById(id).isPresent()) {
            // si l'identifiant existe deja
            response.setStatus(403);
        } else if (!def.getId().equals(id)) {
            // les id ne correspondent pas
            response.setStatus(412);
        } else {
            defi.setId(def.getId());
            defi.setTitre(def.getTitre());
            defi.setAuteur(def.getAuteur());
            defi.setDescription(def.getDescription());
            defi.setDateDeCreation(def.getDateDeCreation());
            defiReposit.save(defi);
            
        }
        return defi;
    }

    @PutMapping("/{defiId}")
    public LesDefis update(@PathVariable(value = "defiId") String id, @RequestBody LesDefis def, HttpServletResponse response) {
        LesDefis defi = new LesDefis();
        LesChamis cham = new LesChamis();
        if (defiReposit.findById(id).isPresent()) {
            defi.setId(def.getId());
            defi.setTitre(def.getTitre());
            cham.setLogin(def.getAuteur().getLogin());
            defi.setAuteur(cham);
            defi.setDescription(def.getDescription());
            defi.setDateDeCreation(def.getDateDeCreation());
        } else {
            response.setStatus(404);
        }
        return defi;
    }

    @DeleteMapping("/{defiId}")
    public void delete(@PathVariable(value = "defi") String id, HttpServletResponse response) {
        if(defiReposit.findById(id).isPresent()){
            defiReposit.deleteById(id);
        }else{
            response.setStatus(404);
        }
        
    }

}