package com.example.bookingsystem.service;

import com.example.bookingsystem.dto.ShowRequestDTO;
import com.example.bookingsystem.entity.Movie;
import com.example.bookingsystem.entity.Show;
import com.example.bookingsystem.exception.MovieNotFoundException;
import com.example.bookingsystem.exception.ShowNotFoundException;
import com.example.bookingsystem.repository.MovieRepository;
import com.example.bookingsystem.repository.ShowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShowServiceImpl implements ShowService {

    private final ShowRepository showRepository;
    private final MovieRepository movieRepository;

    @Override
    public Show createShow(ShowRequestDTO request) {

        Movie movie = movieRepository.findById(
                request.getMovieId()
        ).orElseThrow(() ->
                new MovieNotFoundException(
                        "Movie not found with id: "
                                + request.getMovieId()
                ));

        Show show = new Show();

        show.setMovie(movie);
        show.setShowDate(request.getShowDate());
        show.setShowTime(request.getShowTime());
        show.setTheatre(request.getTheatre());

        return showRepository.save(show);
    }

    @Override
    public Show getShowById(Long id) {

        return showRepository.findById(id)
                .orElseThrow(() ->
                        new ShowNotFoundException(
                                "Show not found with id: " + id
                        ));
    }

    @Override
    public List<Show> getAllShows() {

        return showRepository.findAll();
    }

    @Override
    public Show updateShow(
            Long id,
            ShowRequestDTO request) {

        Show show = showRepository.findById(id)
                .orElseThrow(() ->
                        new ShowNotFoundException(
                                "Show not found with id: " + id
                        ));

        if (request.getMovieId() != null) {

            Movie movie = movieRepository.findById(
                    request.getMovieId()
            ).orElseThrow(() ->
                    new MovieNotFoundException(
                            "Movie not found with id: "
                                    + request.getMovieId()
                    ));

            show.setMovie(movie);
        }

        if (request.getShowDate() != null) {

            show.setShowDate(
                    request.getShowDate()
            );
        }

        if (request.getShowTime() != null) {

            show.setShowTime(
                    request.getShowTime()
            );
        }

        if (request.getTheatre() != null) {

            show.setTheatre(
                    request.getTheatre()
            );
        }

        return showRepository.save(show);
    }

    @Override
    public void deleteShow(Long id) {

        Show show = showRepository.findById(id)
                .orElseThrow(() ->
                        new ShowNotFoundException(
                                "Show not found with id: " + id
                        ));

        showRepository.delete(show);
    }
    }