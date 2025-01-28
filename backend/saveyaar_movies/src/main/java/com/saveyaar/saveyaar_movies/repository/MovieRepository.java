package com.saveyaar.saveyaar_movies.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.saveyaar.saveyaar_movies.model.Genre;
import com.saveyaar.saveyaar_movies.model.Movie;
import com.saveyaar.saveyaar_movies.model.Ott;
import com.saveyaar.saveyaar_movies.model.People;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer>{
    
    @Query("SELECT m FROM Movie m WHERE m.external_id = :external_id")
    Movie findByExternalId(@Param("external_id") String external_id);

    @Query("SELECT m FROM Movie m WHERE m.external_id_2 = :external_id_2")
    Movie findByExternalId2(@Param("external_id_2") int external_id_2);

    @Query("SELECT m FROM Movie m WHERE m.title = :title")
    Movie findByTitle(@Param("title") String title);

    @Query("SELECT m FROM Movie m JOIN m.languages ml JOIN m.release_dates mr "+
            "WHERE ml.language_iso = :language_iso AND "+
            "mr.country_iso = :country_iso AND "+
            "mr.release_date BETWEEN :start_date AND :end_date")
    List<Movie> findTopMoviesByDateIntervalAndLanguage(@Param("language_iso") String language_iso,
        @Param("start_date") LocalDate start_date, 
        @Param("end_date") LocalDate end_date,
        @Param("country_iso") String country_iso);

    @Query("SELECT m FROM Movie m JOIN m.genres mg JOIN m.release_dates mr "+
            "WHERE mg = :genre AND "+
            "mr.country_iso = :country_iso AND "+
            "mr.release_date BETWEEN :start_date AND :end_date")
    List<Movie> findTopMoviesByDateIntervalAndGenre(@Param("genre") Genre genre, 
        @Param("start_date") LocalDate start_date, 
        @Param("end_date") LocalDate end_date,
        @Param("country_iso") String country_iso);

    @Query("SELECT m FROM Movie m JOIN m.credits mc JOIN m.release_dates mr "+
            "WHERE mc.person = :person AND "+
            "mr.country_iso = :country_iso AND "+
            "mr.release_date BETWEEN :start_date AND :end_date")
    List<Movie> findTopMoviesByDateIntervalAndPeople(@Param("person") People person,
        @Param("start_date") LocalDate start_date, 
        @Param("end_date") LocalDate end_date,
        @Param("country_iso") String country_iso);

    @Query("SELECT m FROM Movie m JOIN m.ott_availability mo JOIN m.release_dates mr "+
            "WHERE mo.country_iso = :country_iso AND mo.ott = :ott AND "+
            "mr.country_iso = :country_iso AND "+
            "mr.release_date BETWEEN :start_date AND :end_date")
    List<Movie> findTopMoviesByDateIntervalAndOtt(@Param("ott") Ott ott,
        @Param("start_date") LocalDate start_date, 
        @Param("end_date") LocalDate end_date,
        @Param("country_iso") String country_iso);

    @Query("SELECT m FROM Movie m JOIN m.release_dates mr "+
            "mr.country_iso = :country_iso AND "+
            "mr.release_date BETWEEN :start_date AND :end_date")
    List<Movie> findTopMoviesByDateInterval(@Param("start_date") LocalDate start_date, 
        @Param("end_date") LocalDate end_date,
        @Param("country_iso") String country_iso);
}
