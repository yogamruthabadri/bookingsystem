package com.example.bookingsystem.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonPropertyOrder({
        "seatNumber",
        "showId",
        "heldBy",
        "holdExpiryTime",
        "message"
})
public class SeatRequestDTO {

    private String seatNumber;

    private Long showId;

    private Long heldBy;

    private String holdExpiryTime;

    private String message;
}