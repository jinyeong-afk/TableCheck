package practice.springpractice.repository;

import practice.springpractice.domain.Menu;

import javax.persistence.EntityManager;
import java.util.List;

public class JpaMenuRepository implements MenuRepository{
    private final EntityManager em;

    public JpaMenuRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Menu saveMenu(Menu menu) {
        em.persist(menu);
        return menu;
    }

    @Override
    public List<Menu> findMenu(String store_name) {
        return em.createQuery("select m from Menu m where m.store_name = :store_name", Menu.class)
                .setParameter("store_name", store_name)
                .getResultList();
    }

    @Override
    public int modifyMenu(Menu menu) {
        return em.createQuery("update Menu m set m.store_name = :store_name, m.menu_name = :menu_name, m.price = :price where m.sequence = :sequence")
                .setParameter("store_name", menu.getStore_name())
                .setParameter("menu_name", menu.getMenu_name())
                .setParameter("price", menu.getPrice())
                .setParameter("sequence", menu.getSequence())
                .executeUpdate();
    }


}
