package com.example.demo.customer;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.CustRepo;
import com.example.demo.Customer;
import com.example.demo.CustomerService;
import com.google.common.base.Optional;
@ExtendWith(MockitoExtension.class)
public class CustomerTest {
	@Mock
	CustRepo custrepo;
	@InjectMocks
	CustomerService custservice;
	@BeforeAll
	public static void init() {            //static becoz invoked once
		System.out.println("before all");
	}
	@BeforeEach
	public void initEachTest() {
		System.out.println("beforeeach all");
	}
	
	@Test
	void addCustomerSuccessfull() {
		Customer customer=new Customer();//data preparation
		customer.setCustId(0);
		customer.setCustName("amrutha");
		customer.setCustAdd("sindhanur");
		
		Mockito.when(custrepo.save(customer)).thenReturn(customer); //mocking the method
		
		Customer addedcustomer=custservice.addCustomer(customer); //calling actual method
		
		//test product==matched product
		Assertions.assertEquals(customer.getCustId(), addedcustomer.getCustId()); //assertion
	}
	
	@AfterAll
	public static void Destroy() {
		System.out.println("after all");
	}
	@AfterEach
	public void cleanup() {
		System.out.println("after each");
	}

}
