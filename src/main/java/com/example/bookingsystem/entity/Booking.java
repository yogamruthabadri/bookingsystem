package com.example.bookingsystem.entity;

import com.example.bookingsystem.enums.BookingStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "bookings")
@Data
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    @ManyToOne
    @JoinColumn(name = "seat_id")
    private Seat seat;

    @ManyToOne
    @JoinColumn(name = "show_id")
     Show show;

    @Enumerated(EnumType.STRING)
    private BookingStatus bookingStatus;

    private LocalDateTime bookingTime;
}