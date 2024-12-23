package com.saveyaar.saveyaar_movies.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class TMDBOttList {
    private List<TMDBOtt> results;
}
