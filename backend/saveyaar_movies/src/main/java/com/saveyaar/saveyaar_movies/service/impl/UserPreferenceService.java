package com.saveyaar.saveyaar_movies.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saveyaar.saveyaar_movies.model.Genre;
import com.saveyaar.saveyaar_movies.model.Language;
import com.saveyaar.saveyaar_movies.model.Ott;
import com.saveyaar.saveyaar_movies.model.People;
import com.saveyaar.saveyaar_movies.model.UserPreference;
import com.saveyaar.saveyaar_movies.repository.UserPreferenceRepository;

@Service
public class UserPreferenceService {

    @Autowired
    private UserPreferenceRepository userPreferenceRepository;

    @Autowired
    private LanguageService languageService;
    
    @Autowired
    private GenreService genreService;

    @Autowired
    private PeopleService peopleService;

    @Autowired
    private OttService ottService;

    public com.saveyaar.saveyaar_movies.DTO.UserPreference setUserPreference(int user_id, 
        Optional<List<String>> prefered_languages, 
        Optional<List<String>> prefered_genres,
        Optional<List<String>> prefered_people,
        Optional<List<String>> prefered_otts
    )
    {
        UserPreference preference = new UserPreference();
        if(prefered_languages.isPresent()) {
            List<Language> languages = languageService.getLanguages(prefered_languages.get());
            preference.setLanguages(languages);
        }

        if(prefered_genres.isPresent()) {
            List<Genre> genres = genreService.getGenres(prefered_genres.get());
            preference.setGenres(genres);
        }

        if(prefered_people.isPresent()) {
            List<People> people = peopleService.getPeople(prefered_people.get());
            preference.setPeople_involved(people);
        }

        if(prefered_otts.isPresent()) {
            List<Ott> otts = ottService.getOtts(prefered_otts.get());
            preference.setOtts(otts);
        }

        preference.setUser_id(user_id);

        userPreferenceRepository.save(preference);

        return userPreferenceRepository.findDTOByUserId(user_id);
    }

    public UserPreference getUserPreference(int user_id) {
        return userPreferenceRepository.findByUserId(user_id);
    }

    public UserPreference getUserPreference(String device_token) {
        return userPreferenceRepository.findByDeviceToken(device_token);
    }
}
