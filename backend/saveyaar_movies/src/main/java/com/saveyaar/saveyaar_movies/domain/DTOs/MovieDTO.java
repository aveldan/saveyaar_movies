package com.saveyaar.saveyaar_movies.domain.DTOs;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.saveyaar.saveyaar_movies.model.Cast;
import com.saveyaar.saveyaar_movies.model.Credit;
import com.saveyaar.saveyaar_movies.model.Crew;
import com.saveyaar.saveyaar_movies.model.Movie;
import com.saveyaar.saveyaar_movies.model.OttRecord;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class MovieDTO {
    private int id;
    private String title;
    private String overview;
    private String poster_path;
    private String backdrop_path;
    private float rating;
    private float budget;
    private float revenue;
    private int runtime;
    private List<String> genres;
    private List<String> languages;
    private LocalDate releasDate;
    private List<CreditDTO> credits;
    private List<OttRecordDTO> ott_availability;

    public MovieDTO(Movie movie) {
        this.id = movie.getId();
        this.title = movie.getTitle();
        this.overview = movie.getOverview();
        this.poster_path = movie.getPoster_path();
        this.backdrop_path = movie.getBackdrop_path();
        this.rating = movie.getRating();
        this.budget = movie.getBudget();
        this.revenue = movie.getRevenue();
        this.runtime = movie.getRuntime();
    }

    public void setCredits(List<Credit> credits) {
        if(this.credits == null)
            this.credits = new ArrayList<>();
        for(Credit credit: credits) {
            if( credit instanceof Crew) {
                this.credits.add(new CreditDTO("Movie", (Crew) credit, this.id));
            } else if(credit instanceof Cast) {
                this.credits.add(new CreditDTO("Movie", (Cast) credit, this.id));
            }
        }

        this.credits.sort((c1, c2) -> Double.compare(c2.getPopularity(), c1.getPopularity()));
    }

    public void setOttAvalability(List<OttRecord> otts, String country_iso) {
        if(this.ott_availability == null)
            this.ott_availability = new ArrayList<>();
        
        for(OttRecord record: otts) {
            if(country_iso.equals(record.getCountry_iso()))
                this.ott_availability.add(new OttRecordDTO(record));
        }
    }
}
