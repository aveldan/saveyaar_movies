package com.saveyaar.saveyaar_movies.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saveyaar.saveyaar_movies.model.Language;
import com.saveyaar.saveyaar_movies.repository.LanguageRepository;

@Service
public class LanguageService {

    @Autowired
    private LanguageRepository languageRepository;

    public String getLanguage(String iso) {
        Language lang = languageRepository.findByISO(iso);
        if(lang == null)
            return null;
        
        return lang.getName();  
    }

    public List<Language> getLanguages(List<String> languages) {
        return languageRepository.findAllByLanguage(languages);
    }
}
