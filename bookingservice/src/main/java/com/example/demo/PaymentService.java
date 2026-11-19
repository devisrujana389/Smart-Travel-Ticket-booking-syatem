package com.example.demo;

import org.springframework.stereotype.Service;

@Service
public class PaymentService {
	 
	public String makepayment(double amount) {
		return "payment successfull";
	}

}
