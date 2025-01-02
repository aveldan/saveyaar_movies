package com.saveyaar.saveyaar_movies.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data @EqualsAndHashCode(callSuper = true)
public class Cast extends Credit{

    @Column
    // Write a custom to convertor to store it as a single string
    private Set<String> characters;

    public Set<String> addCharacters(List<String> characters) {
        if(characters == null || characters.size() < 0)
            return this.characters;
        
        if(this.characters == null)
            this.characters = new HashSet<>();
        
        this.characters.addAll(characters);
        return this.characters;
    }
}
