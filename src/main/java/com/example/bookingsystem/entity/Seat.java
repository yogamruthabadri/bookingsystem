package com.example.bookingsystem.entity;

import com.example.bookingsystem.enums.SeatStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "seats")
@Data
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String seatNumber;

    @ManyToOne
    @JoinColumn(name = "show_id")
    private Show show;

    @Enumerated(EnumType.STRING)
    private SeatStatus status;

    private Long heldBy;

    private LocalDateTime holdExpiryTime;
}