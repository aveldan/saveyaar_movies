package com.saveyaar.saveyaar_movies.model;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Entity @Table(name = "cast_and_crew")
@Data @AllArgsConstructor
public class CastNCrew {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private People person;

    @ManyToOne
    @JoinColumn(name = "content_id")
    private Content content;

    
    @NonNull private String display_name;
    
    @Column
    private String image_path;
    
    @Column
    private Set<String> roles;
}
