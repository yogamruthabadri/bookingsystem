package com.example.bookingsystem.controller;

import com.example.bookingsystem.dto.MovieRequestDTO;
import com.example.bookingsystem.entity.Movie;
import com.example.bookingsystem.exception.MovieNotFoundException;
import com.example.bookingsystem.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @PostMapping("/create/movie")
    public ResponseEntity<MovieRequestDTO> createMovie(
            @RequestBody MovieRequestDTO request) {

        try {

            Movie movie =
                    movieService.createMovie(request);

            request.setMessage(
                    "Movie successfully registered"
            );

            return ResponseEntity.ok(request);

        } catch (IllegalArgumentException e) {

            request.setMessage(e.getMessage());

            return ResponseEntity.badRequest()
                    .body(request);
        }
    }

    @GetMapping("/get/movie/{id}")
    public ResponseEntity<MovieRequestDTO> getMovie(
            @PathVariable Long id) {

        try {

            Movie movie =
                    movieService.getMovieById(id);

            MovieRequestDTO response =
                    new MovieRequestDTO();

            response.setMessage(
                    "Movie retrieved successfully"
            );

            return ResponseEntity.ok(response);

        } catch (MovieNotFoundException e) {

            MovieRequestDTO response =
                    new MovieRequestDTO();

            response.setMessage(e.getMessage());

            return ResponseEntity.badRequest()
                    .body(response);

        } catch (IllegalArgumentException e) {

            MovieRequestDTO response =
                    new MovieRequestDTO();

            response.setMessage(e.getMessage());

            return ResponseEntity.badRequest()
                    .body(response);
        }
    }

    @GetMapping("/get/all/movies")
    public ResponseEntity<?> getAllMovies() {

        try {

            return ResponseEntity.ok(
                    movieService.getAllMovies()
            );

        } catch (IllegalArgumentException e) {

            return ResponseEntity.badRequest()
                    .build();
        }
    }

    @PutMapping("/update/movie/{id}")
    public ResponseEntity<MovieRequestDTO> updateMovie(
            @PathVariable Long id,
            @RequestBody MovieRequestDTO request) {

        try {

            Movie movie =
                    movieService.updateMovie(id, request);

            request.setMessage(
                    "Movie successfully updated"
            );

            return ResponseEntity.ok(request);

        } catch (MovieNotFoundException e) {

            request.setMessage(e.getMessage());

            return ResponseEntity.badRequest()
                    .body(request);

        } catch (IllegalArgumentException e) {

            request.setMessage(e.getMessage());

            return ResponseEntity.badRequest()
                    .body(request);
        }
    }

    @DeleteMapping("/delete/movie/{id}")
    public ResponseEntity<MovieRequestDTO> deleteMovie(
            @PathVariable Long id) {

        try {

            movieService.deleteMovie(id);

            MovieRequestDTO response =
                    new MovieRequestDTO();

            response.setMessage(
                    "Movie successfully deleted"
            );

            return ResponseEntity.ok(response);

        } catch (MovieNotFoundException e) {

            MovieRequestDTO response =
                    new MovieRequestDTO();

            response.setMessage(e.getMessage());

            return ResponseEntity.badRequest()
                    .body(response);

        } catch (IllegalArgumentException e) {

            MovieRequestDTO response =
                    new MovieRequestDTO();

            response.setMessage(e.getMessage());

            return ResponseEntity.badRequest()
                    .body(response);
        }
    }
}