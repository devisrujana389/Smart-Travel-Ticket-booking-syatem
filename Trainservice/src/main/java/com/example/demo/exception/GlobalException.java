package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GlobalException {
	  @ExceptionHandler(ResourceNotFoundEx.class)
	    public ResponseEntity<String> handleResourceNotFoundException(
	            ResourceNotFoundEx exception) {

	        return new ResponseEntity<>(
	                exception.getMessage(),
	                HttpStatus.NOT_FOUND
	        );
	    }
	}

