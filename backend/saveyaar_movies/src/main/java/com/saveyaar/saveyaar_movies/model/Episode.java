package com.saveyaar.saveyaar_movies.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
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
import lombok.NonNull;

@Data @AllArgsConstructor
@Entity @Table(name = "episodes")
public class Episode {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    @NonNull private String episode_name;
    
    @Column
    private String overview;
    
    @Column(nullable = false)
    private int episode_number;
    
    @Column
    private String backdrop_path;

    @Column
    private LocalDateTime air_date;

    @Column
    private float rating;
    
    @Column
    private int runtime;
    
    @ManyToOne
    @JoinColumn(name = "season_id", nullable = false)
    private Season season;

    @OneToMany(mappedBy = "episode", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Credit> credits;

    public void addCredit(Credit record){
        credits.add(record);
        record.setEpisode(this);
    }

    public void removeCredit(Credit record){
        credits.remove(record);
        record.setEpisode(null);
    }
}
