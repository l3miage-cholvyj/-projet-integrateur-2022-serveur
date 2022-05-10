package com.example.repository;

import com.example.model.Arret;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface ArretRepository extends JpaRepository<Arret,String> {
    
    //@Query("select * from arret where numero_line =:numberLine")
   // Arret findStopByNumberLine(@Param("numberLine") String numberLine);
}
