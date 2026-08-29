package com.example.bookingsystem.service;

import com.example.bookingsystem.dto.ShowRequestDTO;
import com.example.bookingsystem.entity.Show;

import java.util.List;

public interface ShowService {

    Show createShow(ShowRequestDTO request);

    List<Show> getAllShows();

    Show getShowById(Long id);

    Show updateShow(Long id, ShowRequestDTO request);

    void deleteShow(Long id);
}