package com.saveyaar.saveyaar_movies.model;

import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
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

@Entity @Table(name = "movies")
@Data
public class Movie{

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private String title;
    
    @Column
    private String original_title;
    
    @Column
    private String overview;
    
    @Column
    private String poster_path;

    @Column
    private String backdrop_path;

    @Column
    private float rating;

    @Column
    private float budget;

    @Column
    private String external_id;
    
    @Column
    private double popularity;
    
    @Column
    private int runtime;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "movie_genre",
        joinColumns = @JoinColumn(name = "movie_id"),
        inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private Set<Genre> genres;

    @ElementCollection
    @CollectionTable(name = "movie_languages", joinColumns = @JoinColumn(name = "movie_id"))
    private Set<ContentLanguage> languages;

    @ElementCollection
    @CollectionTable(name = "movie_release_dates", joinColumns = @JoinColumn(name = "movie_id"))
    private Set<ReleaseDate> release_dates;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Credit> credits;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<OttRecord> ott_availability;

    public void addOttRecord(OttRecord record){
        ott_availability.add(record);
        record.setMovie(this);
    }

    public void removeOttRecord(OttRecord record){
        ott_availability.remove(record);
        record.setMovie(null);
    }

    public void addCredit(Credit record){
        credits.add(record);
        record.setMovie(this);
    }

    public void removeCredit(Credit record){
        credits.remove(record);
        record.setMovie(null);
    }
}
