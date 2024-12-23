package com.saveyaar.saveyaar_movies.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public class TMDBCountry {
    private String iso_3166_1;
    private String english_name;
    private String native_name;
}
