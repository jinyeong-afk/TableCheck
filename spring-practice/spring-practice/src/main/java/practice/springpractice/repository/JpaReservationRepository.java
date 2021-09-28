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
        if(reservation.getMenu_num() > 0) em.persist(reservation);
        return reservation;
    }

    @Override
    public List<Reservation> findReserve(String getValue, int value) {
        if(value == 1) return em.createQuery("select r from Reservation r where r.store_name = :store_name", Reservation.class)
                .setParameter("store_name", getValue)
                .getResultList();
        else return em.createQuery("select r from Reservation r where r.id = :id", Reservation.class)
                .setParameter("id", getValue)
                .getResultList();
    }
}
