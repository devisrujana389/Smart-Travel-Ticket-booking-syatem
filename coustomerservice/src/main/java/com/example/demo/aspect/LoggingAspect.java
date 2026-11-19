package com.example.demo.aspect;


import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
@Component
@Aspect
public class LoggingAspect {
	@After("excution(*com.example.demo.Customer.CustomerService.addCustomer(..))")
	public void log() {
		System.out.println("aspect called after");
	}

}
