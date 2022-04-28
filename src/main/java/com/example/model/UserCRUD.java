package com.example.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.example.classe.User;

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
public class UserCRUD {
    @Autowired
    private DataSource dataSource;


    //read All
    @GetMapping("/")
    public ArrayList<User> allUsers(HttpServletResponse response) throws SQLException{
        try (Connection connection = dataSource.getConnection()){
            Statement stmt = connection.createStatement();
            ResultSet rs  = stmt.executeQuery("SELECT * FROM lesChamis");

            ArrayList<User> L = new ArrayList<User>();
            while(rs.next()){
                User u = new User();
                u.login = rs.getString("login");
                u.age =  rs.getInt("age");
                L.add(u);
            }return L;
        }catch(Exception e){
            response.setStatus(500);
            try{
                response.getOutputStream().print(e.getMessage());
            }catch(Exception e2){
                System.err.print(e2.getMessage());
            }System.err.print(e.getMessage());
          return null;
        }
    
    }

    //read
    //qui renvoi l'utilisateur dont le login est userID
    @GetMapping("/{userId}")
    public User read(@PathVariable(value="userId") String id, HttpServletResponse response) throws SQLException{

        try (Connection connection = dataSource.getConnection()){
            Statement stmt = connection.createStatement();
            ResultSet rs  = stmt.executeQuery("SELECT * FROM users");
            User user;
            while(rs.next()){
                String use = rs.getString("id");
                if(id.equals(use)){
                    user = new User();
                    user.age = rs.getInt("age");
                    user.login = rs.getString("login");
                }
            }return null;
        
        }catch(Exception e){
            response.setStatus(404);
            try{
                response.getOutputStream().print(e.getMessage());
            }catch(Exception e2){
                System.err.print(e2.getMessage());
            }System.err.print(e.getMessage());
          return null;
        }
    
    }

    //methode qui permet de creer un nouvel u utilsateur
    @PostMapping("/{userId}")
    public User create(@PathVariable(value="userId") String id, User u, HttpServletResponse response){

        try (Connection connection = dataSource.getConnection()){
            Statement stmt = connection.createStatement();
            ResultSet rss  = stmt.executeQuery("SELECT * FROM users");
            while(rss.next()){
              String identifiant = rss.getString("id");
              if(id.equals(identifiant)){
                  //si la ressource existe déja 
                  response.setStatus(403);
              //TODO l'erreur 412 à prendre en compte
              }else {
                ResultSet rs  = stmt.executeQuery("INSERT INTO users values("+u.age+","+u.login+")");
              }
            }//A voir
            }catch(Exception e){
                response.setStatus(412);
            }
            //retour à voir
       return null;
    }

    @PutMapping("/{userId}")
    public User update(@PathVariable(value="userId") String id, @RequestBody User u, HttpServletResponse response) throws SQLException{

        try (Connection connection = dataSource.getConnection()){
            Statement stmt = connection.createStatement();
            ResultSet rss  = stmt.executeQuery("SELECT * FROM users");
            while(rss.next()){
              String identifiant = rss.getString("id");
              if(id.equals(identifiant)){
                  //si la ressource existe déja 
                 stmt.executeQuery("UPDATE users set login="+u.login+","+"age="+u.age);
              //TODO l'erreur 412 à prendre en compte
              }else {
                response.setStatus(404);
               
              }
            }//A voir
        
            //retour à voir
    }

        return null;
    }



@DeleteMapping("/{userId}")
   public void delete(@PathVariable(value="userId") String id, HttpServletResponse response){

    try (Connection connection = dataSource.getConnection()){
        Statement stmt = connection.createStatement();
        ResultSet rs  = stmt.executeQuery("SELECT * FROM users");
        while(rs.next()){
             String identifiant = rs.getString("id");
             if(identifiant.equals(id)){
                  stmt.executeQuery("DELETE FROM User WHERE id="+id);
             }else{
                response.setStatus(404);
             }
        }

    }catch(Exception e){
        System.err.print(e.getMessage());
    }
}
}