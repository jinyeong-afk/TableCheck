package practice.springpractice.repository;

import practice.springpractice.domain.Reservation;

public interface ReservationRepository {
    public Reservation saveReserve(Reservation reservation);
}
