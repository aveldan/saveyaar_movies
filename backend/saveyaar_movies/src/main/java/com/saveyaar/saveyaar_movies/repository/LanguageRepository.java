package com.saveyaar.saveyaar_movies.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.saveyaar.saveyaar_movies.model.Language;

public interface LanguageRepository extends JpaRepository<Language, Long>{

    @Query("SELECT l FROM Language l WHERE l.language_iso IN :languages")
    public List<Language> findAllByLanguage(@Param("languages") List<String> languages);
}
