package com.saveyaar.saveyaar_movies.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToMany;
import lombok.Data;

@MappedSuperclass
@Data
public class Content {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int content_id;

    @Column
    private String title;

    @Column
    private String overview;

    @OneToMany(mappedBy = "content", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OttRecord> ott_availability;

    @Column
    private String poster_path;

    @ManyToMany
    private List<Genre> genres;

    @ElementCollection
    private List<Language> languages;

    @ElementCollection
    private List<ReleaseDate> release_dates;

    @ElementCollection
    private List<CastNCrew> cast_and_crew;
}
