package com.saveyaar.saveyaar_movies.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data @AllArgsConstructor
@Embeddable @NoArgsConstructor
public class ReleaseDate {
    @Column(nullable = false)
    @NonNull private String country_iso;
    
    private String certification;
    
    private LocalDate release_date;
}
