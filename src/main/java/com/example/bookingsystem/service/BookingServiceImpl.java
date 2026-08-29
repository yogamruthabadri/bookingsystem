package com.example.bookingsystem.service;

import com.example.bookingsystem.dto.BookingRequestDTO;
import com.example.bookingsystem.entity.Booking;
import com.example.bookingsystem.entity.Seat;
import com.example.bookingsystem.entity.Show;
import com.example.bookingsystem.enums.BookingStatus;
import com.example.bookingsystem.enums.SeatStatus;
import com.example.bookingsystem.exception.BookingNotFoundException;
import com.example.bookingsystem.exception.SeatNotAvailableException;
import com.example.bookingsystem.exception.ShowNotFoundException;
import com.example.bookingsystem.repository.BookingRepository;
import com.example.bookingsystem.repository.SeatRepository;
import com.example.bookingsystem.repository.ShowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final SeatRepository seatRepository;
    private final ShowRepository showRepository;

    @Override
    public Booking createBooking(
            BookingRequestDTO request) {

        Seat seat = seatRepository.findById(
                        request.getSeatId())
                .orElseThrow(() ->
                        new SeatNotAvailableException(
                                "Seat not found"));

        Show show = showRepository.findById(
                        request.getShowId())
                .orElseThrow(() ->
                        new ShowNotFoundException(
                                "Show not found"));

        if (seat.getStatus() != SeatStatus.HELD) {

            throw new SeatNotAvailableException(
                    "Seat is not held");
        }

        if (request.getUserId() == seat.getHeldBy()) {

            throw new SeatNotAvailableException(
                    "Seat is held by another user");
        }

        if (seat.getHoldExpiryTime() == null ||
                seat.getHoldExpiryTime()
                        .isBefore(LocalDateTime.now())) {

            throw new SeatNotAvailableException(
                    "Seat hold expired");
        }

        Booking booking = new Booking();

        booking.setUserId(request.getUserId());
        booking.setSeat(seat);
        booking.setShow(show);
        booking.setBookingStatus(
                BookingStatus.CONFIRMED);
        booking.setBookingTime(
                LocalDateTime.now());

        seat.setStatus(SeatStatus.BOOKED);
        seat.setHeldBy(null);
        seat.setHoldExpiryTime(null);

        seatRepository.save(seat);

        return bookingRepository.save(booking);
    }

    @Override
    public List<Booking> getAllBookings() {

        return bookingRepository.findAll();
    }

    @Override
    public Booking getBookingById(Long id) {

        return bookingRepository.findById(id)
                .orElseThrow(() ->
                        new BookingNotFoundException(
                                "Booking not found"));
    }

    @Override
    public Booking updateBooking(
            Long id,
            BookingRequestDTO request) {

        Booking booking = bookingRepository
                .findById(id)
                .orElseThrow(() ->
                        new BookingNotFoundException(
                                "Booking not found"));

        if (request.getUserId() != null) {
            booking.setUserId(
                    request.getUserId());
        }

        if (request.getBookingStatus() != null) {
            booking.setBookingStatus(
                    request.getBookingStatus());
        }

        return bookingRepository.save(booking);
    }

    @Override
    public void deleteBooking(Long id) {

        Booking booking = bookingRepository
                .findById(id)
                .orElseThrow(() ->
                        new BookingNotFoundException(
                                "Booking not found"));

        bookingRepository.delete(booking);
    }
}