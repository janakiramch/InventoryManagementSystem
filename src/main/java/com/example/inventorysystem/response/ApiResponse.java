package com.example.inventorysystem.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T>{
    private boolean success;
    //To check if api request has been successful.

    private String message;
    private T data;
    //To keep track of any generic datatype returned in api response.

    public ApiResponse(){

    }

    public ApiResponse(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public ApiResponse(boolean success, String message){
        this.success = success;
        this.message = message;
        this.data = null;
    }
}
