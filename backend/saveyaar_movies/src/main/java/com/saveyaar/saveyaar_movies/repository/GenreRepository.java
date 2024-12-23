package com.saveyaar.saveyaar_movies.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.saveyaar.saveyaar_movies.model.Genre;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long>{
    
    @Query("SELECT g from Genre g WHERE g.genre IN :genres")
    List<Genre> findAllByGenre(@Param("genres") List<String> genres);
}
