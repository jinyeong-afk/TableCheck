package practice.springpractice.service;

import org.springframework.transaction.annotation.Transactional;
import practice.springpractice.domain.Store;
import practice.springpractice.repository.StoreRepository;

import java.util.List;

@Transactional
public class StoreService {

    private final StoreRepository storeRepository;

    public StoreService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    public List<Store> findByStoreName(String name, String area) {
        return storeRepository.findByStoreName(name, area);
    }

    public List<Store> findAllStore() {
        return storeRepository.findAllStore();
    }
}
