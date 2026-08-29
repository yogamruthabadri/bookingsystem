package com.example.bookingsystem.controller;

import com.example.bookingsystem.dto.ShowRequestDTO;
import com.example.bookingsystem.entity.Show;
import com.example.bookingsystem.exception.MovieNotFoundException;
import com.example.bookingsystem.exception.ShowNotFoundException;
import com.example.bookingsystem.service.ShowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shows")
@RequiredArgsConstructor
public class ShowController {

    private final ShowService showService;

    @PostMapping("/create/show")
    public ResponseEntity<ShowRequestDTO> createShow(
            @RequestBody ShowRequestDTO request) {

        try {

            Show show =
                    showService.createShow(request);

            request.setMessage(
                    "Show successfully registered"
            );

            return ResponseEntity.ok(request);

        } catch (MovieNotFoundException e) {

            request.setMessage(e.getMessage());

            return ResponseEntity.badRequest()
                    .body(request);

        } catch (IllegalArgumentException e) {

            request.setMessage(e.getMessage());

            return ResponseEntity.badRequest()
                    .body(request);
        }
    }

    @GetMapping("/get/show/{id}")
    public ResponseEntity<ShowRequestDTO> getShow(
            @PathVariable Long id) {

        try {

            Show show =
                    showService.getShowById(id);

            ShowRequestDTO response =
                    new ShowRequestDTO();

            response.setMessage(
                    "Show retrieved successfully"
            );

            return ResponseEntity.ok(response);

        } catch (ShowNotFoundException e) {

            ShowRequestDTO response =
                    new ShowRequestDTO();

            response.setMessage(e.getMessage());

            return ResponseEntity.badRequest()
                    .body(response);

        } catch (IllegalArgumentException e) {

            ShowRequestDTO response =
                    new ShowRequestDTO();

            response.setMessage(e.getMessage());

            return ResponseEntity.badRequest()
                    .body(response);
        }
    }

    @GetMapping("/get/all/shows")
    public ResponseEntity<?> getAllShows() {

        try {

            return ResponseEntity.ok(
                    showService.getAllShows()
            );

        } catch (IllegalArgumentException e) {

            return ResponseEntity.badRequest()
                    .build();
        }
    }

    @PutMapping("/update/show/{id}")
    public ResponseEntity<ShowRequestDTO> updateShow(
            @PathVariable Long id,
            @RequestBody ShowRequestDTO request) {

        try {

            Show show =
                    showService.updateShow(id, request);

            request.setMessage(
                    "Show successfully updated"
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

    @DeleteMapping("/delete/show/{id}")
    public ResponseEntity<ShowRequestDTO> deleteShow(
            @PathVariable Long id) {

        try {

            showService.deleteShow(id);

            ShowRequestDTO response =
                    new ShowRequestDTO();

            response.setMessage(
                    "Show successfully deleted"
            );

            return ResponseEntity.ok(response);

        } catch (ShowNotFoundException e) {

            ShowRequestDTO response =
                    new ShowRequestDTO();

            response.setMessage(e.getMessage());

            return ResponseEntity.badRequest()
                    .body(response);

        } catch (IllegalArgumentException e) {

            ShowRequestDTO response =
                    new ShowRequestDTO();

            response.setMessage(e.getMessage());

            return ResponseEntity.badRequest()
                    .body(response);
        }
    }
}
