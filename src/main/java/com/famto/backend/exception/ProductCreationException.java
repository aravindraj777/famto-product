package com.famto.backend.exception;

import com.famto.backend.dto.ProductDto;

public class ProductCreationException extends RuntimeException{

   public ProductCreationException(String message){
        super(message);
    }
}
