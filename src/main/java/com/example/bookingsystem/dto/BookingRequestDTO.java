package com.example.bookingsystem.dto;

import com.example.bookingsystem.enums.BookingStatus;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonPropertyOrder({
        "userId",
        "seatId",
        "showId",
        "bookingStatus",
        "message"
})
public class BookingRequestDTO {

    private Long userId;

    private Long seatId;

    private Long showId;

    private BookingStatus bookingStatus;

    private String message;
}