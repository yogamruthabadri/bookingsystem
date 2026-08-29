package com.example.bookingsystem.repository;

import com.example.bookingsystem.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Long> {

    List<Seat> findAllByOrderByIdDesc();
}