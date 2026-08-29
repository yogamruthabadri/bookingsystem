package com.example.bookingsystem.repository;

import com.example.bookingsystem.entity.Show;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowRepository extends JpaRepository<Show, Long> {
}