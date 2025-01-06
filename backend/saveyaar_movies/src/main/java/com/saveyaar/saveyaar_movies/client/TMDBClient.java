package com.saveyaar.saveyaar_movies.client;


import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.saveyaar.saveyaar_movies.domain.TMDBCountryList;
import com.saveyaar.saveyaar_movies.domain.TMDBDiscover;
import com.saveyaar.saveyaar_movies.domain.TMDBGenreList;
import com.saveyaar.saveyaar_movies.domain.TMDBLanguageList;
import com.saveyaar.saveyaar_movies.domain.TMDBMovie;
import com.saveyaar.saveyaar_movies.domain.TMDBOttList;
import com.saveyaar.saveyaar_movies.domain.TMDBPerson;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class TMDBClient {

    private final RestTemplate restTemplate;

    private Environment env;

    private <T> T request(HttpEntity<String> entity, String uri, Class<T> clazz){
        try {
            final ResponseEntity<T> response = restTemplate.exchange(
                uri,
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

        return request(enti, env.getProperty("TMDB.uri.genre.movie"), TMDBGenreList.class);
    }

    public TMDBGenreList getTvGenres() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(env.getProperty("TMDB.auth.token"));

        HttpEntity<String> enti = new HttpEntity<String>(headers);

        return request(enti, env.getProperty("TMDB.uri.genre.tv"), TMDBGenreList.class);
    }

    public TMDBCountryList getCountries() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(env.getProperty("TMDB.auth.token"));
        HttpEntity<String> enti = new HttpEntity<String>(headers);

        return request(enti, env.getProperty("TMDB.uri.config.countries"), TMDBCountryList.class);
    }

    public TMDBLanguageList getLanguages() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(env.getProperty("TMDB.auth.token"));
        HttpEntity<String> enti = new HttpEntity<String>(headers);
        
        return request(enti, env.getProperty("TMDB.uri.config.languages"), TMDBLanguageList.class);
    }

    public TMDBOttList getMovieOtts() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(env.getProperty("TMDB.auth.token"));
        HttpEntity<String> enti = new HttpEntity<String>(headers);
        
        return request(enti, env.getProperty("TMDB.uri.ott.movies"), TMDBOttList.class);
    }

    public TMDBOttList getTvOtts() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(env.getProperty("TMDB.auth.token"));
        HttpEntity<String> enti = new HttpEntity<String>(headers);
        
        return request(enti, env.getProperty("TMDB.uri.ott.tvs"), TMDBOttList.class);
    }

    public TMDBDiscover getDiscoverMovies(
        String with_origin_country,
        String with_original_language,
        String primary_release_dat_gte,
        String primary_release_date_lte,
        int page
    ) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(env.getProperty("TMDB.auth.token"));
        HttpEntity<String> enti = new HttpEntity<String>(headers);

        String uri = UriComponentsBuilder.fromUriString(env.getProperty("TMDB.uri.discover.movies"))
                        .queryParam("with_origin_country", with_origin_country)
                        .queryParam("primary_release_date.lte", primary_release_date_lte)
                        .queryParam("primary_release_date.gte", primary_release_dat_gte)
                        .queryParam("with_original_language", with_original_language)
                        .queryParam("page", page)
                        .queryParam("sort_by", "primary_release_date.asc")
                        .toUriString();

        
        return request(enti, uri, TMDBDiscover.class);
    }

    public TMDBMovie getMovie(int id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(env.getProperty("TMDB.auth.token"));
        HttpEntity<String> enti = new HttpEntity<String>(headers);

        String uri = UriComponentsBuilder.fromUriString(env.getProperty("TMDB.uri.movie"))
                        .path("/{id}")
                        .queryParam("append_to_response", "release_dates,translations,watch/providers,credits")
                        .buildAndExpand(id)
                        .toUriString();

        
        return request(enti, uri, TMDBMovie.class);
    }

    public TMDBPerson getPerson(int id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(env.getProperty("TMDB.auth.token"));
        HttpEntity<String> enti = new HttpEntity<String>(headers);

        String uri = UriComponentsBuilder.fromUriString(env.getProperty("TMDB.uri.person"))
                        .path("/{id}")
                        .buildAndExpand(id)
                        .toUriString();

        return request(enti, uri, TMDBPerson.class);
    }

    public String ratingJsoupDoc(String id) {
        try {
            String url = env.getProperty("rating.url")+"/"+id;
            
            Document doc = Jsoup.connect(url).get();
            Element spanElement = doc.select(env.getProperty("rating.classname")).first();
            
            if (spanElement != null) {
            
                return spanElement.text();
            } else {
            
                return null;
            }
        } catch (IOException ex) {
            System.err.println("Error fetching the HTML: " + ex.getMessage());
            return null;
        }
        
    }
}
