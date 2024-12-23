package com.saveyaar.saveyaar_movies.model;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data @EqualsAndHashCode(callSuper = true)
public class Crew extends Credit{

    @Column
    // Write a custom to convertor to store it as a single string
    private Set<String> roles;
}
