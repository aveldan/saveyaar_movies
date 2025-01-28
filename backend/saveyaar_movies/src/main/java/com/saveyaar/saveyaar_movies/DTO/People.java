package com.saveyaar.saveyaar_movies.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class People {
    private String display_name;
    private String image_path;

    public People(com.saveyaar.saveyaar_movies.model.People person) {
        if(person != null) {
            this.display_name = person.getDisplay_name();
            this.image_path = person.getImage_path();
        }
    }
}
