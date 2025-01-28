package com.saveyaar.saveyaar_movies.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class Genre {
    private String genre;

    public Genre(com.saveyaar.saveyaar_movies.model.Genre genre){
        if(genre != null) {
            this.genre = genre.getGenre();
        }
    }
}
