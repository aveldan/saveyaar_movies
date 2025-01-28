package com.saveyaar.saveyaar_movies.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Table(name = "user_preference")
@Data @NoArgsConstructor @AllArgsConstructor
public class UserPreference {
    
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false, unique = true)
    private int user_id;

    @Column @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Language> languages;
    
    @Column @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Genre> genres;
    
    @Column @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<People> people_involved;
    
    @Column @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ott> otts;
    
    @Column
    private float min_rating;

    @Column
    private String device_token;

    @ManyToOne
    @JoinColumn(name = "country_iso", nullable = false)
    private Country country;
}
