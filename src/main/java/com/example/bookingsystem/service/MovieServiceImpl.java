package com.example.bookingsystem.service;

import com.example.bookingsystem.dto.MovieRequestDTO;
import com.example.bookingsystem.entity.Movie;
import com.example.bookingsystem.exception.MovieNotFoundException;
import com.example.bookingsystem.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    @Override
    public Movie createMovie(MovieRequestDTO request) {

        Movie movie = new Movie();

        movie.setTitle(request.getTitle());
        movie.setLanguage(request.getLanguage());
        movie.setDuration(request.getDuration());

        return movieRepository.save(movie);
    }

    @Override
    public List<Movie> getAllMovies() {

        return movieRepository.findAll();
    }

    @Override
    public Movie getMovieById(Long id) {

        return movieRepository.findById(id)
                .orElseThrow(() ->
                        new MovieNotFoundException("Movie not found"));
    }

    @Override
    public Movie updateMovie(Long id, MovieRequestDTO request) {

        Movie movie = movieRepository.findById(id)
                .orElseThrow(() ->
                        new MovieNotFoundException("Movie not found"));

        movie.setTitle(request.getTitle());
        movie.setLanguage(request.getLanguage());
        movie.setDuration(request.getDuration());

        return movieRepository.save(movie);
    }

    @Override
    public void deleteMovie(Long id) {

        Movie movie = movieRepository.findById(id)
                .orElseThrow(() ->
                        new MovieNotFoundException("Movie not found"));

        movieRepository.delete(movie);
    }
}
