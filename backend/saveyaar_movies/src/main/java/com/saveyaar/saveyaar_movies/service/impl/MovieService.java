package com.saveyaar.saveyaar_movies.service.impl;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.saveyaar.saveyaar_movies.domain.DTOs.MovieDTO;
import com.saveyaar.saveyaar_movies.model.ContentLanguage;
import com.saveyaar.saveyaar_movies.model.Genre;
import com.saveyaar.saveyaar_movies.model.Movie;
import com.saveyaar.saveyaar_movies.model.ReleaseDate;
import com.saveyaar.saveyaar_movies.repository.MovieRepository;

@Service
public class MovieService {
    
    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private LanguageService languageService;
    
    @Transactional
    public MovieDTO getMovie(int id, String country_iso) {
        Movie movie = movieRepository.findById(id).orElse(null);
        if(movie == null)
            return null;
        
        MovieDTO mv_dto = new MovieDTO(movie);
        
        mv_dto.setLanguages(movie.getLanguages().stream()
                            .map(ContentLanguage::getLanguage_iso)
                            .map(iso -> languageService.getLanguage(iso))
                            .collect(Collectors.toList())
                            );

        mv_dto.setGenres(movie.getGenres().stream()
                         .map(Genre::getGenre)
                         .collect(Collectors.toList())
                        );
        
        mv_dto.setReleasDate(movie.getRelease_dates().stream()
                             .filter(rd -> country_iso.equals(rd.getCountry_iso()))
                             .map(ReleaseDate::getRelease_date)
                             .findFirst()
                             .orElse(null)
                            );      
        
        mv_dto.setCredits(movie.getCredits());
        mv_dto.setOttAvalability(movie.getOtt_availability(), country_iso);
        return mv_dto;
    }
}
