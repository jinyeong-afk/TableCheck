package practice.springpractice.service;

import org.springframework.transaction.annotation.Transactional;
import practice.springpractice.repository.MenuRepository;
@Transactional
public class MenuService {
    private final MenuRepository menuRepository;

    public MenuService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }
}
