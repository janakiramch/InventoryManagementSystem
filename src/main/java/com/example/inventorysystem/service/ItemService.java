package com.example.inventorysystem.service;

import com.example.inventorysystem.dto.ItemDto;
import com.example.inventorysystem.response.ApiResponse;

import java.util.List;

public interface ItemService {

    ItemDto createItem(ItemDto itemDto);
    ItemDto getItemById(int id);
    List<ItemDto> getAllItems();
    ItemDto updateItem(int id, ItemDto itemDto);
    void deleteItem(int id);

    List<ItemDto> getItemsByCategory(String category);
    List<ItemDto> getItemsByThresholdQuantity(int quantity);

    void deleteItemsWithZeroQuantity();

    ItemDto incrementItemQuantity(int id, int quantity);
    ApiResponse<?> decrementItemQuantity(int id, int quantity);
}
