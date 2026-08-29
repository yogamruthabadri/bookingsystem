package com.example.bookingsystem.service;

import com.example.bookingsystem.dto.MovieRequestDTO;
import com.example.bookingsystem.entity.Movie;

import java.util.List;

public interface MovieService {

    Movie createMovie(MovieRequestDTO request);

    List<Movie> getAllMovies();

    Movie getMovieById(Long id);

    Movie updateMovie(Long id, MovieRequestDTO request);

    void deleteMovie(Long id);
}