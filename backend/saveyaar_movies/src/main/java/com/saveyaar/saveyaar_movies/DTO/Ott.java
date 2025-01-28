package com.saveyaar.saveyaar_movies.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class Ott {
    private String ott_name;
    private String logo_path;

    public Ott(com.saveyaar.saveyaar_movies.model.Ott ott) {
        if(ott != null) {
            this.ott_name = ott.getName();
            this.logo_path = ott.getLogo_path();
        }
    }
}
