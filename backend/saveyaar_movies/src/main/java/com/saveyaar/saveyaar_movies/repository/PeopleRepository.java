package com.saveyaar.saveyaar_movies.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.saveyaar.saveyaar_movies.model.People;

@Repository
public interface PeopleRepository extends JpaRepository<People, Long>{

    @Query("SELECT p FROM People p WHERE p.display_name = :display_name")
    People findByDisplayName(@Param("display_name") String display_name);

    @Query("SELECT p FROM People p WHERE p.display_name IN :display_names")
    List<People> findAllByDisplayName(@Param("display_names") List<String> display_names);
}
