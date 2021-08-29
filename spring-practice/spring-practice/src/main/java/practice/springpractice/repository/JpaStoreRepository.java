package practice.springpractice.repository;

import practice.springpractice.domain.Member;
import practice.springpractice.domain.Store;

import javax.persistence.EntityManager;
import java.util.List;

public class JpaStoreRepository implements StoreRepository{

    private final EntityManager em;

    public JpaStoreRepository(EntityManager em) {
        this.em = em;
    }

    public Store tableSave(Store store) {
        em.persist(store);
        return store;
    }

    @Override
    public List<Store> findByStoreName(String name, String area) {
        name = "%" + name + "%";
        List<Store> result = em.createQuery("select s from Store s where s.area = :area and s.store_name like :name", Store.class)
                .setParameter("area", area)
                .setParameter("name", name)
                .getResultList();
        return result;
    }

    @Override
    public List<Store> findByStoreValue(String name) {
        List<Store> result = em.createQuery("select s from Store s left join Member m on s.id = m.name where m.name = :name", Store.class)
                .setParameter("name", name)
                .getResultList();
        return result;
    }

    @Override
    public List<Store> findAllStore() {
        return em.createQuery("select s from Store s", Store.class)
                .getResultList();
    }

    @Override
    public Store registerStore(Store store) {
        return null;
    }
}
