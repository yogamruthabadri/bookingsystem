package com.example.bookingsystem.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonPropertyOrder({
        "title",
        "language",
        "duration",
        "message"
})
public class MovieRequestDTO {

    private String title;

    private String language;

    private Integer duration;

    private String message;
}