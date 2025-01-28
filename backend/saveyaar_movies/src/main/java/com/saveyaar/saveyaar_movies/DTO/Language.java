package com.saveyaar.saveyaar_movies.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class Language {
    private String english_name;
    private String native_name;

    public Language(com.saveyaar.saveyaar_movies.model.Language language) {
        if(language != null) {
            this.english_name = language.getName();
            this.native_name = language.getNative_name();
        }
    }
}
