package com.saveyaar.saveyaar_movies.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.saveyaar.saveyaar_movies.model.Ott;

public interface OttRepository extends JpaRepository<Ott, Long>{
    
    @Query("SELECT o FROM Ott o WHERE o.name IN :otts")
    public List<Ott> findAllByOtt(@Param("otts") List<String> otts);

    @Query("SELECT o FROM Ott o WHERE o.name = :name")
    public Ott findByName(@Param("name") String name);

    @Query("SELECT o FROM Ott o WHERE o.name IN :names")
    public List<Ott> findAllByName(@Param("names") List<String> names);
}
