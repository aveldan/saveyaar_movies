package com.saveyaar.saveyaar_movies.model;

import java.time.LocalDateTime;

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

@Entity @Table(name = "OTT_Records")
@Data @AllArgsConstructor
public class OttRecord {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;
    
    @ManyToOne
    @JoinColumn(name = "season_id")
    private Season season;

    @Column
    private String country_iso;
    
    @ManyToOne
    @JoinColumn(name = "ott_id", nullable = false)
    @NonNull private Ott ott;    
    
    @Column
    @NonNull private LocalDateTime start_date;
    
    @Column
    private LocalDateTime end_date;
}
