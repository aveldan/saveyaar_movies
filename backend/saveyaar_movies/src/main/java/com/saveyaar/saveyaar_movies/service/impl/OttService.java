package com.saveyaar.saveyaar_movies.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saveyaar.saveyaar_movies.model.Ott;
import com.saveyaar.saveyaar_movies.repository.OttRepository;

@Service
public class OttService {

    @Autowired
    private OttRepository ottRepository;

    List<Ott> getOtts(List<String> ott_names) {
        return ottRepository.findAllByName(ott_names);
    }
}
