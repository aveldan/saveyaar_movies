package com.saveyaar.saveyaar_movies.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saveyaar.saveyaar_movies.domain.TMDBResponse;
import com.saveyaar.saveyaar_movies.model.Country;
import com.saveyaar.saveyaar_movies.model.Genre;
import com.saveyaar.saveyaar_movies.model.Language;
import com.saveyaar.saveyaar_movies.model.Ott;
import com.saveyaar.saveyaar_movies.service.Ingest;

@RestController
@RequestMapping("/tmdb/ingest")
public class TMDBIngestController {

    @Autowired
    private Ingest ingestService;

    @PostMapping("/genre")
    public ResponseEntity<TMDBResponse> ingestGenre(){
        List<Genre> added_genres = ingestService.ingestGenre();

        TMDBResponse response = new TMDBResponse();
        response.setNum_items_added(added_genres.size());
        
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/countries")
    public ResponseEntity<TMDBResponse> ingestCountries(){
        List<Country> added_countries = ingestService.ingestCountries();

        TMDBResponse response = new TMDBResponse();
        response.setNum_items_added(added_countries.size());
        
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/languages")
    public ResponseEntity<TMDBResponse> ingestLanguages(){
        List<Language> added_langs = ingestService.ingestLanguages();

        TMDBResponse response = new TMDBResponse();
        response.setNum_items_added(added_langs.size());
        
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/otts")
    public ResponseEntity<TMDBResponse> ingestOtts(){
        List<Ott> added_otts = ingestService.ingestOtts();

        TMDBResponse response = new TMDBResponse();
        response.setNum_items_added(added_otts.size());
        
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
