package com.saveyaar.saveyaar_movies.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saveyaar.saveyaar_movies.client.TMDBClient;
import com.saveyaar.saveyaar_movies.domain.TMDBCountry;
import com.saveyaar.saveyaar_movies.domain.TMDBCountryList;
import com.saveyaar.saveyaar_movies.domain.TMDBGenre;
import com.saveyaar.saveyaar_movies.domain.TMDBLanguage;
import com.saveyaar.saveyaar_movies.domain.TMDBLanguageList;
import com.saveyaar.saveyaar_movies.domain.TMDBOtt;
import com.saveyaar.saveyaar_movies.domain.TMDBOttList;
import com.saveyaar.saveyaar_movies.model.Country;
import com.saveyaar.saveyaar_movies.model.Genre;
import com.saveyaar.saveyaar_movies.model.Language;
import com.saveyaar.saveyaar_movies.model.Ott;
import com.saveyaar.saveyaar_movies.repository.CountryRepository;
import com.saveyaar.saveyaar_movies.repository.GenreRepository;
import com.saveyaar.saveyaar_movies.repository.LanguageRepository;
import com.saveyaar.saveyaar_movies.repository.OttRepository;
import com.saveyaar.saveyaar_movies.service.Ingest;

import jakarta.transaction.Transactional;

@Service("TMDBIngest")
public class TMDBIngest implements Ingest{

    @Autowired
    private TMDBClient client;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private LanguageRepository languageRepository;

    @Autowired
    private OttRepository ottRepository;

    private Genre mapToGenre(TMDBGenre tmdb_genre){
        Genre genre = new Genre();
        genre.setGenre(tmdb_genre.getName());
        return genre;
    }

    private Country mapToCountry(TMDBCountry tmdb_country) {
        Country country = new Country(tmdb_country.getIso_3166_1(), tmdb_country.getEnglish_name(), tmdb_country.getNative_name());
        return country;
    }

    private Language mapToLanguage(TMDBLanguage tmdb_language) {
        Language lang = new Language(tmdb_language.getIso_639_1(), tmdb_language.getEnglish_name(), tmdb_language.getName());
        return lang;
    }

    private Ott maptToOtt(TMDBOtt tmdb_ott) {
        Ott ott = new Ott();
        ott.setLogo_path(tmdb_ott.getLogo_path());
        ott.setName(tmdb_ott.getProvider_name());

        return ott;
    }

    @Override
    @Transactional
    public List<Genre> ingestGenre() {
        List<TMDBGenre> movie_list = client.getMovieGenres().getGenres();
        List<TMDBGenre> tv_list = client.getTvGenres().getGenres();

        List<Genre> genres = Stream.concat(movie_list.stream(), tv_list.stream())
            .map(this::mapToGenre)
            .distinct()
            .collect(Collectors.toList());

        List<String> existing_genres = genreRepository.findAllByGenre(
                genres.stream()
                .map(Genre::getGenre)
                .collect(Collectors.toList())
            ).stream()
            .map(Genre::getGenre)
            .collect(Collectors.toList());

        List<Genre> genres_to_add = genres.stream()
                                    .filter(genre -> !existing_genres.contains(genre.getGenre()))
                                    .collect(Collectors.toList());

        genreRepository.saveAll(genres_to_add);
        
        return genres_to_add;
    }

    @Override
    public List<Country> ingestCountries() {
        TMDBCountryList tmdb_countries = client.getCountries();

        List<Country> countries = tmdb_countries.stream()
            .map(this::mapToCountry)
            .distinct()
            .collect(Collectors.toList());
        
        List<String> countries_present = countryRepository.findAllByCountry(
                countries.stream()
                .map(Country::getCountry_iso)
                .collect(Collectors.toList())
            ).stream()
             .map(Country::getCountry_iso)
             .collect(Collectors.toList());
        
        List<Country> countries_to_add = countries.stream()
                                         .filter(country -> !countries_present.contains(country.getCountry_iso()))
                                         .collect(Collectors.toList());

        countryRepository.saveAll(countries_to_add);
        
        return countries_to_add;
    }

    @Override
    public List<Language> ingestLanguages() {
        TMDBLanguageList tmdb_languages = client.getLanguages();

        List<Language> languages = tmdb_languages.stream()
                                    .map(this::mapToLanguage)
                                    .distinct()
                                    .collect(Collectors.toList());
 
        List<String> lang_present = languageRepository.findAllByLanguage(
                languages.stream().map(Language::getLanguage_iso).collect(Collectors.toList())   
            ).stream()
             .map(Language::getLanguage_iso)
             .collect(Collectors.toList());
        
        List<Language> lang_to_add = languages.stream()
                                     .filter(lang -> !lang_present.contains(lang.getLanguage_iso()))
                                     .collect(Collectors.toList());
        
        languageRepository.saveAll(lang_to_add);

        return lang_to_add;
    }

    @Override
    public List<Ott> ingestOtts() {
        TMDBOttList movies_otts = client.getMovieOtts();
        TMDBOttList tvs_otts = client.getTvOtts();

        List<Ott> otts = Stream.concat(movies_otts.getResults().stream(), tvs_otts.getResults().stream())
                         .map(this::maptToOtt)
                         .distinct()
                         .collect(Collectors.toList());
        
        List<String> otts_present = ottRepository.findAllByOtt(
                otts.stream()
                .map(Ott::getName)
                .collect(Collectors.toList())   
            ).stream()
             .map(Ott::getName)
             .collect(Collectors.toList());


        List<Ott> otts_to_add = otts.stream()
                                .filter(ott -> !otts_present.contains(ott.getName()))
                                .collect(Collectors.toList());

        ottRepository.saveAll(otts_to_add);

        return otts_to_add;
    }
}
