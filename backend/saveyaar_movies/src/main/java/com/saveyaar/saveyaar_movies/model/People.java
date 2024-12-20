package com.saveyaar.saveyaar_movies.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NonNull;

@Entity @AllArgsConstructor
@Table(name = "People")
public class People {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column @NonNull
    private String name;

    @Column
    private String image_path;

    @Column
    private String summary;

    @Column
    private double popularity;

    @Column
    private LocalDate birth_date;

    @Column
    private LocalDate death_date;
}
