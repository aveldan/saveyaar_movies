package com.saveyaar.saveyaar_movies.client;


import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.saveyaar.saveyaar_movies.domain.TMDBCountryList;
import com.saveyaar.saveyaar_movies.domain.TMDBGenreList;
import com.saveyaar.saveyaar_movies.domain.TMDBLanguageList;
import com.saveyaar.saveyaar_movies.domain.TMDBOttList;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class TMDBClient {

    private final RestTemplate restTemplate;

    private Environment env;

    private <T> T request(HttpEntity<String> entity, String uriKey, Class<T> clazz){
        try {
            final ResponseEntity<T> response = restTemplate.exchange(
                env.getProperty(uriKey),
                HttpMethod.GET,
                entity,
                clazz
            );

            if(!response.getStatusCode().is2xxSuccessful()){

            }

            return response.getBody();
        } catch (RestClientException ex) {
            throw ex;
        }
    }

    public TMDBGenreList getMovieGenres() {
        
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(env.getProperty("TMDB.auth.token"));

        HttpEntity<String> enti = new HttpEntity<String>(headers);

        return request(enti, "TMDB.uri.genre.movie", TMDBGenreList.class);
    }

    public TMDBGenreList getTvGenres() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(env.getProperty("TMDB.auth.token"));

        HttpEntity<String> enti = new HttpEntity<String>(headers);

        return request(enti, "TMDB.uri.genre.tv", TMDBGenreList.class);
    }

    public TMDBCountryList getCountries() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(env.getProperty("TMDB.auth.token"));
        HttpEntity<String> enti = new HttpEntity<String>(headers);

        return request(enti, "TMDB.uri.config.countries", TMDBCountryList.class);
    }

    public TMDBLanguageList getLanguages() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(env.getProperty("TMDB.auth.token"));
        HttpEntity<String> enti = new HttpEntity<String>(headers);
        
        return request(enti, "TMDB.uri.config.languages", TMDBLanguageList.class);
    }

    public TMDBOttList getMovieOtts() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(env.getProperty("TMDB.auth.token"));
        HttpEntity<String> enti = new HttpEntity<String>(headers);
        
        return request(enti, "TMDB.uri.ott.movies", TMDBOttList.class);
    }

    public TMDBOttList getTvOtts() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(env.getProperty("TMDB.auth.token"));
        HttpEntity<String> enti = new HttpEntity<String>(headers);
        
        return request(enti, "TMDB.uri.ott.tvs", TMDBOttList.class);
    }
}
