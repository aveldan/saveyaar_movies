package com.saveyaar.saveyaar_movies.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data @AllArgsConstructor
@Embeddable
public class ContentLanguage {

    @NonNull private String language_iso;
    private String local_title;
    private boolean is_original_language;
}
