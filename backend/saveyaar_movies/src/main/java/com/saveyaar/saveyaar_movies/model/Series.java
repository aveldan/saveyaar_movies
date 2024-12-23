package com.saveyaar.saveyaar_movies.model;

import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity @Table(name = "series")
@Data 
public class Series{

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private String title;
    
    @Column
    private String overview;
    
    @Column
    private String poster_path;

    @Column
    private String backdrop_path;

    @Column
    private double rating;

    @Column
    private String external_id;
    
    @Column
    private double popularity;
    
    @OneToMany(mappedBy = "series", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Season> seasons;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "series_genre",
        joinColumns = @JoinColumn(name = "series_id"),
        inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private Set<Genre> genres;

}
