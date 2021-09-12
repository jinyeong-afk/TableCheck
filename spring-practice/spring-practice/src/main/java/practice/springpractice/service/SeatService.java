package practice.springpractice.service;

import org.springframework.transaction.annotation.Transactional;
import practice.springpractice.domain.Seat;
import practice.springpractice.repository.SeatRepository;

import java.util.Date;
import java.util.Optional;

@Transactional
public class SeatService{
    private final SeatRepository seatRepository;

    public SeatService(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    public Seat saveSeat(Seat seat) {return seatRepository.saveSeat(seat);}

    public Optional<Seat> checkSeat(Seat seat, Date now_time, int num) {return seatRepository.checkSeat(seat, now_time, num);}

    public int deleteSeat(Seat seat) {return seatRepository.deleteSeat(seat);}
}
