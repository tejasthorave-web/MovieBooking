package com.example.Movie.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "movie_show")
public class Show {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long movieId;

    private Long theatreId;

    private Long screenId;

    private LocalDateTime showTime;

    private Double price;
    private Integer availableSeats;

//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    private Long movieId;
//    private Long theatreId;
//    @Column(name = "show_time")
//    private LocalDateTime showTime;
//    private int availableSeats;
//    private Long screenId;
//    private Double price;
}
