
package com.example.Controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.example.model.LesChamis;
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
public class LesChamisCRUD {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private LesChamisRepository chamiReposit;

    // read All

    @GetMapping("/")
    public List<LesChamis> allUsers(HttpServletResponse response) {
        return  chamiReposit.findAll();
    }

    // read
    // qui renvoi l'utilisateur dont le login est userID
    @GetMapping("/{userId}")
    public LesChamis read(@PathVariable(value = "userId") String id, HttpServletResponse response) throws SQLException {

        LesChamis chamis = new LesChamis();
        if (chamiReposit.findById(id).isPresent()) {
            chamis = chamiReposit.getById(id);
        } else {
            System.out.println("le truc recherché n'est pas trouvé");
            response.setStatus(404);
        }
        return chamis;
    }

    // methode qui permet de creer un nouvel u utilsateur
    @PostMapping("/{userId}")
    public LesChamis create(@PathVariable(value = "userId") String id, @RequestBody LesChamis u, HttpServletResponse response) {
        LesChamis chami = new LesChamis();

        if (chamiReposit.findById(id).isPresent()) {
            // si l'identifiant existe deja
            response.setStatus(403);
        } else if (!u.getLogin().equals(id)) {
            // les id ne correspondent pas
            response.setStatus(412);
        } else {
            chami.setAge(u.getAge());
            chami.setLogin(u.getLogin());
            chamiReposit.save(chami);
        }
        return chami;
    }

    @PutMapping("/{userId}")
    public LesChamis update(@PathVariable(value = "userId") String id, @RequestBody LesChamis u, HttpServletResponse response) {
        LesChamis cham = new LesChamis();
        if (chamiReposit.findById(id).isPresent()) {
            cham.setAge(u.getAge());
            cham.setLogin(u.getLogin());
            chamiReposit.save(cham);
        } else {
            response.setStatus(404);
        }
        return cham;
    }

    @DeleteMapping("/{userId}")
    public void delete(@PathVariable(value = "userId") String id, HttpServletResponse response) {
        if(chamiReposit.findById(id).isPresent()){
            chamiReposit.deleteById(id);
        }else{
            response.setStatus(404);
        }
        
    }
}