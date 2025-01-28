package com.saveyaar.saveyaar_movies.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saveyaar.saveyaar_movies.model.People;
import com.saveyaar.saveyaar_movies.repository.PeopleRepository;

@Service
public class PeopleService {

    @Autowired
    private PeopleRepository peopleRepository;

    public List<People> getPeople(List<String> display_name) {
        return peopleRepository.findAllByDisplayName(display_name);
    }
}
