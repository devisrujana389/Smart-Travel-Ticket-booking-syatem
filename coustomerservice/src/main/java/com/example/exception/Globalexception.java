package com.example.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class Globalexception {
	@ExceptionHandler(ResourceNotFoundEx.class)
	public ResponseEntity<?>handleResourceNotFoundEx(ResourceNotFoundEx exception, WebRequest request){
		ErrorDetails errordetails=new ErrorDetails(new Date(),exception.getMessage(),request.getDescription(false));
		return new ResponseEntity<>(errordetails,HttpStatus.NOT_FOUND);
}
}
