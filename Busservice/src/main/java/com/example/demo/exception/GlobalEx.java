package com.example.demo.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.example.demo.exception.ErrorDeatils;
import com.example.demo.exception.ResouceNotFoundException;

public class GlobalEx {
	@ExceptionHandler(ResouceNotFoundException.class)
	public ResponseEntity<?>handleResourceNotFoundEx(ResouceNotFoundException exception, WebRequest request){
		ErrorDeatils errordetails=new ErrorDeatils(new Date(),exception.getMessage(),request.getDescription(false));
		return new ResponseEntity<>(errordetails,HttpStatus.NOT_FOUND);
	
}
}
