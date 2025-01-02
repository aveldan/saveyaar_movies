package com.saveyaar.saveyaar_movies.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity @AllArgsConstructor @NoArgsConstructor
@Table(name = "People")
@Data
public class People {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private String external_id;

    @Column @NonNull
    private String display_name;

    @Column @NonNull
    private String original_name;

    @Column
    private String image_path;

    @Column(columnDefinition = "TEXT")
    @Lob
    private String summary;

    @Column
    private double popularity;

    @Column
    private LocalDate birth_date;

    @Column
    private LocalDate death_date;
}
