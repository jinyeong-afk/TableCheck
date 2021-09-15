package practice.springpractice.repository;

import practice.springpractice.domain.Menu;

import javax.persistence.EntityManager;

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
}
