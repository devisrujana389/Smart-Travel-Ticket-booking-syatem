package com.example.demo.Exception;

public class ResourceNotFoundEx extends RuntimeException {

    public ResourceNotFoundEx(String message) {
        super(message);
    }
}