package practice.springpractice.service;

import practice.springpractice.domain.Store;
import practice.springpractice.repository.StoreRepository;

import java.util.List;


public class StoreService {

    private final StoreRepository storeRepository;

    public StoreService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    public String findByStoreName(String name) {
        return storeRepository.findByStoreName(name);
    }

    public List<Store> findAllStore() {
        return storeRepository.findAllStore();
    }
}
