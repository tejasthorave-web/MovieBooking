package com.example.Movie.service;

import com.example.Movie.entity.Movie;
import com.example.Movie.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    private final MovieRepository movieRepository;
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }
    public Movie addMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Movie getMovie(Long id) {
        return movieRepository.findById(id).orElseThrow();
    }

    public Movie updateMovie(Long id, Movie updatedMovie) {
        return movieRepository.findById(id).map(movie -> {
            movie.setTitle(updatedMovie.getTitle());
            movie.setLanguage(updatedMovie.getLanguage());
            movie.setGenre(updatedMovie.getGenre());
            movie.setDuration(updatedMovie.getDuration());
            return movieRepository.save(movie);
        }).orElseThrow(() -> new RuntimeException("Movie not found with id " + id));
    }

    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }
}
