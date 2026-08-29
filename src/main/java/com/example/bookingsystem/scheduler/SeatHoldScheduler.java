package com.example.bookingsystem.scheduler;

import com.example.bookingsystem.service.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SeatHoldScheduler {

    private final SeatService seatService;

    @Scheduled(fixedRate = 60000)
    public void releaseExpiredSeats() {

        seatService.releaseExpiredSeats();
    }
}
