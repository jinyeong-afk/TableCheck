package practice.springpractice.repository;

import practice.springpractice.domain.Store;

import java.util.List;
import java.util.Optional;

public interface StoreRepository {
    String findByStoreName(String name);
    List<Store> findAllStore();
}
