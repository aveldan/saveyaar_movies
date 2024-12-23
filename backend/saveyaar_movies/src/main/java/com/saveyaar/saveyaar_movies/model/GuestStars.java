package com.saveyaar.saveyaar_movies.model;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data @EqualsAndHashCode(callSuper = true)
public class GuestStars extends Credit{

}
