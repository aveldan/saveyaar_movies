package com.saveyaar.saveyaar_movies.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@NoArgsConstructor 
@AllArgsConstructor
public class Cast {

    private String role;
    private String character_name;
}
