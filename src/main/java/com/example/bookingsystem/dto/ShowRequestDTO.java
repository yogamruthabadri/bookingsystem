package com.example.bookingsystem.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@JsonPropertyOrder({
        "movieId",
        "showDate",
        "showTime",
        "theatre",
        "message"
})
public class ShowRequestDTO {

    private Long movieId;

    private LocalDate showDate;

    private LocalTime showTime;

    private String theatre;

    private String message;
}