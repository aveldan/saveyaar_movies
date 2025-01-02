package com.saveyaar.saveyaar_movies.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class TMDBMovie {
    private int id;
    private String backdrop_path;
    private float budget;
    private String imdb_id;
    private List<TMDBGenre> genres;
    private String original_language;
    private String original_title;
    private String overview;
    private double popularity;
    private String poster_path;
    private int runtime;
    private float revenue;
    private String title;
    private TMDBReleaseDates release_dates;
    private TMDBTranslations translations;
    @JsonProperty("watch/providers")
    private TMDBWatchProviders watch_providers;
    private TMDBCredits credits;

    @Data @NoArgsConstructor @AllArgsConstructor
    public static class TMDBGenre {
        private int id;
        private String name;
    }

    @Data @NoArgsConstructor @AllArgsConstructor
    public static class TMDBReleaseDates {
        private List<Result> results;

        @Data @NoArgsConstructor @AllArgsConstructor
        public static class Result {
            private String iso_3166_1;
            private List<ReleaseDate> release_dates;

            @Data @NoArgsConstructor @AllArgsConstructor
            public static class ReleaseDate {
                private String certification;
                private List<String> descriptors;
                private String iso_639_1;
                private String note;
                private String release_date;
                private int type;
            }
        }
    }

    @Data @NoArgsConstructor @AllArgsConstructor
    public static class TMDBTranslations {
        private List<Translation> translations;

        @Data @NoArgsConstructor @AllArgsConstructor
        public static class Translation {
            private String iso_3166_1;
            private String iso_639_1;
            private String name;
            private String english_name;
            private TranslationData data;

            @Data @NoArgsConstructor @AllArgsConstructor
            public static class TranslationData {
                private String homepage;
                private String overview;
                private int runtime;
                private String tagline;
                private String title;
            }
        }
    }

    @Data @NoArgsConstructor @AllArgsConstructor
    public static class TMDBWatchProviders {
        @JsonProperty("results")
        private Results results;

        @Data @NoArgsConstructor @AllArgsConstructor
        public static class Results {
            @JsonProperty("IN")
            private WatchProviders IN;

            @JsonProperty("US")
            private WatchProviders US;

            @Data @NoArgsConstructor @AllArgsConstructor
            public static class WatchProviders {
                private String link;
                private List<Provider> flatrate;
                private List<Provider> rent;
                private List<Provider> buy;
                private List<Provider> ads;

                @Data @NoArgsConstructor @AllArgsConstructor
                public static class Provider {
                    private String logo_path;
                    private int provider_id;
                    private String provider_name;
                    private int display_priority;
                }
            }
        }
    }

    @Data @NoArgsConstructor @AllArgsConstructor
    public static class TMDBCredits {
        private List<Cast> cast;
        private List<Crew> crew;

        @Data @NoArgsConstructor @AllArgsConstructor
        public static class Cast {
            private boolean adult;
            private int gender;
            private int id;
            private String known_for_department;
            private String name;
            private String original_name;
            private double popularity;
            private String profile_path;
            private int cast_id;
            private String character;
            private String credit_id;
            private int order;
        }

        @Data @NoArgsConstructor @AllArgsConstructor
        public static class Crew {
            private boolean adult;
            private int gender;
            private int id;
            private String known_for_department;
            private String name;
            private String original_name;
            private double popularity;
            private String profile_path;
            private String credit_id;
            private String department;
            private String job;
        }
   }
}