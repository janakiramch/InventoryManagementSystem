package com.example.inventorysystem.repository;

import com.example.inventorysystem.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {
    List<Item> findByCategory(String category);
    List<Item> findByQuantityGreaterThanEqual(int quantity);

    //void deleteByQuantity(int quantity);
    List<Item> findByQuantity(int quantity);
}
