package practice.springpractice.repository;

import practice.springpractice.domain.Seat;

import java.util.Date;
import java.util.Optional;

public interface SeatRepository {
    Seat saveSeat(Seat seat);
    Optional<Seat> checkSeat(Seat seat, Date now_time, int num);
    int deleteSeat(Seat seat);
}
