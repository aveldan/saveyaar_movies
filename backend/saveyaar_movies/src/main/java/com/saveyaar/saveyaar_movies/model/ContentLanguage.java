package com.saveyaar.saveyaar_movies.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data @AllArgsConstructor
@Embeddable
public class ContentLanguage {

    @Column(nullable = false)
    @NonNull private String language_iso;

    private String local_title;
    
    @Column(nullable = false)
    private boolean is_original_language;
}
