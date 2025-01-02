package com.saveyaar.saveyaar_movies.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class TMDBPerson {
    private int id;
    private String biography;
    private String birthday;
    private String deathday;
    private String profile_path;
    private String imdb_id;
    private double popularity;
    private String name;
}
