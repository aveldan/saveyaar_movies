package com.saveyaar.saveyaar_movies.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity @Table(name = "OTTs")
@Data
public class Ott {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int ott_id;

    @Column
    private String home_page;

    @Column
    private String logo_path;
}
