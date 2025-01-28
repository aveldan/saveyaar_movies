package com.saveyaar.saveyaar_movies.DTO;

import java.util.ArrayList;
import java.util.List;

import com.saveyaar.saveyaar_movies.model.Movie;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class DiscoverDTO {
    @Data @NoArgsConstructor @AllArgsConstructor
    public class Item {
        private int item_id;
        private String poster_path;
        private String title;
        private float rating;

        public Item(Movie mv) {
            if(mv != null) {
                this.item_id = mv.getId();
                this.poster_path = mv.getPoster_path();
                this.title = mv.getTitle();
                this.rating = mv.getRating();
            }
        }
    }

    @Data @NoArgsConstructor @AllArgsConstructor
    public class Categorie {
        private String list_title;
        private List<Item> items;
    }

    private List<Categorie> categories;

    public void addCategory(String title, List<Movie> mvs) {
        if(mvs == null)
            return;
        if(this.categories == null)
            this.categories = new ArrayList<>();
        
        List<Item> items = mvs.stream().map(Item::new).toList();
        this.categories.add(new Categorie(title, items));
    }
}
