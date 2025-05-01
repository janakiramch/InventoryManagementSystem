Inventory Management System API
Overview
This API allows users to manage items in an inventory system. The system supports creating, updating, retrieving, and deleting items, as well as performing operations on item quantities (increment, decrement), and filtering items based on categories or quantity thresholds.

Endpoints
1. Welcome Page
Endpoint: /items/home

Method: GET

Description: Returns a welcome message to the user.

Response:

json
Copy
Edit
{
    "success": true,
    "message": "Welcome to Items Inventory",
    "data": null
}
2. Create Item
Endpoint: /items/add-item

Method: POST

Request Body:

json
Copy
Edit
{
    "name": "Item Name",
    "price": 100.0,
    "category": "Electronics",
    "quantity": 50
}
Response:

json
Copy
Edit
{
    "success": true,
    "message": "New Item Created",
    "data": {
        "id": 1,
        "name": "Item Name",
        "price": 100.0,
        "category": "Electronics",
        "quantity": 50
    }
}
3. Get All Items
Endpoint: /items/get-all-items

Method: GET

Response:

json
Copy
Edit
{
    "success": true,
    "message": "All items",
    "data": [
        {
            "id": 1,
            "name": "Item Name",
            "price": 100.0,
            "category": "Electronics",
            "quantity": 50
        },
        ...
    ]
}
4. Get Item By ID
Endpoint: /items/item/{id}

Method: GET

Path Variable: id (The unique identifier for the item)

Response:

json
Copy
Edit
{
    "success": true,
    "message": "Item retrieved by Id",
    "data": {
        "id": 1,
        "name": "Item Name",
        "price": 100.0,
        "category": "Electronics",
        "quantity": 50
    }
}
5. Update Item
Endpoint: /items/update/{id}

Method: PUT

Path Variable: id (The unique identifier for the item)

Request Body:

json
Copy
Edit
{
    "name": "Updated Item Name",
    "price": 120.0,
    "category": "Updated Category",
    "quantity": 60
}
Response:

json
Copy
Edit
{
    "success": true,
    "message": "Item updated",
    "data": {
        "id": 1,
        "name": "Updated Item Name",
        "price": 120.0,
        "category": "Updated Category",
        "quantity": 60
    }
}
6. Delete Item
Endpoint: /items/delete/{id}

Method: DELETE

Path Variable: id (The unique identifier for the item)

Response:

json
Copy
Edit
{
    "success": true,
    "message": "Item deleted",
    "data": null
}
7. Get Items by Category
Endpoint: /items/category/{category}

Method: GET

Path Variable: category (The category of the items to retrieve)

Response:

json
Copy
Edit
{
    "success": true,
    "message": "Items by category",
    "data": [
        {
            "id": 1,
            "name": "Item Name",
            "price": 100.0,
            "category": "Electronics",
            "quantity": 50
        }
    ]
}
8. Get Items by Quantity Threshold
Endpoint: /items/quantity/{quantity}

Method: GET

Path Variable: quantity (The minimum quantity threshold)

Response:

json
Copy
Edit
{
    "success": true,
    "message": "Items by quantity",
    "data": [
        {
            "id": 1,
            "name": "Item Name",
            "price": 100.0,
            "category": "Electronics",
            "quantity": 50
        }
    ]
}
9. Increment Item Quantity
Endpoint: /items/increment/{id}/quantity/{quantity}

Method: PUT

Path Variable:

id (The unique identifier for the item)

quantity (The quantity to increment)

Response:

json
Copy
Edit
{
    "success": true,
    "message": "Quantity increased",
    "data": {
        "id": 1,
        "name": "Item Name",
        "price": 100.0,
        "category": "Electronics",
        "quantity": 100
    }
}
10. Decrement Item Quantity
Endpoint: /items/decrement/{id}/quantity/{quantity}

Method: PUT

Path Variable:

id (The unique identifier for the item)

quantity (The quantity to decrement)

Response:

json
Copy
Edit
{
    "success": true,
    "message": "Quantity decremented",
    "data": {
        "id": 1,
        "name": "Item Name",
        "price": 100.0,
        "category": "Electronics",
        "quantity": 40
    }
}
11. Delete Items with Zero Quantity
Endpoint: /items/quantity/zero

Method: DELETE

Response:

json
Copy
Edit
{
    "success": true,
    "message": "Items with zero quantity removed from Inventory",
    "data": null
}
Request and Response Format
All responses are wrapped in a consistent ApiResponse format, which includes:

success: A boolean indicating the success of the request.

message: A message providing additional information.

data: The data returned from the request (could be null in some cases).

Dependencies
Spring Boot 2.x

Spring Data JPA

H2 Database or MySQL (depending on your configuration)

Conclusion
This API provides a simple yet effective way to manage inventory. It supports full CRUD operations for item management, as well as specialized operations for handling quantities, filtering by category and quantity, and removing out-of-stock items.
