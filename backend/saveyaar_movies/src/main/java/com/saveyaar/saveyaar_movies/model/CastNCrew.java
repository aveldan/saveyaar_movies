package com.saveyaar.saveyaar_movies.model;

import java.util.List;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter
@RequiredArgsConstructor @AllArgsConstructor
@Embeddable
public class CastNCrew {

    private int person_id;
    @NonNull private String display_name;
    private String image_path;
    private List<String> crew_roles;
    private List<Cast> cast_roles;
}
