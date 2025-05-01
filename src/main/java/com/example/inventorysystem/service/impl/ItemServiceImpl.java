package com.example.inventorysystem.service.impl;

import com.example.inventorysystem.dto.ItemDto;
import com.example.inventorysystem.exception.ResourceNotFoundException;
import com.example.inventorysystem.model.Item;
import com.example.inventorysystem.repository.ItemRepository;
import com.example.inventorysystem.response.ApiResponse;
import com.example.inventorysystem.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository){
        this.itemRepository = itemRepository;
    }

    private ItemDto mapToDto(Item item){
        return ItemDto.builder()
                .id(item.getId())
                .name(item.getName())
                .price(item.getPrice())
                .category(item.getCategory())
                .quantity(item.getQuantity())
                .build();
    }

    private Item mapToEntity(ItemDto dto){
        return Item.builder()
                .id(dto.getId())
                .name(dto.getName())
                .price(dto.getPrice())
                .category(dto.getCategory())
                .quantity(dto.getQuantity())
                .build();
    }


    @Override
    public ItemDto createItem(ItemDto itemDto) {
        return mapToDto(itemRepository.save(mapToEntity(itemDto)));
    }

    @Override
    public ItemDto getItemById(int id) {
        return mapToDto(itemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Item Not Found with id : " + id)));
    }

    @Override
    public List<ItemDto> getAllItems() {
        return itemRepository.findAll().stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public ItemDto updateItem(int id, ItemDto itemDto) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Item Not Found with id : " + id));
        item.setName(itemDto.getName());
        item.setPrice(itemDto.getPrice());
        item.setCategory(itemDto.getCategory());
        item.setQuantity(itemDto.getQuantity());

        return mapToDto(itemRepository.save(item));
    }

    @Override
    public void deleteItem(int id) {
        Item item = itemRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Item Not Found with Id: " + id));
        itemRepository.delete(item);
    }

    @Override
    public List<ItemDto> getItemsByCategory(String category) {
        return itemRepository.findByCategory(category).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ItemDto> getItemsByThresholdQuantity(int quantity) {
        return itemRepository.findByQuantityGreaterThanEqual(quantity)
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ApiResponse<?> deleteItemsWithZeroQuantity() {
        List<Item> items = itemRepository.findByQuantity(0);
        if (!items.isEmpty()) {
            itemRepository.deleteAll(items);
            return new ApiResponse<>(true, "Items with zero quantity removed from Inventory");
        } else {
            return new ApiResponse<>(false, "No items with zero quantity found.");
        }
    }


    @Override
    public ItemDto incrementItemQuantity(int id, int quantity) {
        if(quantity <= 0){
            throw new IllegalArgumentException("Quantity must be greater than 0");
        }
        Item item = itemRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Item with id: " + id + " Not Found"));

        item.setQuantity(item.getQuantity() + quantity);
        return mapToDto(itemRepository.save(item));
    }

    @Override
    public ApiResponse<?> decrementItemQuantity(int id, int quantity) {
        if(quantity <= 0){
            throw new IllegalArgumentException("Quantity must be greater than 0");
        }

        Item item = itemRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Item with id: " + id + " Not Found"));

        int remainingQuantity = item.getQuantity() - quantity;

        if(remainingQuantity <= 0){
            itemRepository.deleteById(id);
            return new ApiResponse<>(true,
                    "Item Quantity Decremented and inventory is empty now. Item Deleted",
                    null);
        }

        item.setQuantity(remainingQuantity);
        return new ApiResponse<>(true,
                "Item Quantity decremented",
                mapToDto(itemRepository.save(item))
        );
    }
}
