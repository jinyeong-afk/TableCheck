package practice.springpractice.repository;

import practice.springpractice.domain.Store;

import java.util.List;
import java.util.Optional;

public interface StoreRepository {
    int tableSave(Store store, int value);
    List<Store> findByStoreName(String name, String area);
    Store findByStoreValue(String name);
    List<Store> findAllStore();
    Store registerStore(Store store);
    Optional<Store> BooleanStore(String id);
    Store save(Store store);
    Store tableCheck(String store_name);
    int modify(Store store);
}
