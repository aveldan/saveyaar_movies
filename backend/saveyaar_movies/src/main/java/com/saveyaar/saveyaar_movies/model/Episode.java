package com.saveyaar.saveyaar_movies.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data @AllArgsConstructor
@Embeddable
public class Episode {

    @NonNull private String episode_name;
    private String overview;
    private int episode_number;
    private String poster_path;
}
