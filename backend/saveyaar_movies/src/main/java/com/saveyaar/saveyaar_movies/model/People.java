package com.saveyaar.saveyaar_movies.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "People")
public class People {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int person_id;

    @Column
    private String first_name;

    @Column
    private String last_name;

    @Column
    private String other_name;

    @Column
    private String display_name;

    @Column
    private String image_path;

    @Column
    private String summary;
}
