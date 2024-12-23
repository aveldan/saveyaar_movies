package com.saveyaar.saveyaar_movies.service;

import java.util.List;

import com.saveyaar.saveyaar_movies.model.Country;
import com.saveyaar.saveyaar_movies.model.Genre;
import com.saveyaar.saveyaar_movies.model.Language;
import com.saveyaar.saveyaar_movies.model.Ott;

public interface Ingest {
    
    public List<Genre> ingestGenre();
    public List<Country> ingestCountries();
    public List<Language> ingestLanguages();
    public List<Ott> ingestOtts();

}
