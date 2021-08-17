package practice.springpractice.repository;

import practice.springpractice.domain.Member;
import practice.springpractice.domain.Store;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaStoreRepository implements StoreRepository{

    private final EntityManager em;

    public JpaStoreRepository(EntityManager em) {
        this.em = em;
    }


    @Override
    public String findByStoreName(String name) {
        Store result = em.createQuery("select s from Store s where s.name = :name", Store.class)
                .setParameter("name", name)
                .getSingleResult();
        return result.toString();
    }

    @Override
    public List<Store> findAllStore() {
        return em.createQuery("select s from Store s", Store.class)
                .getResultList();
    }
}
