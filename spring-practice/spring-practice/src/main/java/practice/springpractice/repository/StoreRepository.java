package practice.springpractice.repository;

import practice.springpractice.domain.Store;

import java.util.List;

public interface StoreRepository {
    List<Store> findByStoreName(String name, String area);
    List<Store> findByStoreValue(String name);
    List<Store> findAllStore();
    Store registerStore(Store store);
}
