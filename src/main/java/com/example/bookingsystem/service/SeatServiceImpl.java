package com.example.bookingsystem.service;

import com.example.bookingsystem.dto.SeatRequestDTO;
import com.example.bookingsystem.entity.Seat;
import com.example.bookingsystem.entity.Show;
import com.example.bookingsystem.enums.SeatStatus;
import com.example.bookingsystem.exception.SeatNotAvailableException;
import com.example.bookingsystem.exception.SeatNotFoundException;
import com.example.bookingsystem.exception.ShowNotFoundException;
import com.example.bookingsystem.repository.SeatRepository;
import com.example.bookingsystem.repository.ShowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SeatServiceImpl implements SeatService {

    private final SeatRepository seatRepository;
    private final ShowRepository showRepository;

    @Override
    public Seat createSeat(SeatRequestDTO request) {

        Show show = showRepository.findById(request.getShowId())
                .orElseThrow(() ->
                        new ShowNotFoundException(
                                "Show not found with id: "
                                        + request.getShowId()
                        ));

        Seat seat = new Seat();

        seat.setSeatNumber(request.getSeatNumber());
        seat.setShow(show);
        seat.setStatus(SeatStatus.AVAILABLE);
        seat.setHeldBy(null);
        seat.setHoldExpiryTime(null);

        return seatRepository.save(seat);
    }

    @Override
    public Seat getSeatById(Long id) {

        return seatRepository.findById(id)
                .orElseThrow(() ->
                        new SeatNotFoundException(
                                "Seat not found with id: " + id
                        ));
    }

    @Override
    public List<Seat> getAllSeats() {

        return seatRepository.findAll();
    }

    @Override
    public Seat updateSeat(
            Long id,
            SeatRequestDTO request) {

        Seat seat = seatRepository.findById(id)
                .orElseThrow(() ->
                        new SeatNotFoundException(
                                "Seat not found with id: " + id
                        ));

        if (request.getSeatNumber() != null) {

            seat.setSeatNumber(
                    request.getSeatNumber()
            );
        }

        if (request.getShowId() != null) {

            Show show = showRepository.findById(
                    request.getShowId()
            ).orElseThrow(() ->
                    new ShowNotFoundException(
                            "Show not found with id: "
                                    + request.getShowId()
                    ));

            seat.setShow(show);
        }

        return seatRepository.save(seat);
    }

    @Override
    public Seat holdSeat(
            Long id,
            SeatRequestDTO request) {

        Seat seat = seatRepository.findById(id)
                .orElseThrow(() ->
                        new SeatNotFoundException(
                                "Seat not found with id: " + id
                        ));

        LocalDateTime now =
                LocalDateTime.now();

        if (seat.getStatus() == SeatStatus.BOOKED) {

            throw new SeatNotAvailableException(
                    "Seat is already booked"
            );
        }

        if (seat.getStatus() == SeatStatus.HELD) {

            if (seat.getHoldExpiryTime() != null
                    && seat.getHoldExpiryTime().isAfter(now)) {

                throw new SeatNotAvailableException(
                        "Seat is already held by another user"
                );
            }

            seat.setStatus(
                    SeatStatus.AVAILABLE
            );

            seat.setHeldBy(null);

            seat.setHoldExpiryTime(null);
        }

        if (seat.getStatus() != SeatStatus.AVAILABLE) {

            throw new SeatNotAvailableException(
                    "Seat is not available"
            );
        }

        LocalDateTime expiryTime =
                now.plusMinutes(7);

        seat.setStatus(
                SeatStatus.HELD
        );

        seat.setHeldBy(
                request.getHeldBy()
        );

        seat.setHoldExpiryTime(
                expiryTime
        );

        return seatRepository.save(seat);
    }

    @Override
    public void deleteSeat(Long id) {

        Seat seat = seatRepository.findById(id)
                .orElseThrow(() ->
                        new SeatNotFoundException(
                                "Seat not found with id: " + id
                        ));

        seatRepository.delete(seat);
    }

    @Override
    public void releaseExpiredSeats() {

        List<Seat> seats =
                seatRepository.findAll();

        LocalDateTime now =
                LocalDateTime.now();

        for (Seat seat : seats) {

            if (seat.getStatus() == SeatStatus.HELD
                    && seat.getHoldExpiryTime() != null
                    && seat.getHoldExpiryTime().isBefore(now)) {

                seat.setStatus(
                        SeatStatus.AVAILABLE
                );

                seat.setHeldBy(null);

                seat.setHoldExpiryTime(null);

                seatRepository.save(seat);
            }
        }
    }
}