package com.saveyaar.saveyaar_movies.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity @Table(name = "OTTs")
@Data @AllArgsConstructor
public class Ott {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private String home_page;

    @Column
    private String logo_path;
}
