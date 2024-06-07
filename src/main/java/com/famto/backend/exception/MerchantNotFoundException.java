package com.famto.backend.exception;

public class MerchantNotFoundException extends RuntimeException{

    public MerchantNotFoundException(String message){
        super(message);
    }
}
