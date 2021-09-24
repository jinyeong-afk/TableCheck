package practice.springpractice.repository;

import practice.springpractice.domain.Reservation;

import javax.persistence.EntityManager;
import java.util.List;

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

    @Override
    public List<Reservation> findReserve(String store_name) {
        return em.createQuery("select r from Reservation r where r.store_name = :store_name", Reservation.class)
                .setParameter("store_name", store_name)
                .getResultList();
    }
}
