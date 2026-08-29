package com.example.bookingsystem.repository;

import com.example.bookingsystem.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}