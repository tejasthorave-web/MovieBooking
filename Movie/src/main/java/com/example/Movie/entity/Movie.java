package com.example.Movie.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Id;


@Entity
@Data
@AllArgsConstructor
@Builder
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String genre;
    private String language;
    private int duration;

    public Movie() {}

    public Movie(String title, String language, String genre, int duration) {
        this.title = title;
        this.language = language;
        this.genre = genre;
        this.duration = duration;
    }
}
