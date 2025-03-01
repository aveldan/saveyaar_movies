package com.saveyaar.saveyaar_movies.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.saveyaar.saveyaar_movies.DTO.DiscoverDTO;
import com.saveyaar.saveyaar_movies.domain.DTOs.MovieDTO;
import com.saveyaar.saveyaar_movies.service.impl.MovieService;

@RestController
@RequestMapping("/movie")
public class MovieController {
    
    @Autowired
    private MovieService movieService;

    @GetMapping("/{id}")
    public ResponseEntity<MovieDTO> getMovie(@PathVariable("id") int id, @RequestParam("country_iso") String country_iso) {
        MovieDTO mv = movieService.getMovie(id, country_iso);
        if(mv == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        
        return new ResponseEntity<>(mv, HttpStatus.OK);
    }

    @GetMapping("/discover")
    public ResponseEntity<DiscoverDTO> getMoviesByDateInterval(@RequestParam("start_date") LocalDate start_date, 
        @RequestParam("end_date") LocalDate end_date,
        @RequestParam("user_id") int user_id
    ) {

        DiscoverDTO discover = movieService.prefered(user_id, start_date, end_date);
        if(discover == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<DiscoverDTO>(discover, HttpStatus.OK);
    }
}
