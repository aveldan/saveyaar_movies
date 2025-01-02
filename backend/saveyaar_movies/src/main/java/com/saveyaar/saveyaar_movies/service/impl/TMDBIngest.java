package com.saveyaar.saveyaar_movies.service.impl;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saveyaar.saveyaar_movies.client.TMDBClient;
import com.saveyaar.saveyaar_movies.domain.TMDBCountry;
import com.saveyaar.saveyaar_movies.domain.TMDBCountryList;
import com.saveyaar.saveyaar_movies.domain.TMDBDiscover;
import com.saveyaar.saveyaar_movies.domain.TMDBGenre;
import com.saveyaar.saveyaar_movies.domain.TMDBLanguage;
import com.saveyaar.saveyaar_movies.domain.TMDBLanguageList;
import com.saveyaar.saveyaar_movies.domain.TMDBMovie;
import com.saveyaar.saveyaar_movies.domain.TMDBOtt;
import com.saveyaar.saveyaar_movies.domain.TMDBOttList;
import com.saveyaar.saveyaar_movies.domain.TMDBPerson;
import com.saveyaar.saveyaar_movies.model.Cast;
import com.saveyaar.saveyaar_movies.model.ContentLanguage;
import com.saveyaar.saveyaar_movies.model.Country;
import com.saveyaar.saveyaar_movies.model.Credit;
import com.saveyaar.saveyaar_movies.model.Crew;
import com.saveyaar.saveyaar_movies.model.Genre;
import com.saveyaar.saveyaar_movies.model.Language;
import com.saveyaar.saveyaar_movies.model.Movie;
import com.saveyaar.saveyaar_movies.model.Ott;
import com.saveyaar.saveyaar_movies.model.OttRecord;
import com.saveyaar.saveyaar_movies.model.People;
import com.saveyaar.saveyaar_movies.model.ReleaseDate;
import com.saveyaar.saveyaar_movies.repository.CountryRepository;
import com.saveyaar.saveyaar_movies.repository.GenreRepository;
import com.saveyaar.saveyaar_movies.repository.LanguageRepository;
import com.saveyaar.saveyaar_movies.repository.MovieRepository;
import com.saveyaar.saveyaar_movies.repository.OttRepository;
import com.saveyaar.saveyaar_movies.repository.PeopleRepository;
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

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private PeopleRepository peopleRepository;
    
    private Logger logger = LogManager.getLogger();

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

    private Genre mapToGenre(TMDBMovie.TMDBGenre tmdb_genre) {
        Genre genre = genreRepository.findByGenre(tmdb_genre.getName());
        if(genre == null)
            genre = new Genre();
        genre = genreRepository.save(genre);
        return genre;
    }

    private ContentLanguage mapToContentLanguage(TMDBMovie.TMDBTranslations.Translation translation, String original_lang) {
        ContentLanguage lang = new ContentLanguage();
        lang.setLanguage_iso(translation.getIso_639_1());
        lang.setLocal_title(translation.getData().getTitle());
        lang.set_original_language(translation.getIso_639_1() == original_lang);
        return lang;
    }

    private LocalDate mapToDate(String date) {
        LocalDate l_date;

        if(date == null){
            return null;
        }
        
        if(date.length() > 10){
            l_date = ZonedDateTime.parse(date).toLocalDate();
        } else {
            l_date = LocalDate.parse(date);
        }

        return l_date;
    }

    private ReleaseDate mapToReleaseDate(TMDBMovie.TMDBReleaseDates.Result result) {
        ReleaseDate releaseDate = new ReleaseDate();

        TMDBMovie.TMDBReleaseDates.Result.ReleaseDate tmp = result.getRelease_dates().stream()
                                                                .filter(rd -> rd.getCertification() != "")
                                                                .findFirst()
                                                                .orElse(null);

                                                            
        releaseDate.setCertification(tmp == null ? null : tmp.getCertification());
        releaseDate.setCountry_iso(result.getIso_3166_1());
        releaseDate.setRelease_date(mapToDate(tmp == null ? null : tmp.getRelease_date()));
        return releaseDate;
    }

    private People mapToPeople(TMDBPerson tmdb_person, String original_name) {
        People person = new People();

        person.setBirth_date(mapToDate(tmdb_person.getBirthday()));
        person.setDeath_date(mapToDate(tmdb_person.getDeathday()));
        person.setDisplay_name(tmdb_person.getName());
        person.setOriginal_name(original_name);
        person.setExternal_id(tmdb_person.getImdb_id());
        person.setImage_path(tmdb_person.getProfile_path());
        person.setSummary(tmdb_person.getBiography());
        person.setPopularity(tmdb_person.getPopularity());
        
        return person;
    }

    private Credit mapToCredit(TMDBMovie.TMDBCredits.Cast cast) {
        Cast c = new Cast();
        People person = peopleRepository.findByDisplayName(cast.getName());
        if (person == null) {
            TMDBPerson tmdb_person = client.getPerson(cast.getId());
            person = mapToPeople(tmdb_person, cast.getOriginal_name());
            peopleRepository.save(person);
        }
        c.setPerson(person);
        List<String> characters = Arrays.asList(cast.getCharacter().split(" / "));
        c.addCharacters(characters);
        return c;
    }

    
    private List<Credit> mapToCredits(List<TMDBMovie.TMDBCredits.Crew> crews) {
        HashMap<TMDBMovie.TMDBCredits.Crew, List<String>> mp = new HashMap<>();
        
        for(TMDBMovie.TMDBCredits.Crew crew: crews) {
            if(!mp.containsKey(crew))
                mp.put(crew, new ArrayList<>());
               
            mp.get(crew).add(crew.getJob());
        }

        List<Credit> credits = new ArrayList<>();

       for(Map.Entry<TMDBMovie.TMDBCredits.Crew, List<String>> entry: mp.entrySet()) {
            TMDBMovie.TMDBCredits.Crew crew = entry.getKey();
            List<String> jobs = entry.getValue();
            credits.add(mapToCredit(crew, jobs));
        }

        return credits;
    }

    private Credit mapToCredit(TMDBMovie.TMDBCredits.Crew crew, List<String> jobs) {
        Crew c = new Crew();
        People person = peopleRepository.findByDisplayName(crew.getName());
        if (person == null) {
            TMDBPerson tmdb_person = client.getPerson(crew.getId());
            person = mapToPeople(tmdb_person, crew.getOriginal_name());
            peopleRepository.save(person);
        }
        c.setPerson(person);
        c.addRoles(jobs);
        return c;
    }

    private List<Credit> mapToCredits(TMDBMovie.TMDBCredits credits) {
        List<Credit> crews = mapToCredits(credits.getCrew());
        List<Credit> casts = credits.getCast().stream().map(this::mapToCredit).collect(Collectors.toList());
        
        List<Credit> movie_credits = new ArrayList<>(crews);
        movie_credits.addAll(casts);

        return movie_credits;
    }

    private List<OttRecord> mapToOttRecords(TMDBMovie.TMDBWatchProviders.Results.WatchProviders wp, String country_iso) {
        List<OttRecord> records = new ArrayList<>();
        
        if(wp == null)
            return records;
        
        if(wp.getFlatrate() != null) {
            for(TMDBMovie.TMDBWatchProviders.Results.WatchProviders.Provider flatrate: wp.getFlatrate()){
                OttRecord record = new OttRecord();
                record.setCountry_iso(country_iso);
                record.setWatch_type("subscription");
                Ott ott = ottRepository.findByName(flatrate.getProvider_name());
                record.setOtt(ott);
                records.add(record);
            }
        }

        if(wp.getBuy() != null) {
            for(TMDBMovie.TMDBWatchProviders.Results.WatchProviders.Provider buy: wp.getBuy()){
                OttRecord record = new OttRecord();
                record.setCountry_iso(country_iso);
                record.setWatch_type("buy");
                Ott ott = ottRepository.findByName(buy.getProvider_name());
                record.setOtt(ott);
                records.add(record);
            }
        }

        if(wp.getRent() != null) {
            for(TMDBMovie.TMDBWatchProviders.Results.WatchProviders.Provider rent: wp.getRent()){
                OttRecord record = new OttRecord();
                record.setCountry_iso(country_iso);
                record.setWatch_type("rent");
                Ott ott = ottRepository.findByName(rent.getProvider_name());
                record.setOtt(ott);
                records.add(record);
            }
        }
        
        if(wp.getAds() != null){
            for(TMDBMovie.TMDBWatchProviders.Results.WatchProviders.Provider ads: wp.getAds()){
                OttRecord record = new OttRecord();
                record.setCountry_iso(country_iso);
                record.setWatch_type("ads");
                Ott ott = ottRepository.findByName(ads.getProvider_name());
                record.setOtt(ott);
                records.add(record);
            }
        }

        return records;
    }

    private List<OttRecord> mapToOttRecords(TMDBMovie.TMDBWatchProviders watch_providers) {
        List<OttRecord> records = new ArrayList<>();
        records.addAll(mapToOttRecords(watch_providers.getResults().getIN(), "IN"));
        records.addAll(mapToOttRecords(watch_providers.getResults().getUS(), "US"));
        return records;
    }

    private void mapToMovie(Movie mv, TMDBMovie tmdb_mv){
        
        mv.setTitle(tmdb_mv.getTitle());
        mv.setOriginal_title(tmdb_mv.getOriginal_title());
        mv.setOverview(tmdb_mv.getOverview());
        mv.setBackdrop_path(tmdb_mv.getBackdrop_path());
        mv.setPoster_path(tmdb_mv.getPoster_path());
        mv.setBudget(tmdb_mv.getBudget());
        mv.setRevenue(tmdb_mv.getRevenue());
        mv.setExternal_id(tmdb_mv.getImdb_id());
        mv.setExternal_id_2(tmdb_mv.getId());
        mv.setPopularity(tmdb_mv.getPopularity());
        mv.setRuntime(tmdb_mv.getRuntime());
        
        mv.setGenres(tmdb_mv.getGenres().stream()
            .map(this::mapToGenre)
            .collect(Collectors.toSet())
        );

        mv.setLanguages(
            tmdb_mv.getTranslations().getTranslations().stream()
            .map(s -> mapToContentLanguage(s, tmdb_mv.getOriginal_language()))
            .collect(Collectors.toSet())
        );

        mv.setRelease_dates(
            tmdb_mv.getRelease_dates().getResults().stream()
            .map(this::mapToReleaseDate)
            .collect(Collectors.toSet())
        );
        
        List<Credit> credits = mapToCredits(tmdb_mv.getCredits());
        mv.setCredits(credits);

        List<OttRecord> records = mapToOttRecords(tmdb_mv.getWatch_providers());
        mv.setOttRecords(records);

        // rating
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
        
        logger.info("{} Genres added to the DB", genres_to_add.size());

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
        
        logger.info("{} Countries added to the DB", countries_to_add.size());

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

        logger.info("{} Languages added to the DB", lang_to_add.size());

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

        logger.info("{} Otts added to the DB", otts_to_add.size());

        return otts_to_add;
    }

    @Override
    public TMDBDiscover getDiscoverMovies(
        String with_origin_country,
        String with_original_language,
        String primary_release_dat_gte,
        String primary_release_date_lte,
        int page
    ) {
        TMDBDiscover discover_movies = client.getDiscoverMovies(with_origin_country, with_original_language, primary_release_dat_gte, primary_release_date_lte, page);
        
        if(discover_movies != null)
            logger.info("{} movies discovered from TMDB Discover Movies", discover_movies.getResults().size());
        else
            logger.info("404 error when calling TMDB Discover Movies with params {} {} {} {} {}", with_origin_country, with_original_language, primary_release_dat_gte, primary_release_date_lte, page);
        
        return discover_movies;
    }

    @Override
    @Transactional
    public Movie ingestMovie(int id) {
        TMDBMovie tmdb_mv = client.getMovie(id);
        if(tmdb_mv == null) {
            logger.info("404 error when calling TMDB Movie with params {}", id);
            return null;
        }

        Movie mv = tmdb_mv.getImdb_id() == null ? null : movieRepository.findByExternalId(tmdb_mv.getImdb_id());
        
        if(mv == null)
            mv = movieRepository.findByExternalId2(tmdb_mv.getId());
        
        if(mv == null)    
            mv = new Movie();

        mapToMovie(mv, tmdb_mv);
        
        movieRepository.save(mv);
        
        logger.info("Ingested movie with id:{}", id);    
        
        return mv;
    }
}
