package com.saveyaar.saveyaar_movies.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.saveyaar.saveyaar_movies.model.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer>{
    
    @Query("SELECT m FROM Movie m WHERE m.external_id = :external_id")
    Movie findByExternalId(@Param("external_id") String external_id);

    @Query("SELECT m FROM Movie m WHERE m.external_id_2 = :external_id_2")
    Movie findByExternalId2(@Param("external_id_2") int external_id_2);

    @Query("SELECT m FROM Movie m WHERE m.title = :title")
    Movie findByTitle(@Param("title") String title);
}
