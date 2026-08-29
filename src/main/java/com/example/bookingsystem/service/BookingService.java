package com.example.bookingsystem.service;

import com.example.bookingsystem.dto.BookingRequestDTO;
import com.example.bookingsystem.entity.Booking;

import java.util.List;

public interface BookingService {

    Booking createBooking(BookingRequestDTO request);

    List<Booking> getAllBookings();

    Booking getBookingById(Long id);

    Booking updateBooking(
            Long id,
            BookingRequestDTO request);

    void deleteBooking(Long id);
}