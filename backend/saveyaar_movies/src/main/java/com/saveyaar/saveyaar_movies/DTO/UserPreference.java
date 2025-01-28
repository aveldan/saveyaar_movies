package com.saveyaar.saveyaar_movies.DTO;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class UserPreference {
    private List<Language> languages;
    private List<Genre> genres;
    private List<People> people_involved;
    private List<Ott> otts;

    public UserPreference(com.saveyaar.saveyaar_movies.model.UserPreference preference) {
        if(preference != null) {
            this.languages = preference.getLanguages().stream().map(Language::new).toList();
            this.genres = preference.getGenres().stream().map(Genre::new).toList();
            this.people_involved = preference.getPeople_involved().stream().map(People::new).toList();
            this.otts = preference.getOtts().stream().map(Ott::new).toList();
        }
    }
}
