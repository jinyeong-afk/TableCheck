package practice.springpractice.repository;

import javax.persistence.EntityManager;

public class JpaMenuRepository implements MenuRepository{
    private final EntityManager em;

    public JpaMenuRepository(EntityManager em) {
        this.em = em;
    }
}
