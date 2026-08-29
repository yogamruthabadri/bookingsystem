package com.example.bookingsystem.controller;

import com.example.bookingsystem.dto.BookingRequestDTO;
import com.example.bookingsystem.entity.Booking;
import com.example.bookingsystem.exception.BookingNotFoundException;
import com.example.bookingsystem.exception.SeatNotAvailableException;
import com.example.bookingsystem.exception.ShowNotFoundException;
import com.example.bookingsystem.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping("/create/booking")
    public ResponseEntity<BookingRequestDTO> createBooking(
            @RequestBody BookingRequestDTO request) {

        try {

            Booking booking =
                    bookingService.createBooking(request);

            request.setMessage(
                    "Booking successfully registered"
            );

            return ResponseEntity.ok(request);

        } catch (SeatNotAvailableException e) {

            request.setMessage(e.getMessage());

            return ResponseEntity.badRequest()
                    .body(request);

        } catch (ShowNotFoundException e) {

            request.setMessage(e.getMessage());

            return ResponseEntity.badRequest()
                    .body(request);

        } catch (IllegalArgumentException e) {

            request.setMessage(e.getMessage());

            return ResponseEntity.badRequest()
                    .body(request);
        }
    }

    @GetMapping("/get/booking/{id}")
    public ResponseEntity<BookingRequestDTO> getBooking(
            @PathVariable Long id) {

        try {

            Booking booking =
                    bookingService.getBookingById(id);

            BookingRequestDTO response =
                    new BookingRequestDTO();

            response.setMessage(
                    "Booking retrieved successfully"
            );

            return ResponseEntity.ok(response);

        } catch (BookingNotFoundException e) {

            BookingRequestDTO response =
                    new BookingRequestDTO();

            response.setMessage(e.getMessage());

            return ResponseEntity.badRequest()
                    .body(response);

        } catch (IllegalArgumentException e) {

            BookingRequestDTO response =
                    new BookingRequestDTO();

            response.setMessage(e.getMessage());

            return ResponseEntity.badRequest()
                    .body(response);
        }
    }

    @GetMapping("/get/all/bookings")
    public ResponseEntity<?> getAllBookings() {

        try {
            return ResponseEntity.ok(
                    bookingService.getAllBookings()
            );

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .build();
        }
    }

    @PutMapping("/update/booking/{id}")
    public ResponseEntity<BookingRequestDTO> updateBooking(
            @PathVariable Long id,
            @RequestBody BookingRequestDTO request) {

        try {

            Booking booking =
                    bookingService.updateBooking(id, request);

            request.setMessage(
                    "Booking successfully updated"
            );

            return ResponseEntity.ok(request);

        } catch (BookingNotFoundException e) {

            request.setMessage(e.getMessage());

            return ResponseEntity.badRequest()
                    .body(request);

        } catch (IllegalArgumentException e) {

            request.setMessage(e.getMessage());

            return ResponseEntity.badRequest()
                    .body(request);
        }
    }

    @DeleteMapping("/delete/booking/{id}")
    public ResponseEntity<BookingRequestDTO> deleteBooking(
            @PathVariable Long id) {

        try {

            bookingService.deleteBooking(id);

            BookingRequestDTO response =
                    new BookingRequestDTO();

            response.setMessage(
                    "Booking successfully deleted"
            );

            return ResponseEntity.ok(response);

        } catch (BookingNotFoundException e) {

            BookingRequestDTO response =
                    new BookingRequestDTO();

            response.setMessage(e.getMessage());

            return ResponseEntity.badRequest()
                    .body(response);

        } catch (IllegalArgumentException e) {

            BookingRequestDTO response =
                    new BookingRequestDTO();

            response.setMessage(e.getMessage());

            return ResponseEntity.badRequest()
                    .body(response);
        }
    }
}