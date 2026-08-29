package com.example.bookingsystem.service;

import com.example.bookingsystem.dto.SeatRequestDTO;
import com.example.bookingsystem.entity.Seat;

import java.util.List;

public interface SeatService {

    Seat createSeat(SeatRequestDTO request);

    Seat getSeatById(Long id);

    List<Seat> getAllSeats();

    Seat updateSeat(Long id, SeatRequestDTO request);

    Seat holdSeat(Long id, SeatRequestDTO request);

    void deleteSeat(Long id);

    void releaseExpiredSeats();
}