package com.saveyaar.saveyaar_movies.domain.DTOs;

import java.util.Set;

import com.saveyaar.saveyaar_movies.model.Cast;
import com.saveyaar.saveyaar_movies.model.Crew;
import com.saveyaar.saveyaar_movies.model.People;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class CreditDTO {
    private int person_id;
    private String display_name;
    private double popularity;
    private String image_path;
    private int movie_id;
    private int episode_id;
    private Set<String> characters;
    private Set<String> roles;
    private String type;

    public CreditDTO(String type, Crew credit, int id) {
        if(type == "Movie")
            this.movie_id = id;
        else if(type == "Episode")
            this.episode_id = id;
        
        People person = credit.getPerson();
        this.person_id = person.getId();
        this.display_name = person.getDisplay_name();
        this.popularity = person.getPopularity();
        this.image_path = person.getImage_path();
        this.roles = credit.getRoles();
        this.type = "crew";
    }

    public CreditDTO(String type, Cast credit, int id) {
        if(type == "Movie")
            this.movie_id = id;
        else if(type == "Episode")
            this.episode_id = id;
        
        People person = credit.getPerson();
        this.person_id = person.getId();
        this.display_name = person.getDisplay_name();
        this.popularity = person.getPopularity();
        this.image_path = person.getImage_path();
        this.roles = credit.getCharacters();
        this.type = "cast";
    }
}
