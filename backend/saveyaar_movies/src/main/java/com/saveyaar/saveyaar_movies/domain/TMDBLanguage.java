package com.saveyaar.saveyaar_movies.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public class TMDBLanguage {
    private String iso_639_1;
    private String english_name;
    private String name;
}
