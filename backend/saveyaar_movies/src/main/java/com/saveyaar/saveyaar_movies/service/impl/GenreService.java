package com.saveyaar.saveyaar_movies.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saveyaar.saveyaar_movies.model.Genre;
import com.saveyaar.saveyaar_movies.repository.GenreRepository;

@Service
public class GenreService {

    @Autowired
    private GenreRepository genreRepository;

    public List<Genre> getGenres(List<String> genres) {
       return genreRepository.findAllByGenre(genres);
    }
}
