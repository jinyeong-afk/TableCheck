package practice.springpractice.repository;

import practice.springpractice.domain.Reservation;

import java.util.List;

public interface ReservationRepository {
    public Reservation saveReserve(Reservation reservation);

    public List<Reservation> findReserve(String store_name);
}
