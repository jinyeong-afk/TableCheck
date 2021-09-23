package practice.springpractice.repository;

import practice.springpractice.domain.Reservation;

import javax.persistence.EntityManager;

public class JpaReservationRepository implements ReservationRepository{

    private final EntityManager em;

    public JpaReservationRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Reservation saveReserve(Reservation reservation) {
        em.persist(reservation);
        return reservation;
    }
}
