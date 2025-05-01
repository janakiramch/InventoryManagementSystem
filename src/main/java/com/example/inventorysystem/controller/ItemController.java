package com.example.inventorysystem.controller;

import com.example.inventorysystem.dto.ItemDto;
import com.example.inventorysystem.response.ApiResponse;
import com.example.inventorysystem.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService){
        this.itemService = itemService;
    }

    @PostMapping("/add-item")
    public ResponseEntity<ApiResponse<ItemDto>> createItem(@RequestBody ItemDto itemDto){
        ItemDto newItem = itemService.createItem(itemDto);
        return ResponseEntity.ok(
                new ApiResponse<>(true, "New Item Created", newItem)
        );
    }

    @GetMapping("/home")
    public ResponseEntity<ApiResponse<String>> welcomePage(){
        return ResponseEntity.ok().body(new ApiResponse<>(true, "Welcome to Items Inventory"));
    }

    @GetMapping("/get-all-items")
    public ResponseEntity<ApiResponse<List<ItemDto>>> getAllItems(){
        List<ItemDto> itemsList = itemService.getAllItems();
        return ResponseEntity.ok(
                new ApiResponse<>(true, "All items", itemsList)
        );
    }

    @GetMapping("/item/{id}")
    public ResponseEntity<ApiResponse<ItemDto>> getItemById(@PathVariable int id){
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Item retrieved by Id",
                        itemService.getItemById(id))
        );
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<ItemDto>> updateItem(@PathVariable int id, @RequestBody ItemDto itemdto) {
        return ResponseEntity.ok(new ApiResponse<>(true, "Item updated", itemService.updateItem(id, itemdto)));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<String>> deleteItem(@PathVariable int id) {
        itemService.deleteItem(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Item deleted"));
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<ApiResponse<List<ItemDto>>> getByCategory(@PathVariable String category) {
        return ResponseEntity.ok(new ApiResponse<>(true, "Items by category", itemService.getItemsByCategory(category)));
    }

    @GetMapping("/quantity/{quantity}")
    public ResponseEntity<ApiResponse<List<ItemDto>>> getByThresholdQuantity(@PathVariable int quantity) {
        return ResponseEntity.ok(new ApiResponse<>(true, "Items by quantity", itemService.getItemsByThresholdQuantity(quantity)));
    }

    @PutMapping("/increment/{id}/quantity/{quantity}")
    public ResponseEntity<ApiResponse<ItemDto>> incrementQuantity(@PathVariable int id, @PathVariable int quantity) {
        return ResponseEntity.ok(new ApiResponse<>(true, "Quantity increased", itemService.incrementItemQuantity(id, quantity)));
    }

    @PutMapping("/decrement/{id}/quantity/{quantity}")
    public ResponseEntity<ApiResponse<?>> decrementQuantity(@PathVariable int id, @PathVariable int quantity) {
        return ResponseEntity.ok(new ApiResponse<>(true, "Quantity decremented", itemService.decrementItemQuantity(id, quantity)));
    }

    @DeleteMapping("/quantity/zero")
    public ResponseEntity<?> deleteZeroQuantityItems() {
        return ResponseEntity.ok(itemService.deleteItemsWithZeroQuantity());
    }
}
