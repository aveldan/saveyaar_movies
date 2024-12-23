package com.saveyaar.saveyaar_movies.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.saveyaar.saveyaar_movies.model.Country;

public interface CountryRepository extends JpaRepository<Country, Long>{

    @Query("SELECT c FROM Country c WHERE c.country_iso IN :countries")
    List<Country> findAllByCountry(@Param("countries") List<String> countries);
}
