package com.saveyaar.saveyaar_movies.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.saveyaar.saveyaar_movies.DTO.DiscoverDTO;
import com.saveyaar.saveyaar_movies.domain.DTOs.MovieDTO;
import com.saveyaar.saveyaar_movies.model.ContentLanguage;
import com.saveyaar.saveyaar_movies.model.Genre;
import com.saveyaar.saveyaar_movies.model.Language;
import com.saveyaar.saveyaar_movies.model.Movie;
import com.saveyaar.saveyaar_movies.model.Ott;
import com.saveyaar.saveyaar_movies.model.People;
import com.saveyaar.saveyaar_movies.model.ReleaseDate;
import com.saveyaar.saveyaar_movies.model.UserPreference;
import com.saveyaar.saveyaar_movies.repository.MovieRepository;

@Service
public class MovieService {
    
    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private LanguageService languageService;
    
    @Autowired
    private UserPreferenceService userPreferenceService;

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

    private List<Movie> getTopMovies(Language lang, LocalDate start_date, LocalDate end_date, String country_iso) {
        return movieRepository.findTopMoviesByDateIntervalAndLanguage(lang.getLanguage_iso(), start_date, end_date, country_iso);
    }

    private List<Movie> getTopMovies(Genre genre, LocalDate start_date, LocalDate end_date, String country_iso) {
        return movieRepository.findTopMoviesByDateIntervalAndGenre(genre, start_date, end_date, country_iso);
    }

    private List<Movie> getTopMovies(People person, LocalDate start_date, LocalDate end_date, String country_iso) {
        return movieRepository.findTopMoviesByDateIntervalAndPeople(person, start_date, end_date, country_iso);
    }

    private List<Movie> getTopMovies(Ott ott, LocalDate start_date, LocalDate end_date, String country_iso) {
        return movieRepository.findTopMoviesByDateIntervalAndOtt(ott, start_date, end_date, country_iso);
    }

    private List<Movie> getTopMovies(String country_iso, LocalDate start_date, LocalDate end_date) {
        return movieRepository.findTopMoviesByDateInterval(start_date, end_date, country_iso);
    }

    public DiscoverDTO prefered(int user_id, LocalDate start_date, LocalDate end_date) {
        UserPreference preference = userPreferenceService.getUserPreference(user_id);
        
        DiscoverDTO ret = new DiscoverDTO();

        if(preference == null) {
            List<Movie> mvs = getTopMovies("IN", start_date, end_date);
            String title = "Top Movies";

            ret.addCategory(title, mvs);
        } else {
            if( preference.getLanguages() != null) {
                for(Language lang: preference.getLanguages()) {
                    List<Movie> mvs = getTopMovies(lang, start_date, end_date, preference.getCountry().getCountry_iso());
                    String title = "Top "+lang.getName()+" for you";

                    ret.addCategory(title, mvs);
                }
            }

            if(preference.getGenres() != null) {
                for(Genre genre: preference.getGenres()) {
                    List<Movie> mvs = getTopMovies(genre, start_date, end_date, preference.getCountry().getCountry_iso());
                    String title = "Top "+genre.getGenre()+" for you";

                    ret.addCategory(title, mvs);
                }
            }

            if(preference.getPeople_involved() != null) {
                for(People person: preference.getPeople_involved()) {
                    List<Movie> mvs = getTopMovies(person, start_date, end_date, preference.getCountry().getCountry_iso());
                    String title = "Top Movies with "+person.getDisplay_name()+" in them";

                    ret.addCategory(title, mvs);
                }
            }

            if(preference.getOtts() != null) {
                for(Ott ott: preference.getOtts()) {
                    List<Movie> mvs = getTopMovies(ott, start_date, end_date, preference.getCountry().getCountry_iso());
                    String title = "Top Movies on "+ott.getName();

                    ret.addCategory(title, mvs);
                }
            }
        }

        return ret;
    }

    public DiscoverDTO prefered(String device_token, LocalDate start_date, LocalDate end_date) {

        UserPreference preference = userPreferenceService.getUserPreference(device_token);
        DiscoverDTO ret = new DiscoverDTO();

        if(preference == null) {
            List<Movie> mvs = getTopMovies("IN", start_date, end_date);
            String title = "Top Movies";

            ret.addCategory(title, mvs);
        } else {
            if( preference.getLanguages() != null) {
                for(Language lang: preference.getLanguages()) {
                    List<Movie> mvs = getTopMovies(lang, start_date, end_date, preference.getCountry().getCountry_iso());
                    String title = "Top "+lang.getName()+" for you";

                    ret.addCategory(title, mvs);
                }
            }

            if(preference.getGenres() != null) {
                for(Genre genre: preference.getGenres()) {
                    List<Movie> mvs = getTopMovies(genre, start_date, end_date, preference.getCountry().getCountry_iso());
                    String title = "Top "+genre.getGenre()+" for you";

                    ret.addCategory(title, mvs);
                }
            }

            if(preference.getPeople_involved() != null) {
                for(People person: preference.getPeople_involved()) {
                    List<Movie> mvs = getTopMovies(person, start_date, end_date, preference.getCountry().getCountry_iso());
                    String title = "Top Movies with "+person.getDisplay_name()+" in them";

                    ret.addCategory(title, mvs);
                }
            }

            if(preference.getOtts() != null) {
                for(Ott ott: preference.getOtts()) {
                    List<Movie> mvs = getTopMovies(ott, start_date, end_date, preference.getCountry().getCountry_iso());
                    String title = "Top Movies on "+ott.getName();

                    ret.addCategory(title, mvs);
                }
            }
        }

        return ret;
    } 
}
