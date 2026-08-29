package com.example.bookingsystem.controller;

import com.example.bookingsystem.dto.SeatRequestDTO;
import com.example.bookingsystem.entity.Seat;
import com.example.bookingsystem.exception.SeatNotAvailableException;
import com.example.bookingsystem.exception.SeatNotFoundException;
import com.example.bookingsystem.exception.ShowNotFoundException;
import com.example.bookingsystem.service.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/seats")
@RequiredArgsConstructor
public class SeatController {

    private final SeatService seatService;

    @PostMapping("/create/seat")
    public ResponseEntity<SeatRequestDTO> createSeat(
            @RequestBody SeatRequestDTO request) {

        try {

            Seat seat =
                    seatService.createSeat(request);

            request.setMessage(
                    "Seat successfully registered"
            );

            return ResponseEntity.ok(request);

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

    @GetMapping("/get/seat/{id}")
    public ResponseEntity<SeatRequestDTO> getSeat(
            @PathVariable Long id) {

        try {

            Seat seat =
                    seatService.getSeatById(id);

            SeatRequestDTO response =
                    new SeatRequestDTO();

            response.setSeatNumber(
                    seat.getSeatNumber()
            );

            if (seat.getShow() != null) {

                response.setShowId(
                        seat.getShow().getId()
                );
            }

            response.setHeldBy(
                    seat.getHeldBy()
            );

            if (seat.getHoldExpiryTime() != null) {

                response.setHoldExpiryTime(
                        seat.getHoldExpiryTime().toString()
                );
            }

            response.setMessage(
                    "Seat retrieved successfully"
            );

            return ResponseEntity.ok(response);

        } catch (SeatNotFoundException e) {

            SeatRequestDTO response =
                    new SeatRequestDTO();

            response.setMessage(e.getMessage());

            return ResponseEntity.badRequest()
                    .body(response);

        } catch (IllegalArgumentException e) {

            SeatRequestDTO response =
                    new SeatRequestDTO();

            response.setMessage(e.getMessage());

            return ResponseEntity.badRequest()
                    .body(response);
        }
    }

    @GetMapping("/get/all/seats")
    public ResponseEntity<?> getAllSeats() {

        try {

            return ResponseEntity.ok(
                    seatService.getAllSeats()
            );

        } catch (IllegalArgumentException e) {

            return ResponseEntity.badRequest()
                    .build();
        }
    }

    @PutMapping("/update/seat/{id}")
    public ResponseEntity<SeatRequestDTO> updateSeat(
            @PathVariable Long id,
            @RequestBody SeatRequestDTO request) {

        try {

            Seat seat =
                    seatService.updateSeat(id, request);

            request.setMessage(
                    "Seat successfully updated"
            );

            return ResponseEntity.ok(request);

        } catch (SeatNotFoundException e) {

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

    @PostMapping("/{id}/hold")
    public ResponseEntity<SeatRequestDTO> holdSeat(
            @PathVariable Long id,
            @RequestBody SeatRequestDTO request) {

        try {

            Seat seat =
                    seatService.holdSeat(id, request);

            request.setMessage(
                    "Seat successfully held"
            );

            return ResponseEntity.ok(request);

        } catch (SeatNotFoundException e) {

            request.setMessage(e.getMessage());

            return ResponseEntity.badRequest()
                    .body(request);

        } catch (SeatNotAvailableException e) {

            request.setMessage(e.getMessage());

            return ResponseEntity.badRequest()
                    .body(request);

        } catch (IllegalArgumentException e) {

            request.setMessage(e.getMessage());

            return ResponseEntity.badRequest()
                    .body(request);
        }
    }

    @DeleteMapping("/delete/seat/{id}")
    public ResponseEntity<SeatRequestDTO> deleteSeat(
            @PathVariable Long id) {

        try {

            seatService.deleteSeat(id);

            SeatRequestDTO response =
                    new SeatRequestDTO();

            response.setMessage(
                    "Seat successfully deleted"
            );

            return ResponseEntity.ok(response);

        } catch (SeatNotFoundException e) {

            SeatRequestDTO response =
                    new SeatRequestDTO();

            response.setMessage(e.getMessage());

            return ResponseEntity.badRequest()
                    .body(response);

        } catch (IllegalArgumentException e) {

            SeatRequestDTO response =
                    new SeatRequestDTO();

            response.setMessage(e.getMessage());

            return ResponseEntity.badRequest()
                    .body(response);
        }
    }
}