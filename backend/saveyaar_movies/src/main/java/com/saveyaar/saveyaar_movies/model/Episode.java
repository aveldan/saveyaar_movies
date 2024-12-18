package com.saveyaar.saveyaar_movies.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@AllArgsConstructor @RequiredArgsConstructor
@Embeddable
public class Episode {

    @NonNull private String episode_name;
    private String overview;
    private int episode_number;
}
