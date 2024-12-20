package com.saveyaar.saveyaar_movies.model;

import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

    @Column
    private int season_number;

    @Column
    private String overview;

    @Column
    private String backdrop_path;

    @ManyToOne
    @JoinColumn(name = "tv_id")
    private Tv tv_show;
    
    @ElementCollection
    @CollectionTable(name = "Episodes", joinColumns = @JoinColumn(name = "season_id"))
    private List<Episode> episodes;
}
