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
    public Store save(Store store) {
        em.persist(store);
        return store;
    }

    @Override
    public Store tableCheck(String store_name) {
        return em.createQuery("select s from Store s where s.store_name = :store_name", Store.class)
                .setParameter("store_name", store_name)
                .getSingleResult();
    }

    @Override
    public int modify(Store store) {
        System.out.println("값들: " + store.getStore_name() + store.getId() + store.getArea() + store.getManager() + store.getTable_status() + store.getTable_x() + store.getTable_y());
        return em.createQuery("update Store s set s.store_name =:store_name, s.manager = :manager, s.area = :area, s.table_status = :table_status, s.table_x = :table_x, s.table_y = :table_y where s.id = :id")
                .setParameter("store_name", store.getStore_name())
                .setParameter("manager", store.getManager())
                .setParameter("area", store.getArea())
                .setParameter("table_status", store.getTable_status())
                .setParameter("table_x", store.getTable_x())
                .setParameter("table_y", store.getTable_y())
                .setParameter("id", store.getId())
                .executeUpdate();
    }

    @Override
    public int deleteStore(String id) {
        return em.createQuery("delete from Store s where s.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    public Optional<Store> tableBoolean(Store store) {
        String table_status = "%" + store.getTable_status() + "%";
        List<Store> result = em.createQuery("select s from Store s where s.id = :id and s.table_status like :table_status", Store.class)
                .setParameter("id", store.getId())
                .setParameter("table_status", table_status)
                .getResultList();
        return result.stream().findAny();
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
    public Optional<Store> BooleanStore(String id) {
        List<Store> store = em.createQuery("select s from Store s where s.id = :id", Store.class)
                .setParameter("id", id)
                .getResultList();
        return store.stream().findAny();
    }
}
