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
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity @Table(name = "Content")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Content {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private String title;

    @Column
    private String overview;

    @Column
    private String poster_path;

    @Column
    private double rating;

    @OneToMany(mappedBy = "content", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<OttRecord> ott_availability;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "content_genre",
        joinColumns = @JoinColumn(name = "content_id"),
        inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private Set<Genre> genres;

    @ElementCollection
    @CollectionTable(name = "content_languages", joinColumns = @JoinColumn(name = "content_id"))
    private Set<ContentLanguage> languages;

    @ElementCollection
    @CollectionTable(name = "release_dates", joinColumns = @JoinColumn(name = "content_id"))
    private Set<ReleaseDate> release_dates;

    @OneToMany(mappedBy = "content", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<CastNCrew> cast_and_crew;

    public void addOttRecord(OttRecord record){
        ott_availability.add(record);
        record.setContent(this);
    }

    public void removeOttRecord(OttRecord record){
        ott_availability.remove(record);
        record.setContent(null);
    }

    public void addCastNCrew(CastNCrew record){
        cast_and_crew.add(record);
        record.setContent(this);
    }

    public void removeCastNCrew(CastNCrew record){
        cast_and_crew.remove(record);
        record.setContent(null);
    }
}
