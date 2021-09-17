package practice.springpractice.repository;

import practice.springpractice.domain.Menu;

import java.util.List;

public interface MenuRepository {
    public Menu saveMenu(Menu menu);

    public List<Menu> findMenu(String store_name);

    public int modifyMenu(Menu menu);
}
