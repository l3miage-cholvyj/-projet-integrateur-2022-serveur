package com.example.Controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.*;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.example.model.Reponse;
import com.example.repository.EtapeRepository;
import com.example.repository.ReponseRepository;

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
@RequestMapping("/api/reponses")

public class ReponseCRUD {
    @Autowired
    private ReponseRepository reponseReposit;
    
    
    @GetMapping("/")
    public List<Reponse> allreponse(HttpServletResponse response){
    return reponseReposit.findAll();
    }

    //retourne une reponse en fonction de la visite de l'id  
    @GetMapping("/reponse/{idVisite}")
    public Set<Reponse> findReponeByIdVisite(@PathVariable(value = "idVisite") int idVisite,HttpServletResponse response){
        List<Reponse> reponses = new ArrayList<>();
        Set<Reponse> listReponse = new HashSet<>();
        reponses = allreponse(response);
        for (Reponse rep : reponses) {
            if(rep.getIdVisite()==idVisite){
                listReponse.add(rep);
            }
        }if(listReponse.isEmpty()) response.setStatus(404);
        return listReponse;
    }   

  /*   @PostMapping("/{idEtape}/{idVisite}"})
    public Reponse create(@PathVariable(value = "idEtape" @PathVariable value="idVisite") int idEtape, int idVisite, 
    @RequestBody Reponse u,HttpServletResponse response)throws SQLException {

        Reponse reponse  = new Reponse();
      
    if (reponseReposit.findById(idEtape).isPresent() && responseReposit.findId(idVisite)) {
        response.setStatus(404);
    }else{
       response. 
         
    }
*/

    
}
   
