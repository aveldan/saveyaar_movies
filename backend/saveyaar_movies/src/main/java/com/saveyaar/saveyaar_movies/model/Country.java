package com.saveyaar.saveyaar_movies.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity @Table(name = "countries")
public class Country {
    
    @Id
    private String country_iso;

    @Column(nullable = false, unique = true)
    private String name;
    
    @Column
    private String native_name;
}
