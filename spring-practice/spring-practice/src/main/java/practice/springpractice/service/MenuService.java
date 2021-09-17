package practice.springpractice.service;

import org.springframework.transaction.annotation.Transactional;
import practice.springpractice.domain.Menu;
import practice.springpractice.repository.MenuRepository;

import java.util.List;

@Transactional
public class MenuService {
    private final MenuRepository menuRepository;

    public MenuService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public Menu saveMenu(Menu menu){return menuRepository.saveMenu(menu);}

    public List<Menu> findMenu(String store_name) {return menuRepository.findMenu(store_name);}

    public int modifyMenu(Menu menu) {return menuRepository.modifyMenu(menu);}
}
