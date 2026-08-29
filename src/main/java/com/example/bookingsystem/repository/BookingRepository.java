package com.example.bookingsystem.repository;

import com.example.bookingsystem.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository
        extends JpaRepository<Booking, Long> {
}