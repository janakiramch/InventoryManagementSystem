package com.example.inventorysystem.exception;

import com.example.inventorysystem.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<String>> handleNotFound(ResourceNotFoundException ex){
        return ResponseEntity
                .status(404)
                .body(new ApiResponse<>(false, ex.getMessage()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<String>> handleIllegalArgument(IllegalArgumentException ex){
        return ResponseEntity
                .badRequest()
                .body(new ApiResponse<>(false, ex.getMessage()));
    }
}
