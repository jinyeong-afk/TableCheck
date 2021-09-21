package practice.springpractice.repository;

import practice.springpractice.domain.Seat;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class JpaSeatRepository implements SeatRepository{

    private final EntityManager em;

    public JpaSeatRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Seat saveSeat(Seat seat) {
        em.persist(seat);
        return seat;
    }

    @Override
    public Optional<Seat> checkSeat(Seat seat, Date now_time, int num) {
        List<Seat> result = null;
        if(num == 1)
        {
            result =  em.createQuery("select s from Seat s where s.store_name = :store_name and s.enter_time >= :now_time")
                    .setParameter("store_name", seat.getStore_name())
                    .setParameter("now_time", now_time)
                    .getResultList();
        }
        else if(num == 2)
        {
            result =  em.createQuery("select s from Seat s where s.store_name = :store_name and s.seat = :seat")
                    .setParameter("store_name", seat.getStore_name())
                    .setParameter("seat", seat.getSeat())
                    .getResultList();
        }
        return result.stream().findAny();
    }

    @Override
    public int deleteSeat(Seat seat) {
        return em.createQuery("delete from Seat s where s.store_name = :store_name and s.seat = :seat")
                .setParameter("store_name", seat.getStore_name())
                .setParameter("seat", seat.getSeat())
                .executeUpdate();
    }

    @Override
    public List<Seat> findAllSeat(String store_name) {
        return em.createQuery("select s from Seat s where s.store_name = :store_name", Seat.class)
                .setParameter("store_name", store_name)
                .getResultList();
    }

}
