package practice.springpractice.repository;

import practice.springpractice.domain.Store;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaStoreRepository implements StoreRepository{

    private final EntityManager em;

    public JpaStoreRepository(EntityManager em) {
        this.em = em;
    }

    public int tableSave(Store store, int value) {
        if(value == 1)
        {
            return em.createQuery("update Store s set s.table_status = :table_status where s.id = :id")
                    .setParameter("table_status", store.getTable_status())
                    .setParameter("id", store.getId())
                    .executeUpdate();
        }
        else
        {
            return em.createQuery("update Store s set s.table_x = :table_x, s.table_y = :table_y where s.id = :id")
                    .setParameter("table_x", store.getTable_x())
                    .setParameter("table_y", store.getTable_y())
                    .setParameter("id", store.getId())
                    .executeUpdate();
        }

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
    public Store findByStoreValue(String name) {
        Store store = em.createQuery("select s from Store s left join Member m on s.id = m.name where m.name = :name", Store.class)
                .setParameter("name", name)
                .getSingleResult();
        return store;
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

    @Override
    public Optional<Store> BooleanStore(String id) {
        List<Store> store = em.createQuery("select s from Store s where s.id = :id", Store.class)
                .setParameter("id", id)
                .getResultList();
        return store.stream().findAny();
    }
}
