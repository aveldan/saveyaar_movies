package com.saveyaar.saveyaar_movies.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.saveyaar.saveyaar_movies.domain.TMDBDiscover;
import com.saveyaar.saveyaar_movies.domain.TMDBMovie;
import com.saveyaar.saveyaar_movies.domain.TMDBResponse;
import com.saveyaar.saveyaar_movies.model.Country;
import com.saveyaar.saveyaar_movies.model.Genre;
import com.saveyaar.saveyaar_movies.model.Language;
import com.saveyaar.saveyaar_movies.model.Movie;
import com.saveyaar.saveyaar_movies.model.Ott;
import com.saveyaar.saveyaar_movies.service.Ingest;

@RestController
@RequestMapping("/tmdb/ingest")
public class TMDBIngestController {

    @Autowired
    @Qualifier("TMDBIngest")
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

    @GetMapping("/discover/movies")
    public ResponseEntity<TMDBDiscover> getDiscoverMovies(
        @RequestParam(name = "with_origin_country", defaultValue = "IN") String with_origin_country,
        @RequestParam(name = "with_original_language", defaultValue = "hi") String with_original_language,
        @RequestParam("primary_release_date.gte") String primary_release_dat_gte,
        @RequestParam("primary_release_date.lte") String primary_release_date_lte,
        @RequestParam(name = "page", defaultValue = "1") int page
    ){

        TMDBDiscover discover_results = ingestService.getDiscoverMovies(with_origin_country, with_original_language, primary_release_dat_gte, primary_release_date_lte, page);
     
        if(discover_results == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(discover_results, HttpStatus.OK);
    }

    @PostMapping("/movie/{id}")
    public ResponseEntity<Movie> ingestMovie(@PathVariable(name = "id") int id) {
        Movie mv = ingestService.ingestMovie(id);

        if(mv == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        
        return new ResponseEntity<>(mv, HttpStatus.OK);
    }
}
