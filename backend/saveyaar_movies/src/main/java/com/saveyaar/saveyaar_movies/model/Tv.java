package com.saveyaar.saveyaar_movies.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity @Table(name = "Tvs")
@Data @EqualsAndHashCode(callSuper = true)
public class Tv extends Content{

    @OneToMany(mappedBy = "tv", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Season> seasons;
}
