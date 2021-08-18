package practice.springpractice.repository;

import practice.springpractice.domain.Store;

import javax.persistence.EntityManager;
import java.util.List;

public class JpaStoreRepository implements StoreRepository{

    private final EntityManager em;

    public JpaStoreRepository(EntityManager em) {
        this.em = em;
    }


    @Override
    public List<Store> findByStoreName(String name, String area) {
        name = "%" + name + "%";
        System.out.println("name = " + name);
        List<Store> result = em.createQuery("select s from Store s where s.area = :area and s.name like :name", Store.class)
                .setParameter("area", area)
                .setParameter("name", name)
                .getResultList();
        System.out.println(result);
        return result;

    }

    @Override
    public List<Store> findAllStore() {
        return em.createQuery("select s from Store s", Store.class)
                .getResultList();
    }
}
