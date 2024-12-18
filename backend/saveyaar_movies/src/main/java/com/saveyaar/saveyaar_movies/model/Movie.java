package com.saveyaar.saveyaar_movies.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "Movies")
@Data @EqualsAndHashCode(callSuper = true)
public class Movie extends Content{
    
    @Column
    private String backdrop_path;
}
