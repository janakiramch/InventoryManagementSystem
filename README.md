
# Inventory Management System - Spring Boot

## Project Overview
This is a RESTful Inventory Management System built using **Spring Boot**, designed to manage item inventory. 
The system supports creating, updating, retrieving, and deleting items, as well as performing operations on item quantities (increment, decrement), and filtering items based on categories or quantity thresholds.
It provides full CRUD operations and business logic such as quantity-based updates and automated deletion when quantity hits zero.

## Technologies Used
- Java 17
- Spring Boot 3.4.4
- Spring Data JPA
- MySQL
- Lombok
- Maven

## Database
- **MySQL** is used as the database for persistent storage.
- Tables are auto-generated using Spring Data JPA and annotations.

## Folder Structure
```
InventoryManagementSystem/
├── controller/
│   └── ItemController.java
├── dto/
│   └── ItemDto.java
├── exception/
│   └── ResourceNotFoundException.java
├── model/
│   └── Item.java
├── repository/
│   └── ItemRepository.java
├── response/
│   └── ApiResponse.java
├── service/
│   ├── ItemService.java
│   └── impl/ItemServiceImpl.java
└── InventoryManagementSystemApplication.java
```

## Entity: Item
Represents an inventory item.

| Field     | Type    | Description          |
|-----------|---------|----------------------|
| id        | int     | Unique identifier    |
| name      | String  | Item name            |
| price     | double  | Item price           |
| category  | String  | Item category        |
| quantity  | int     | Quantity in stock    |

## Response Wrapper: ApiResponse<T>
```java
public class ApiResponse<T> {
    private boolean success;
    private String message;
    private T data;
}
```
- Standard response format for all APIs.
All responses are wrapped in a consistent ApiResponse format, which includes:

success: A boolean indicating the success of the request.
message: A message providing additional information.
data: The data returned from the request (could be null in some cases).
---

## API Endpoints
Base URL : http://localhost:8080
### 1. Welcome
- **URL:** `/items/home`
- **Method:** GET
- **Response:**
```json
{
  "success": true,
  "message": "Welcome to Items Inventory",
  "data": null
}
```

---

### 2. Create Item
- **URL:** `/items/add-item`
- **Method:** POST
- **Request Body:**
```json
{
  "name": "Laptop",
  "price": 1200.00,
  "category": "Electronics",
  "quantity": 10
}
```
- **Response:**
```json
{
  "success": true,
  "message": "New Item Created",
  "data": {
    "id": 1,
    "name": "Laptop",
    "price": 1200.0,
    "category": "Electronics",
    "quantity": 10
  }
}
```

---

### 3. Get All Items
- **URL:** `/items/get-all-items`
- **Method:** GET
- **Response:** List of all items.

---

### 4. Get Item By ID
- **URL:** `/items/item/{id}`
- **Method:** GET
- **Path Variable:** `id` (int)
- **Response:** Single item with matching ID.

---

### 5. Update Item
- **URL:** `/items/update/{id}`
- **Method:** PUT
- **Request Body:** Updated `ItemDto`
- **Response:** Updated item.

---

### 6. Delete Item
- **URL:** `/items/delete/{id}`
- **Method:** DELETE
- **Path Variable:** `id` (int)
- **Response:**
```json
{
  "success": true,
  "message": "Item deleted"
}
```

---

### 7. Get Items by Category
- **URL:** `/items/category/{category}`
- **Method:** GET
- **Response:** List of items filtered by category.

---

### 8. Get Items by Minimum Quantity
- **URL:** `/items/quantity/{quantity}`
- **Method:** GET
- **Response:** Items with quantity >= specified value.

---

### 9. Increment Quantity
- **URL:** `/items/increment/{id}/quantity/{quantity}`
- **Method:** PUT
- **Response:** Updated item with increased quantity.

---

### 10. Decrement Quantity
- **URL:** `/items/decrement/{id}/quantity/{quantity}`
- **Method:** PUT
- **Response:**
  - If quantity > 0, returns updated item.
  - If quantity <= 0, item is deleted and confirmation is sent.

---

### 11. Delete All Zero-Quantity Items
- **URL:** `/items/quantity/zero`
- **Method:** DELETE
- **Response:**
```json
{
  "success": true,
  "message": "Items with zero quantity removed from Inventory"
}
```
OR
```json
{
  "success": false,
  "message": "No items with zero quantity found."
}
```

---

## Notes
- Uses `@Builder` and Lombok to reduce boilerplate.
- Standardized API responses using `ApiResponse<T>`.
- Graceful error handling using custom exception `ResourceNotFoundException`.

---

## Run Locally
1. Ensure MySQL is running and configured.
2. Update `application.properties` with your DB credentials.
3. Build and run using:
```bash
mvn spring-boot:run
```

Conclusion
This API provides a simple yet effective way to manage inventory. It supports full CRUD operations for item management, as well as specialized operations for handling quantities, filtering by category and quantity, and removing out-of-stock items.


