package practice.springpractice.service;

import org.springframework.transaction.annotation.Transactional;
import practice.springpractice.domain.Reservation;
import practice.springpractice.repository.JpaReservationRepository;
import practice.springpractice.repository.ReservationRepository;

import java.util.List;

@Transactional
public class ReservationService {
    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public Reservation saveReserve(Reservation reservation){
        return reservationRepository.saveReserve(reservation);
    }

    public List<Reservation> findReserve(String store_name) {return reservationRepository.findReserve(store_name);}
}
