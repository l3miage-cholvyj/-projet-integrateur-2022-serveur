
package com.example.Controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.example.model.Arret;
import com.example.model.Chami;
import com.example.model.Defi;
import com.example.repository.ChamisRepository;
import com.example.repository.DefisRepository;
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
import com.example.*;

@RestController
@CrossOrigin
@RequestMapping("/api/defis")
public class DefiCRUD {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private DefisRepository defiReposit;
    @Autowired
    private ChamisRepository chamiReposit;

    @GetMapping("/")
    public List<Defi> allDefis(HttpServletResponse response) {
        return defiReposit.findAll();
    }

    @GetMapping("/{defi}")
    public Defi read(@PathVariable(value = "defi") String id, HttpServletResponse response) {

        Defi defi = new Defi();
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
     * @param id       l'idenfiant du defi
     * @param def      defis à inserer
     * @param response la reponse du server
     * @return le defis
     */
    @PostMapping("/{defiId}")
    public Defi create(@PathVariable(value = "defiId") String email, @RequestBody Defi def,
            HttpServletResponse response) {
        System.out.println("=================methode post==================");
        Defi defi = new Defi();
        Chami cham = new Chami();
        Arret arret = new Arret();
        boolean isAuteur = new ChamiCRUD().isAuteur(def, response);
        boolean isArret = new ArretCRUD().isArret(def,response);
        if (defiReposit.findById(email).isPresent()) {
            // si l'identifiant existe deja
            response.setStatus(403);
        } else if (!def.getId().equals(email)) {
            // les id ne correspondent pas
            response.setStatus(412);
        } else if (isAuteur & isArret) {
            cham = def.getAuteur();
            arret = def.getArret();
            defi.setAuteur(cham);
            defi.setArret(arret);
            defi.setId(def.getId());
            defi.setTitre(def.getTitre());
            defi.setAuteur(def.getAuteur());
            defi.setDescription(def.getDescription());
            defi.setDateDeCreation(def.getDateDeCreation());
            defi.setMoyenneEvaluation(def.getMoyenneEvaluation());
            defiReposit.save(defi);

        } else {
            System.out.println("l'auteur n'a pas été trouvé");
            response.setStatus(404);
        }

        return defi;
    }

    @PutMapping("/{defiId}")
    public Defi update(@PathVariable(value = "defiId") String email, @RequestBody Defi def,
            HttpServletResponse response) {
        Defi defi = new Defi();
        Chami cham = new Chami();
        Arret arret = new Arret();

        //methodes qui permettent qui verifier s'arret et l'auteur existe deja dans la base
        //pour pouvoir faire une modification 
        boolean isAuteur = new ChamiCRUD().isAuteur(def, response);
        boolean isArret = new ArretCRUD().isArret(def,response);
        
        
        if ((defiReposit.findById(email).isPresent()) && isAuteur && isArret) {
            System.out.println("je suis dedans");
            //mettre à jour l'auteur et l'arret qui existe deja
            cham = def.getAuteur();
            arret = def.getArret();
            defi.setAuteur(cham);
            defi.setArret(arret);
            defi.setId(def.getId());
            defi.setTitre(def.getTitre());
            defi.setDescription(def.getDescription());
            defi.setDateDeCreation(def.getDateDeCreation());
            defi.setMoyenneEvaluation(def.getMoyenneEvaluation());
            defiReposit.save(defi);
        } else {
            response.setStatus(404);
        }
        return defi;
    }

    /**
     * 
     * @param email email d'un chami
     * @param response
     */
    @DeleteMapping("/{defiId}")
    public void delete(@PathVariable(value = "defiId") String email, HttpServletResponse response) {
        if (defiReposit.findById(email).isPresent()) {
            defiReposit.deleteById(email);
        } else {
            response.setStatus(404);
        }

    }

    /**
     * 
     * @param def represente le defi
     * @param response reponse server
     * @return vrai si l'auteur d'un chami existe
     */
  
    public boolean isDefi(Defi def, HttpServletResponse response) {
        boolean isDefi = false;
        if (read(def.getId(), response) != null) {
            isDefi = true;
        }
        return isDefi;
    }

    /**
     * 
     * @param titre    titre d'un defi
     * @param response repone du server
     * @return liste des defi dont le titre est donné
     */
    @GetMapping("titre/{titre}")
    public Set<Defi> getDefiByTitre(@PathVariable(value = "titre") String titre, HttpServletResponse response) {

        List<Defi> defis = new ArrayList<>();
        Set<Defi> listDefi = new HashSet<>();
        defis = allDefis(response);
        for (Defi defi : defis) {
            if (defi.getTitre().equalsIgnoreCase(titre)) {
                listDefi.add(defi);
            }
        }
        if (listDefi.isEmpty()) {
            response.setStatus(404);
        }
        return listDefi;
    }

    /**
     * 
     * @param login    login du chami
     * @param response reponse du server
     * @return liste de defi d'un chami donné
     */
    @GetMapping("chami/{login}")
    public Set<Defi> getDefiyByChami(@PathVariable(value = "chami") String login, HttpServletResponse response) {
        List<Defi> defis = new ArrayList<>();
        Set<Defi> listDefi = new HashSet<>();
        defis = allDefis(response);
        for (Defi defi : defis) {
            if (defi.getAuteur().getLogin().equalsIgnoreCase(login)) {
                listDefi.add(defi);
            }
        }
        if (listDefi.isEmpty()) {
            response.setStatus(404);
        }
        return listDefi;
    }

    /**
     * 
     * @param nomArret nom de l'arret qui correspondant à un defi donnée
     * @param response reponse du server
     * @return liste de defi avec avec comme arret nomArret
     */
    @GetMapping("arret/{nomArret}")
    public Set<Defi> getDefiyByArret(@PathVariable(value = "nomArret") String nomArret, HttpServletResponse response) {
        List<Defi> defis = new ArrayList<>();
        Set<Defi> listDefi = new HashSet<>();
        defis = allDefis(response);
        for (Defi defi : defis) {
            if (defi.getArret().getNom().equalsIgnoreCase(nomArret)) {
                listDefi.add(defi);
            }
        }
        if (listDefi.isEmpty()) {
            response.setStatus(404);
        }
        return listDefi;
    }

}
