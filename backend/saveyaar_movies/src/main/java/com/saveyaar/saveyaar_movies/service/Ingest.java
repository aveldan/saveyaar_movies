package com.saveyaar.saveyaar_movies.service;

import java.util.List;

import com.saveyaar.saveyaar_movies.domain.TMDBDiscover;
import com.saveyaar.saveyaar_movies.model.Country;
import com.saveyaar.saveyaar_movies.model.Genre;
import com.saveyaar.saveyaar_movies.model.Language;
import com.saveyaar.saveyaar_movies.model.Movie;
import com.saveyaar.saveyaar_movies.model.Ott;

public interface Ingest {
    
    public List<Genre> ingestGenre();
    public List<Country> ingestCountries();
    public List<Language> ingestLanguages();
    public List<Ott> ingestOtts();
    public TMDBDiscover getDiscoverMovies(
        String with_origin_country,
        String with_original_language,
        String primary_release_dat_gte,
        String primary_release_date_lte,
        int page
    );
    public Movie ingestMovie(int id);
}
