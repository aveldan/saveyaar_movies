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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity @Table(name = "Seasons")
@Data @AllArgsConstructor
public class Season {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private String season_name;

    @Column(nullable = false)
    private int season_number;

    @Column
    private String overview;

    @Column
    private String poster_path;

    @ManyToOne
    @JoinColumn(name = "series_id")
    private Series series;
    
    @OneToMany(mappedBy = "season", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Episode> episodes;

    @ElementCollection
    @CollectionTable(name = "season_languages", joinColumns = @JoinColumn(name = "season_id"))
    private Set<ContentLanguage> languages;

    @OneToMany(mappedBy = "season", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<OttRecord> ott_availability;
}
