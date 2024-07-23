package com.fsse2406.project.exception.product;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ProductExistedException extends RuntimeException{
    public ProductExistedException(String name){
        super("Product Existed: " + name);
    }
}
