package com.saveyaar.saveyaar_movies.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Table(name = "OTTs")
@Data @AllArgsConstructor @NoArgsConstructor
public class Ott {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private String home_page;
    
    @Column(nullable = false, unique = true)
    private String name;
    
    @Column
    private String logo_path;
}
