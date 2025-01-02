package com.saveyaar.saveyaar_movies.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class TMDBDiscover {
    private int page;
    private List<TMDBDiscoverResult> results;
    private int total_pages;
    private int total_results;

    @Data @NoArgsConstructor @AllArgsConstructor
    public static class TMDBDiscoverResult {
        private String title;
        private int id;
    }
}
