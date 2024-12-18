package com.saveyaar.saveyaar_movies.model;

import java.time.LocalDate;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@RequiredArgsConstructor @AllArgsConstructor
@Embeddable
public class ReleaseDate {

    @NonNull private String country_iso;
    private String certification;
    private LocalDate release_date;
}
